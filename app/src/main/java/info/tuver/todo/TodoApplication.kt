package info.tuver.todo

import android.app.Application
import info.tuver.todo.data.dataModule
import info.tuver.todo.provider.providerModule
import info.tuver.todo.ui.todo.todoModule
import info.tuver.todo.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TodoApplication)
            modules(
                listOf(
                    dataModule,
                    providerModule,
                    uiModule,
                    todoModule
                )
            )
        }
    }

}