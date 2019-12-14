package info.tuver.todo.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<List<T>>.contains(item: T): Boolean {
    return value?.contains(item) ?: false
}

fun <T> MutableLiveData<List<T>>.add(item: T, index: Int? = null): List<T> {
    val existingValue = value?.toMutableList() ?: mutableListOf()

    when (index) {
        null -> existingValue.add(item)
        else -> existingValue.add(index, item)
    }

    return existingValue.also {
        postValue(it)
    }
}

fun <T> MutableLiveData<List<T>>.remove(item: T?): List<T> {
    val existingValue = value?.toMutableList() ?: mutableListOf()

    if (item != null) {
        value?.let {
            existingValue.remove(item)
            postValue(existingValue)
        }
    }

    return existingValue
}

fun <T> MutableLiveData<List<T>>.replace(existingItem: T, newItem: T) {
    value?.let {
        val existingValue = it.toMutableList()
        val existingItemIndex = existingValue.indexOf(existingItem)

        if (existingItemIndex > -1) {
            existingValue[existingItemIndex] = newItem
            postValue(existingValue)
        }
    }
}
