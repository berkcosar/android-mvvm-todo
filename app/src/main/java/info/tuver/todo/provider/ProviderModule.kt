package info.tuver.todo.provider

import org.koin.dsl.module

val providerModule = module {
    single { CoroutineDispatcherProviderImpl() as CoroutineDispatcherProvider }
}