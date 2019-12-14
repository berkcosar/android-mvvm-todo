package info.tuver.todo.domain

import org.koin.dsl.module

val domainModule = module {
    single { ColorDomainImpl(get()) as ColorDomain }
    single { TagDomainImpl(get()) as TagDomain }
    single { TodoDomainImpl(get()) as TodoDomain }
}