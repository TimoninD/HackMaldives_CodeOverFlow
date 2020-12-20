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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = mainActivityNavHostFragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.main)
        val navController = navHostFragment.navController
        when {
            !prefs.isTestShow -> navGraph.startDestination = R.id.interestsFragment
            !prefs.isBudgetShow -> navGraph.startDestination = R.id.budgetFragment
            prefs.token != null -> navGraph.startDestination = R.id.homeFragment
            else -> navGraph.startDestination = R.id.budgetFragment
        }
        navController.graph = navGraph
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