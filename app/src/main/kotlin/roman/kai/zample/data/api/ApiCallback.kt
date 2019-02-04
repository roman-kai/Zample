package roman.kai.zample.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallback<ResponseType>: Callback<ResponseType> {

    override fun onFailure(call: Call<ResponseType>?, t: Throwable?) {
        onFail(null, -1)
        onComplete()

        t?.printStackTrace()
    }

    @Deprecated("use onSuccess() instead")
    override fun onResponse(call: Call<ResponseType>?, response: Response<ResponseType>?) {
        val method = call?.request()?.url().toString()

        if (response?.body() != null) {
            onSuccess(response.body()!!, response.code())
        } else {
            onFail(response?.errorBody(), response!!.code())
        }

        onComplete()
    }

    abstract fun onSuccess(response: ResponseType, code: Int)

    open fun onFail(errorBody: ResponseBody?, code: Int) {}

    open fun onComplete() {}

    open fun onMaintenance(active: Boolean) {}
}