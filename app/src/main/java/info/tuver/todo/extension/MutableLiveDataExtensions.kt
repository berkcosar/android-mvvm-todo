package info.tuver.todo.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<List<T>>.add(item: T, index: Int = 0) {
    value?.let {
        val existingValue = it.toMutableList()

        existingValue.add(index, item)
        postValue(existingValue)
    }
}

fun <T> MutableLiveData<List<T>>.remove(item: T) {
    value?.let {
        val existingValue = it.toMutableList()

        existingValue.remove(item)
        postValue(existingValue)
    }
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