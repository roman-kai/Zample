package roman.kai.zample.di

import roman.kai.zample.data.api.Api
import roman.kai.zample.data.api.ApiInterface
import dagger.Module
import dagger.Provides
import roman.kai.zample.data.local.SharedPrefs
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideApi(): ApiInterface {
        return Api.get()
    }

    @Provides
    @Singleton
    internal fun providePrefs(): SharedPrefs {
        return SharedPrefs.DEFAULT
    }
}