package ru.codeoverflow.junctionhack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.di.globalNavScopeId
import ru.codeoverflow.junctionhack.model.Prefs
import ru.codeoverflow.junctionhack.ui.common.BaseFragment

class MainActivity : AppCompatActivity() {

    private val prefs: Prefs by inject()

    //private val listWithoutToolbar = listOf(R.id.signInFragment, R.id.homeFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = mainActivityNavHostFragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.main)
        val navController = navHostFragment.navController
        when {
            prefs.token != null -> {
                navGraph.startDestination = R.id.homeFragment
                navController.graph = navGraph
            }
            else -> {
                navGraph.startDestination = R.id.signInFragment
                navController.graph = navGraph
            }
        }
        mainActivityNavHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                /*toolbar.isVisible = !listWithoutToolbar.contains(destination.id)*/
            }
    }

    override fun onStop() {
        getKoin().deleteScope(globalNavScopeId)
        super.onStop()
    }

    override fun onBackPressed() {
        if (mainActivityNavHostFragment.findNavController().currentDestination?.id == R.id.homeFragment) {
            if ((mainActivityNavHostFragment.childFragmentManager.fragments[0] as BaseFragment).onBackPressed()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }


}