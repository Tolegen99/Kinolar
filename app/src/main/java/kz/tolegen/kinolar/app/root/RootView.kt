package kz.tolegen.kinolar.app.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import kz.tolegen.kinolar.App
import kz.tolegen.kinolar.R
import kz.tolegen.kinolar.Screens.home
import kz.tolegen.kinolar.databinding.ViewRootBinding
import kz.tolegen.kinolar.utils.BackButtonListener
import javax.inject.Inject

class RootView : AppCompatActivity(R.layout.view_root) {

    private val binding by viewBinding(ViewRootBinding::bind)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = object : AppNavigator(this, R.id.fragmentContainer) {
        override fun applyCommand(command: Command) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(home())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else
            super.onBackPressed()
    }

}