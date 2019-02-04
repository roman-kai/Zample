package roman.kai.zample.ui.base.navigation

object Navigation {

    var currentNavigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        currentNavigator = navigator
        navigator.onActivated()
    }

    fun removeNavigator() {
        currentNavigator?.onRemoved()
        currentNavigator = null
    }
}