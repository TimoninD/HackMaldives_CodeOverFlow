package ru.codeoverflow.junctionhack

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.codeoverflow.junctionhack.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule, networkModule, viewModelModule, interactorModule, networkModuleMl
                )
            )
        }
    }
}