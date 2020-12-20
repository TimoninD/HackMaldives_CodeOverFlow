package ru.codeoverflow.junctionhack.viewmodel.profile

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.junctionhack.entity.profile.User
import ru.codeoverflow.junctionhack.model.interactor.AuthInteractor
import ru.codeoverflow.junctionhack.viewmodel.BaseViewModel

class ProfileViewModel : BaseViewModel() {

    private val interactor: AuthInteractor by inject()

    val user: MutableLiveData<User> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getUser()
    }

    private fun getUser() {
        coroutineScope.launch {
            isLoading.postValue(true)
            try {
                val result = interactor.getUser()
                withContext(Dispatchers.Main) {
                    user.postValue(result)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            isLoading.postValue(false)
        }
    }
}