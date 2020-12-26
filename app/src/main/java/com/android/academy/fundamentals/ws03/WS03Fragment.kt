package com.android.academy.fundamentals.ws03

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.academy.fundamentals.BaseFragment
import com.android.academy.fundamentals.BuildConfig
import com.android.academy.fundamentals.R
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlin.random.Random

class WS03Fragment : BaseFragment() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
        coroutineScope = createCoroutineScope()
    }

    private var catPhotoView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ws03, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catPhotoView = view.findViewById(R.id.catPhotoView)

        view.findViewById<Button>(R.id.loadCatView).setOnClickListener {
            laodCatPhoto()
        }
    }

    private fun laodCatPhoto() {
        coroutineScope.launch(exceptionHandler) {
            val cats = RetrofitModule.catsApi.loadCats()
            showRandomCat(cats)
        }
    }

    private suspend fun showRandomCat(catsImages: List<CatImage>) = withContext(Dispatchers.Main) {
        val randomIndex = Random.nextInt(catsImages.size - 1)
        val randomCat = catsImages[randomIndex]
        catPhotoView?.load(randomCat.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_download_progress)
            error(R.drawable.ic_download_error)
            transformations(CircleCropTransformation())
        }
    }

    @Serializable
    private data class CatImage(
        val id: String,
        @SerialName("url")
        val imageUrl: String
    )

    private interface CatsApi {
        @GET("images/search?size=small&order=RANDOM&limit=5&format=json")
        suspend fun loadCats(): List<CatImage>
    }

    // TODO 06: Add interceptor to your Okhttp client
    private class CatsApiHeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val request = originalRequest.newBuilder()
                .url(originalHttpUrl)
                // TODO 05: Add header API_KEY_HEADER with your Api Key to request builder
                .build()

            return chain.proceed(request)
        }
    }

    private object RetrofitModule {
        private val json = Json {
            ignoreUnknownKeys = true
        }

        // TODO 01: Instantiate the OkHttpClient val by using ".newBuilder()"
        // TODO 02: Add "HttpLoggingInterceptor" to your Okhttp client
        // TODO 03: Add Logging Level - ".setLevel(HttpLoggingInterceptor.Level.BODY):

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            //TODO 04: Add okhttp client to retrofit
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val catsApi: CatsApi = retrofit.create(CatsApi::class.java)
    }


    companion object {
        private val TAG = WS03Fragment::class.java.simpleName
        private const val API_KEY_HEADER = "x-api-key"

        fun create() = WS03Fragment()
    }
}