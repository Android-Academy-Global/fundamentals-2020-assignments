package com.android.fundamentals.workshop04

import android.app.Dialog
import android.view.View
import androidx.core.content.ContextCompat
import com.android.fundamentals.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SampleBottomSheet : BottomSheetDialogFragment() {

    override fun setupDialog(
        dialog: Dialog,
        style: Int
    ) {
        val contentView =
            View.inflate(context, R.layout.dialog_fragment_ws04, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                contentView.context,
                android.R.color.transparent
            )
        )
    }
}