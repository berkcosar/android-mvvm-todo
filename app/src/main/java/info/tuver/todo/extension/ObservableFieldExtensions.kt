package info.tuver.todo.extension

import androidx.databinding.Observable
import androidx.databinding.ObservableField

private class OnPropertyChangedCallback<T>(private val onPropertyChanged: () -> Unit) : Observable.OnPropertyChangedCallback() {

    override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
        onPropertyChanged()
    }

}

fun <T> ObservableField<T>.addOnPropertyChangedCallback(onPropertyChanged: (value: T) -> Unit) {
    addOnPropertyChangedCallback(OnPropertyChangedCallback<T> { get()?.let(onPropertyChanged) })
}