package ru.codeoverflow.junctionhack.di

import org.koin.dsl.module
import ru.codeoverflow.junctionhack.model.interactor.AuthInteractor
import ru.codeoverflow.junctionhack.model.interactor.ToursInteractor

val interactorModule = module {
    factory { ToursInteractor(get()) }
    factory { AuthInteractor(get()) }
}