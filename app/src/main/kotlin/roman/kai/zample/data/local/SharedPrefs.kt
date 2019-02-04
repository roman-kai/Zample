package roman.kai.zample.data.local


import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import roman.kai.zample.ZampleApp

class SharedPrefs private constructor(private val name: String) {

    fun get(): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    /**
     * boolean
     */
    fun getBool(key: String, defaultVal: Boolean = false): Boolean {
        return get().getBoolean(key, defaultVal)
    }

    fun putBool(key: String, value: Boolean) {
        get().edit().putBoolean(key, value).apply()
    }

    /**
     * int
     */
    fun getInt(key: String, defaultVal: Int = -1): Int {
        return get().getInt(key, defaultVal)
    }

    fun putInt(key: String, value: Int) {
        get().edit().putInt(key, value).apply()
    }

    /**
     * float
     */
    fun getFloat(key: String, defaultVal: Float = -1f): Float {
        return get().getFloat(key, defaultVal)
    }

    fun putFloat(key: String, value: Float) {
        get().edit().putFloat(key, value).apply()
    }

    /**
     * long
     */
    fun getLong(key: String, defaultVal: Long = -1): Long {
        return get().getLong(key, defaultVal)
    }

    fun putLong(key: String, value: Long) {
        get().edit().putLong(key, value).apply()
    }

    /**
     * String
     */
    fun getString(key: String, defaultVal: String? = null): String? {
        return get().getString(key, defaultVal)
    }

    fun putString(key: String, value: String?) {
        get().edit().putString(key, value).apply()
    }

    /**
     * Object
     */
    fun <T> getObject(key: String, cls: Class<*>): T? {
        val serialized = getString(key) ?: return null
        return objectSerializer.fromString(serialized, cls) as? T
    }

    fun putObject(key: String, value: Any?) {
        val serialized = if (value != null) objectSerializer.toString(value) else null
        putString(key, serialized)
    }

    fun clear() {
        get().edit().clear().apply()
    }

    interface ObjectSerializer {
        fun toString(o: Any): String
        fun fromString(str: String, cls: Class<*>): Any
    }

    companion object {
        val DEFAULT = SharedPrefs("default")

        private val context: Context by lazy { ZampleApp.getContext() }
        private val objectSerializer = DefaultSerializer()

        class DefaultSerializer: ObjectSerializer {
            override fun toString(o: Any): String {
                return Gson().toJson(o)
            }

            override fun fromString(str: String, cls: Class<*>): Any {
                return Gson().fromJson(str, cls)
            }
        }
    }

    private object Keys {
        // objects
        const val PROFILE = "profile_key"

        // bools
        const val IS_LOGGED_IN = "is_logged_in_key"
    }
}