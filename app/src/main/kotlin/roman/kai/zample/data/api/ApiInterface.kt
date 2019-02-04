package roman.kai.zample.data.api

import retrofit2.Call
import retrofit2.http.*
import roman.kai.zample.data.api.ApiConstants.ALBUMS
import roman.kai.zample.data.api.model.BaseResponse

interface ApiInterface {

    /**
     * Location
     */

    @GET(ALBUMS)
    fun getAlbums(): Call<BaseResponse>
}