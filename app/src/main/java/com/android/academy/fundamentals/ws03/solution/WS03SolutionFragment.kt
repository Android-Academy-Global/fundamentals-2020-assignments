package com.android.academy.fundamentals.ws03.solution

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
import com.android.academy.fundamentals.apiKey
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlin.random.Random

class WS03SolutionFragment : BaseFragment() {

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
            loadCats()
        }
    }

    private fun loadCats() {
        coroutineScope.launch(exceptionHandler) {
            val cats = RetrofitModule.catsApi.getCats()
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
        suspend fun getCats(): List<CatImage>
    }

    private class CatsApiHeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val request = originalRequest.newBuilder()
                .url(originalHttpUrl)
                .addHeader(API_KEY_HEADER, apiKey)
                .build()

            return chain.proceed(request)
        }
    }

    private object RetrofitModule {
        private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(CatsApiHeaderInterceptor())
            .build()

        private val json = Json {
            ignoreUnknownKeys = true
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val catsApi: CatsApi = retrofit.create(CatsApi::class.java)
    }

    companion object {
        private val TAG = WS03SolutionFragment::class.java.simpleName
        private const val API_KEY_HEADER = "x-api-key"

        fun create() = WS03SolutionFragment()
    }
}