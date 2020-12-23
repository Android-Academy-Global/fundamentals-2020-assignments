package com.android.academy.fundamentals.ws01.solution

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.academy.fundamentals.BaseFragment
import com.android.academy.fundamentals.R
import com.android.academy.fundamentals.ws01.CatImage
import com.android.academy.fundamentals.ws01.CatImageResult
import kotlinx.coroutines.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import kotlin.random.Random

// Some common things are moved out in the BaseFragment.
class WS01SolutionFragment : BaseFragment() {
	
	private lateinit var tvDownloadSyncButton: TextView
	private lateinit var tvDownloadAsyncButton: TextView
	private lateinit var ivAvatar: ImageView
	private lateinit var tvResults: TextView
	
	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
		coroutineScope = createCoroutineScope()
	}
	
	// region init fragment
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_ws01, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		findViews(parent = view)
	}
	// endregion
	
	// region load image Synchronous
	private fun getImageSync() {
		Log.d(TAG, "getImageSync")
		coroutineScope.launch(exceptionHandler) {
			
			// runCatching {} is a Kotlin-style extension for a try-catch.
			createGetImageCall().runCatching {
				
				// use {} is a Kotlin-style solution for a try-catch with resources.
				// It's closes resources which is implements Closable interface but throws an Exception.
				// As you remember we have to close the response instance.
				this.execute().use { response ->
					createCatImageResult(
						statusCode = response.code,
						headers = response.headers,
						message = response.message,
						
						// string() returns the response as a String or throws IOException.
						jsonResponseBody = response.body?.string()
					)
				}
				
			}.onFailure {
				handleCallError(it)
				
			}.onSuccess {
				handleCallSuccess(catImageResult = it)
			}
		}
	}
	
	private fun createCatImageResult(
		statusCode: Int,
		headers: Headers,
		message: String,
		jsonResponseBody: String?
	): CatImageResult {
		
		return CatImageResult(
			statusCode = statusCode,
			headers = headers,
			message = message,
			jsonResponse = jsonResponseBody
		).also {
			Log.d(TAG, "getImageSync catImageResult:$this")
		}
	}
	// endregion
	
	// region load image Asynchronous
	private fun getImageAsync() {
		Log.d(TAG, "getImageAsync")
		coroutineScope.launch(exceptionHandler) {
			createGetImageCall().enqueue(object : Callback {
				override fun onFailure(call: Call, e: IOException) {
					asyncOnFailureFunction(e)
				}
				
				override fun onResponse(call: Call, response: Response) {
					asyncOnResponseFunction(response)
				}
			})
		}
	}
	
	private fun asyncOnFailureFunction(e: Exception) {
		coroutineScope.launch(exceptionHandler) { handleCallError(e) }
	}
	
	private fun asyncOnResponseFunction(response: Response) {
		coroutineScope.launch(exceptionHandler) {
			response.use {
				CatImageResult(
					statusCode = response.code,
					headers = response.headers,
					message = response.message,
					jsonResponse = response.body?.string()
				).apply {
					Log.d(TAG, "getImageAsync catImageResult:$this")
					handleCallSuccess(this)
				}
			}
		}
	}
	// endregion
	
	// region handle results
	private suspend fun handleCallError(throwable: Throwable) {
		val message = when (throwable) {
			is IOException -> getString(R.string.ws01_results_internet_connection_error_text)
			is IllegalStateException -> getString(R.string.ws01_results_wrong_arguments_error_text)
			else -> getString(R.string.ws01_results_unknown_error_text)
		}
		showError(message, throwable)
	}
	
	private suspend fun handleCallSuccess(catImageResult: CatImageResult) {
		if (catImageResult.hasBadResult()) {
			showEmptyResult(getString(R.string.ws01_results_parsing_error_text))
			
		} else {
			catImageResult.jsonResponse?.let { handleResponse(it) }
		}
	}
	
	/**
	 * JSON parsing [tutorial](http://www.androidhive.info/2012/01/android-json-parsing-tutorial/)
	 *
	 * JSON parsing StackOverflow
	 * [sample](https://stackoverflow.com/questions/28736419/parsing-json-array-and-object-in-android)
	 */
	private suspend fun handleResponse(jsonResponse: String) {
		Log.d(TAG, "parseResponse")
		
		var catsImages = mutableListOf<CatImage>()
		try {
			val jsonArray = JSONArray(jsonResponse)
			for (i in 0 until jsonArray.length()) {
				val jsonObject = jsonArray.getJSONObject(i)
				catsImages.add(CatImage(
					id = jsonObject.getString("id"),
					imageUrl = jsonObject.getString("url")
				))
			}
			bindViews(catsImages)
			
		} catch (exception: JSONException) {
			val message = getString(R.string.ws01_results_parsing_error_text)
			showError(message, exception)
		}
	}
	
	private suspend fun showEmptyResult(message: String) = withContext(Dispatchers.Main) {
		tvResults.text = message
	}
	
	private suspend fun showError(
		message: String,
		throwable: Throwable
	) = withContext(Dispatchers.Main) {
		
		Log.e(TAG, message, throwable)
		tvResults.text = message
	}
	// endregion
	
	// region prepare http request
	private fun createGetImageCall() = OkHttpClient().newCall(createGetImageRequest())
	
	private fun createGetImageRequest() = Request.Builder()
		.get()
		.addHeader(API_KEY_HEADER, getApiKey())
		.url("${getBaseUrl()}$IMAGE_REQUEST_ENDPOINT")
		.build()
	// endregion
	
	// region handle views
	private suspend fun bindViews(catsImages: List<CatImage>) = withContext(Dispatchers.Main) {
		val randomCat = catsImages[Random.nextInt(catsImages.size - 1)]
		Log.d(TAG, "bindViews cat:${randomCat}")
		randomCat.let {
			ivAvatar.load(it.imageUrl) {
				crossfade(true)
				placeholder(R.drawable.ic_download_progress)
				error(R.drawable.ic_download_error)
				transformations(CircleCropTransformation())
			}
		}
		tvResults.text = getString(
			R.string.ws01_results_success_text,
			catsImages.joinToString(", ") { it.id }
		)
	}
	
	private fun findViews(parent: View) {
		tvDownloadSyncButton = parent.findViewById<TextView>(R.id.tvDownloadSyncButton).apply {
			setOnClickListener { getImageSync() }
		}
		tvDownloadAsyncButton = parent.findViewById<TextView>(R.id.tvDownloadAsyncButton).apply {
			setOnClickListener { getImageAsync() }
		}
		ivAvatar = parent.findViewById(R.id.ivAvatar)
		tvResults = parent.findViewById(R.id.tvResults)
	}
	// endregion
	
	companion object {
		private const val API_KEY_HEADER = "x-api-key"
		private const val IMAGE_REQUEST_ENDPOINT = "images/search?size=small&order=RANDOM&limit=5&format=json"
		private val TAG = WS01SolutionFragment::class.java.simpleName
		
		fun create() = WS01SolutionFragment()
	}
}