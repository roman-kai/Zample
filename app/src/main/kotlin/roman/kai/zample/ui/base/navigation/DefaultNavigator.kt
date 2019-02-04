package roman.kai.zample.ui.base.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import roman.kai.zample.ZampleApp
import roman.kai.zample.data.local.SharedPrefs
import roman.kai.zample.ui.base.BaseActivity
import javax.inject.Inject

class DefaultNavigator(private val activity: BaseActivity) : Navigator {

    private var removed: Boolean = false

    @Inject
    lateinit var prefs: SharedPrefs

    init {
        ZampleApp.getComponent().inject(this)
    }

    override fun onRemoved() {
        removed = true
    }

    override fun onActivated() {
        removed = false
    }

    override fun openFragment(fragment: Fragment) {
        if (removed) return

        activity.openFragment(fragment)
    }

    override fun startActivity(intent: Intent) {
        activity.startActivity(intent)
    }

    override fun openUrl(url: String, title: String) {
//        activity.startActivity(WebViewActivity.createIntent(activity, url, title)) todo
    }

    override fun openAlbum(id: String) {}
}