package roman.kai.zample.data.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import roman.kai.zample.BuildConfig
import java.io.IOException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object Api {
    private val buildVersion = BuildConfig.VERSION_NAME

    @JvmStatic private val apiInstance: ApiInterface by lazy { buildApi() }

    fun get(): ApiInterface {
        return apiInstance
    }

    private fun buildApi(): ApiInterface {
        return Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(buildOkHttpClient())
                .build()
                .create(ApiInterface::class.java)
    }

    private class TokenInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
//                    .addHeader("x-application", ApiConstants.APPLICATION_HEADER)
                    .build()
            return chain.proceed(request)
        }
    }

    private fun buildOkHttpClient() :OkHttpClient {

        val trustAllCerts = object: X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
        }
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustAllCerts), java.security.SecureRandom())
        val sslSocketFactory = sslContext.getSocketFactory()

        val logLvl = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE

        return  OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(logLvl))
                .sslSocketFactory(sslSocketFactory, trustAllCerts)
                .build()
    }
}