package info.tuver.todo

import android.app.Application
import info.tuver.todo.data.dataModule
import info.tuver.todo.domain.domainModule
import info.tuver.todo.provider.providerModule
import info.tuver.todo.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TodoApplication)
            modules(
                mutableListOf<Module>().apply {
                    add(dataModule)
                    add(domainModule)
                    add(providerModule)
                    addAll(uiModule)
                }
            )
        }
    }

}