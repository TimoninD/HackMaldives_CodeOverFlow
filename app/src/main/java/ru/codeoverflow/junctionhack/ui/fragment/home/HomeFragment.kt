package ru.codeoverflow.junctionhack.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.getKoin
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.di.getNavScopeName
import ru.codeoverflow.junctionhack.di.navScopeScopeName
import ru.codeoverflow.junctionhack.ext.removePaddingFromNavigationItem
import ru.codeoverflow.junctionhack.ext.setupWithNavController
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.ui.navigation.NavControllerNavigator
import java.util.HashSet

class HomeFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_home

    private var currentNavController: LiveData<NavController>? = null

    private var navScopesIds: HashSet<String> = hashSetOf()

    private val listWithoutBottomMenu =
        listOf(
            R.id.detailTourFragment
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        clHome.setOnApplyWindowInsetsListener { clHome, insets ->
            clHome.updatePadding(top = insets.systemWindowInsetTop)
            return@setOnApplyWindowInsetsListener insets
        }

        bottomMenu.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        bottomMenu.removePaddingFromNavigationItem()

        val navGraphIds = listOf(
            R.navigation.tours,
            R.navigation.profile
        )

        val controller = bottomMenu.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.homeNavHostContainer,
            intent = requireActivity().intent
        )

        controller.observe(viewLifecycleOwner, Observer { navController ->

            currentNavController?.value?.addOnDestinationChangedListener { controller, destination, arguments ->
                bottomMenu.isVisible = !listWithoutBottomMenu.contains(destination.id)
                val navScopeId = getNavScopeName(controller.graph.label ?: "")

                val navigatorScope =
                    getKoin().getOrCreateScope(navScopeId, named(navScopeScopeName))
                navigatorScope.get<NavControllerNavigator>().navController = controller
                navScopesIds.add(navScopeId)
            }

        })

        currentNavController = controller
    }

    override fun onDestroy() {
        navScopesIds.forEach {
            getKoin().deleteScope(it)
        }
        super.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        val needActivityHandleBackPressed = currentNavController?.value?.navigateUp() ?: true
        return !needActivityHandleBackPressed
    }
}