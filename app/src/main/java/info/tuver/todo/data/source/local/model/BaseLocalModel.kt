package info.tuver.todo.data.source.local.model

abstract class BaseLocalModel<TModel> {

    abstract fun toModel(): TModel

}