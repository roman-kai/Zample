package roman.kai.zample.data.api


import roman.kai.zample.BuildConfig

object ApiConstants {

    private const val BASE_URL = "https://api.getchute.com/v2"

    val ENDPOINT by lazy { getEndpoint() }

    @JvmStatic fun getEndpoint(): String {
        return when(BuildConfig.BUILD_TYPE) {
            "debug" -> BASE_URL
            else -> BASE_URL
        }
    }

    /**
     * Api endpoints
     */

    // albums
    const val ALBUMS = "/albums/%s/"
}