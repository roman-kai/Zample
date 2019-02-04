package roman.kai.zample

import android.app.Application
import android.content.Context
import roman.kai.zample.di.AppComponent
import roman.kai.zample.di.DaggerAppComponent

/**
 * Created by Roman K. on 04/02/2019
 */
class ZampleApp: Application() {
    companion object {
        @JvmStatic private lateinit var appContext: Context

        @JvmStatic private lateinit var appComponent: AppComponent

        fun getContext() = appContext

        fun getComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .build()
    }

}