package ru.codeoverflow.junctionhack.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.codeoverflow.junctionhack.viewmodel.codeconfirm.ConfirmCodeViewModel
import ru.codeoverflow.junctionhack.viewmodel.login.SignInViewModel
import ru.codeoverflow.junctionhack.viewmodel.tours.ToursViewModel

val viewModelModule = module {
    viewModel { SignInViewModel() }
    viewModel { ConfirmCodeViewModel() }
    viewModel { ToursViewModel() }
}