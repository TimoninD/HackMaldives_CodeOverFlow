package ru.codeoverflow.junctionhack.viewmodel.tours

import androidx.lifecycle.MutableLiveData
import com.kizitonwose.calendarview.model.CalendarDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.model.interactor.ToursInteractor
import ru.codeoverflow.junctionhack.viewmodel.BaseViewModel

private const val MAX_TOUR = 10
private const val ACTIVITIES = 5

class ToursViewModel : BaseViewModel() {

    private val interactor: ToursInteractor by inject()

    val startDay: MutableLiveData<CalendarDay> = MutableLiveData()
    val endDay: MutableLiveData<CalendarDay> = MutableLiveData()

    val listTours: MutableLiveData<List<TourModel>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getTours()
    }

    private fun getTours() {
        coroutineScope.launch {
            isLoading.postValue(true)
            try {
                val listActivities = interactor.getActivities()
                val tours = mutableListOf<TourModel>()
                // Complete tour from activities
                for (i in 0..MAX_TOUR) {
                    val firstItem = listActivities[i * ACTIVITIES]
                    tours.add(
                        TourModel(
                            id = "",
                            image = firstItem.images?.firstOrNull() ?: "",
                            title = firstItem.title ?: "Maldives Beach Tour",
                            description = firstItem.description
                                ?: "An ideal place for a secluded holiday - each resort is located on a separate island, you will not find crowds of tourists here. Your number will always be \"on the first line\", and the clear waters of the Indian Ocean are just a stone's throw away. Fine sand, coconut palms, atoll lagoons, where there are no hidden currents, and strange fish swim under the water, they are eagerly awaiting tourists.",
                            price = firstItem.price ?: 12000,
                            activitiesList = listActivities.subList(
                                i * ACTIVITIES,
                                i * ACTIVITIES - 1 + ACTIVITIES
                            )
                        )
                    )
                }
                withContext(Dispatchers.Main) {
                    listTours.postValue(tours)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            isLoading.postValue(false)
        }
    }
}