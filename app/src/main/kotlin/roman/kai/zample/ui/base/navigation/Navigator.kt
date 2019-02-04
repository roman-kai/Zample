package roman.kai.zample.ui.base.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import java.util.*

interface Navigator {

    /**
     * Navigator state
     */
    fun onRemoved()

    fun onActivated()

    /**
     * Basic stuff
     */

    fun openFragment(fragment: Fragment)

    fun startActivity(intent: Intent)

    fun openUrl(url: String, title: String = " ")



    /**
     * Open album
     */
    fun openAlbum(id: String)
}