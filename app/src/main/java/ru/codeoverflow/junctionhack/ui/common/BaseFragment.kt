package ru.codeoverflow.junctionhack.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.di.globalNavScopeId
import ru.codeoverflow.junctionhack.di.navScopeScopeName
import ru.codeoverflow.junctionhack.ui.MainActivity
import ru.codeoverflow.junctionhack.ui.navigation.NavControllerNavigator

abstract class BaseFragment : Fragment() {

    abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Обновляем ссылку на глобальный navController ,потому что
        //она может иногда изменяться
        val navControllerNavigator = getKoin().getOrCreateScope(
            globalNavScopeId, named(
                navScopeScopeName
            )
        ).get<NavControllerNavigator>()

        navControllerNavigator.navController =
            (activity as MainActivity).findNavController(R.id.mainActivityNavHostFragment)

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * @return true if activity need handle on onBackPressed() callback
     */
    open fun onBackPressed(): Boolean {
        return false
    }


}