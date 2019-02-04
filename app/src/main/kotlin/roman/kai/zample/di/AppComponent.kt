package roman.kai.zample.di

import dagger.Component
import roman.kai.zample.ui.base.navigation.DefaultNavigator
import javax.inject.Singleton

/**
* Created by Roman K. on 07.07.17.
*/
@Singleton
@Component(modules = arrayOf(DataModule::class))
interface AppComponent {
//    fun inject(presenter: Presenter)

    fun inject(navigator: DefaultNavigator)
}