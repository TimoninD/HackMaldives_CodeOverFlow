package ru.codeoverflow.junctionhack.viewmodel.login

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.junctionhack.model.interactor.AuthInteractor
import ru.codeoverflow.junctionhack.util.SingleLiveData
import ru.codeoverflow.junctionhack.viewmodel.BaseViewModel

class SignInViewModel :BaseViewModel(){

    private val interactor: AuthInteractor by inject()

    val signInResult: SingleLiveData<Boolean> = SingleLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun signIn(phone: String) {
        coroutineScope.launch {
            try {
                isLoading.postValue(true)
                interactor.signIn(phone)
                withContext(Dispatchers.Main) {
                    signInResult.postValue(true)
                }
            } catch (t: Throwable) {
                signInResult.postValue(false)
                t.printStackTrace()
            }
            isLoading.postValue(false)
        }
    }

}