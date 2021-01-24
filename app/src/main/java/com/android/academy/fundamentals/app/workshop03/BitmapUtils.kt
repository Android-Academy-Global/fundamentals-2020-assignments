package com.android.academy.fundamentals.app

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import java.io.File
import java.io.FileOutputStream

/**
 * Blurs the given Bitmap image
 * @param bitmap Image to blur
 * @param applicationContext Application context
 * @return Blurred bitmap image
 */
fun blurBitmap(bitmap: Bitmap, applicationContext: Context): Bitmap {
    lateinit var rsContext: RenderScript
    try {
        // Create the output bitmap
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)

        // Blur the image
        rsContext = RenderScript.create(applicationContext, RenderScript.ContextType.DEBUG)
        val inAlloc = Allocation.createFromBitmap(rsContext, bitmap)
        val outAlloc = Allocation.createTyped(rsContext, inAlloc.type)
        val theIntrinsic = ScriptIntrinsicBlur.create(rsContext, Element.U8_4(rsContext))
        theIntrinsic.apply {
            setRadius(10f)
            theIntrinsic.setInput(inAlloc)
            theIntrinsic.forEach(outAlloc)
        }
        outAlloc.copyTo(output)

        return output
    } finally {
        rsContext.finish()
    }
}

/**
 * Writes bitmap to a temporary file and returns the Uri for the file
 * @param applicationContext Application context
 * @param bitmap Bitmap to write to temp file
 * @return Uri for temp file with bitmap
 */
fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
    val name = "blurred.png"
    val outputDir = File(applicationContext.filesDir, "")
    val outputFile = File(outputDir, name)
    FileOutputStream(outputFile).use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, it)
    }
    return Uri.fromFile(outputFile)
}
