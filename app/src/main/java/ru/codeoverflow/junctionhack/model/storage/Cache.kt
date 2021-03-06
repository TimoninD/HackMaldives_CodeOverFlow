package ru.codeoverflow.junctionhack.model.storage

import io.paperdb.Paper
import ru.codeoverflow.junctionhack.entity.profile.User
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Cache {

    var user: User?
    var interests: List<String>?
    var favoriteTours: MutableList<TourModel>?

    class PaperCache : Cache {

        override var user by CacheProperty<User?>(USER)
        override var interests by CacheProperty<List<String>?>(INTERESTS)
        override var favoriteTours by CacheProperty<MutableList<TourModel>?>(FAVORITE)

        private class CacheProperty<T : Any?>(
            private val key: String
        ) : ReadWriteProperty<Any, T> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T =
                Paper.book().read(key)

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
                if (value == null) {
                    Paper.book().delete(key)
                } else {
                    Paper.book().write(key, value)
                }
            }

        }

        companion object {
            private const val USER = "USER"
            private const val INTERESTS = "INTERESTS"
            private const val FAVORITE = "FAVORITE"
        }
    }
}