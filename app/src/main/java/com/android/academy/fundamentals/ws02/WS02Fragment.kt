@file:Suppress("unused")

package com.android.academy.fundamentals.ws02

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.academy.fundamentals.BaseFragment
import com.android.academy.fundamentals.R
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException
import kotlin.random.Random

class WS02Fragment : BaseFragment() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(
            "WS02Fragment",
            "Coroutine exception, scope active:${coroutineScope.isActive}",
            throwable
        )
        coroutineScope = createCoroutineScope()

        val errorTextId = when (throwable) {
            is IOException, is HttpException -> R.string.ws02_results_internet_connection_error_text
            is SerializationException -> R.string.ws02_results_parsing_error_text
            else -> R.string.ws02_results_unexpected_error_text
        }
        tvResults?.text = getText(errorTextId)
    }

    private var ivCatImage: ImageView? = null
    private var tvResults: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_ws02, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivCatImage = view.findViewById(R.id.ivAvatar)
        tvResults = view.findViewById(R.id.tvResults)

        view.findViewById<Button>(R.id.btStart).setOnClickListener {
            loadCats()
        }
    }

    override fun onDestroyView() {
        ivCatImage = null
        tvResults = null

        super.onDestroyView()
    }

    private fun loadCats() {
        coroutineScope.launch(exceptionHandler) {
            // TODO: WS02_06: Replace empty list with call API method to get cats
            val cats = emptyList<CatImage>()
            showCats(cats)
        }
    }

    private suspend fun showCats(catsImages: List<CatImage>) = withContext(Dispatchers.Main) {
        val randomIndex = Random.nextInt(catsImages.size - 1)
        val randomCat = catsImages[randomIndex]
        ivCatImage?.load(randomCat.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_download_progress)
            error(R.drawable.ic_download_error)
            transformations(CircleCropTransformation())
        }
        tvResults?.text = getString(
            R.string.ws01_results_success_text,
            catsImages.joinToString { it.id }
        )
    }

    companion object {
        fun create() = WS02Fragment()
    }
}

// TODO WS02_01: Put annotation Serializable to inform kotlin serialisation to generate adapter for it
private data class CatImage(
    val id: String,
    // TODO WS02_02: The json field is named "url" inform kotlin serialisation about it
    //  with SerialName annotation
    val imageUrl: String
)

private interface CatsApi {
    // TODO: WS02_03 Mark this method for Retrofit to generate API call class
    //  use GET annotation and pass URL in
    suspend fun getCats(): List<CatImage>
}

private object RetrofitModule {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    // TODO: WS02_04: Create retrofit instance using builder - provide base url,
    //  converter factory for json (use asConverterFactory method)
    // private val retrofit: Retrofit =

    // TODO: WS02_05: Create API instance that we will use for networking
    // val catsApi: CatsApi =
}