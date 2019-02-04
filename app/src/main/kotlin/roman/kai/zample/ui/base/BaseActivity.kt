package roman.kai.zample.ui.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.arellomobile.mvp.MvpAppCompatActivity
import roman.kai.zample.R
import roman.kai.zample.ui.base.navigation.DefaultNavigator
import roman.kai.zample.ui.base.navigation.Navigation
import roman.kai.zample.ui.base.navigation.Navigator
import java.lang.Exception

/**
 * Created by Roman K. on 04/02/2019
 */
abstract class BaseActivity: MvpAppCompatActivity() {

    private val containerId: Int by lazy { fragmentContainerId() }

    open fun menuId() = 0

    open fun fragmentContainerId() = R.id.fragment_container

    lateinit var navigator: Navigator

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = DefaultNavigator(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menuId() != 0) menuInflater?.inflate(menuId(), menu)
        return true
    }

    fun findToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    /**
     * Toolbar
     */
    fun setupToolbar(toolbar: Toolbar?, title: String, showHomeButton: Boolean = true) {
        setSupportActionBar(toolbar)
        setupToolbar(title, showHomeButton)
    }

    fun setupToolbar(title: String, showHomeButton: Boolean = true) {
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeButton)
        supportActionBar?.setDisplayShowHomeEnabled(showHomeButton)
//        supportActionBar?.setHomeAsUpIndicator( ContextCompat.getDrawable(this, R.drawable.ic_back)) fixme
        supportActionBar?.title = title
    }

    fun openFragment(fragment: Fragment, backStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction.replace(containerId, fragment, fragment::class.java.simpleName)

        if (backStack) transaction.addToBackStack(fragment::class.java.simpleName)

        transaction.commit()
    }

    fun popFragment(backStack: String) {
        supportFragmentManager.popBackStackImmediate(backStack, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(containerId)
    }

    fun showDialog(fragment: DialogFragment) {
        fragment.show(supportFragmentManager, "")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
//        if (backPressHandler != null) {
//            if (backPressHandler!!.backPressed()) {
//                return
//            }
//        }
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }
        try { super.onBackPressed() } catch (ignore: Exception) {}
    }

    /**
     * Cicerone style navigation
     */
    override fun onResumeFragments() {
        super.onResumeFragments()
        Navigation.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        Navigation.removeNavigator()
    }
}