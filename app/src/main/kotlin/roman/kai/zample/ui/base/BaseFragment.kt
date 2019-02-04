package roman.kai.zample.ui.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import roman.kai.zample.R

abstract class BaseFragment: MvpAppCompatFragment() {

    var toolbar: Toolbar? = null
    var progressBar: View? = null

    val baseActivity: BaseActivity by lazy { activity as BaseActivity }

    var loaded = false

    abstract fun layoutId() : Int

    open fun menuId() = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view?.findViewById<View>(R.id.toolbar) as? Toolbar
        progressBar = view?.findViewById(R.id.progress)

        setHasOptionsMenu(menuId() != 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!loaded) {
            loaded = true
            doInitialLoad()
        }
    }

    open fun doInitialLoad() {}

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (menuId() != 0) inflater!!.inflate(menuId(), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
//            R.id.search -> Navigation.currentNavigator?.openSearch()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Toolbar
     */
    fun setupToolbar(title: String, showHomeButton: Boolean = true) {
        if (activity == null) return
        baseActivity.setupToolbar(toolbar, title, showHomeButton)
    }

    fun setToolbarIcon(drawableId: Int) {
        actionBar()?.setHomeAsUpIndicator(drawableId)

//        if (drawableId == R.drawable.menu) {
//            toolbar?.setNavigationOnClickListener {
//                openDrawer()
//            }
//        }
    }

    /**
     * Basics
     */

    open fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    open fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    /**
     * Activity
     */

    fun back() {
        activity?.onBackPressed()
    }

    fun actionBar() = (activity as? AppCompatActivity)?.supportActionBar
}
