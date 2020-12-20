package ru.codeoverflow.junctionhack.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.codeoverflow.junctionhack.model.Prefs
import ru.codeoverflow.junctionhack.model.storage.Cache
import ru.codeoverflow.junctionhack.ui.navigation.NavControllerNavigator


val appModule = module {

    //Navigation
    scope(named(navScopeScopeName)) {
        scoped { NavControllerNavigator() }
    }
    //App
    single { Prefs(androidContext()) }

    //Paper
    single { Cache.PaperCache() }
}

//Navigation
const val navScopeScopeName = "NAV_SCOPE"
const val globalNavScopeId = "NAVIGATION_GLOBAL"

/**
 * Формирует имя скоупа на labl'у графа
 *
 * @param graphLabel имя скоупа с приставкой NAVIGATION_
 */
fun getNavScopeName(graphLabel: CharSequence) = "NAVIGATION_$graphLabel"