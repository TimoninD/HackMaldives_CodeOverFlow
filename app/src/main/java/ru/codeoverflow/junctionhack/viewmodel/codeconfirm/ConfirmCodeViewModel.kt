package ru.codeoverflow.junctionhack.viewmodel.codeconfirm

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.junctionhack.model.Prefs
import ru.codeoverflow.junctionhack.model.interactor.AuthInteractor
import ru.codeoverflow.junctionhack.model.storage.Cache
import ru.codeoverflow.junctionhack.util.SingleLiveData
import ru.codeoverflow.junctionhack.viewmodel.BaseViewModel

class ConfirmCodeViewModel : BaseViewModel() {
    private val interactor: AuthInteractor by inject()
    private val prefs: Prefs by inject()
    private val cache: Cache by inject()

    val codeConfirmResult: SingleLiveData<Boolean> = SingleLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val isError: SingleLiveData<Boolean> = SingleLiveData()

    fun verify(phoneString: String, code: String) {
        coroutineScope.launch {
            isLoading.postValue(true)
            try {
                val result = interactor.verify(phoneString, code)
                prefs.token = result
                var user = interactor.getUser()

                cache.interests?.let {
                    user = user?.copy(
                        interests = cache.interests ?: listOf(),
                        budget = prefs.budget
                    )
                    cache.interests = null
                    interactor.updateUser(user)
                }
                cache.user = user
                withContext(Dispatchers.Main) {
                    codeConfirmResult.postValue(true)
                }
            } catch (t: Throwable) {
                isError.postValue(true)
                t.printStackTrace()
            }
            isLoading.postValue(false)
        }
    }
}