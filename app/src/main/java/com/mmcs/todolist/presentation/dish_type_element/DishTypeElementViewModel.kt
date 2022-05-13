package com.mmcs.todolist.presentation.dish_type_element

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mmcs.todolist.application.App
import com.mmcs.todolist.data.network.DishType
import com.mmcs.todolist.data.network.models.DishImage
import kotlinx.coroutines.launch

class DishTypeElementViewModel(id: DishType) : AndroidViewModel(App.instance) {
    private val _dishImage = MutableLiveData<List<DishImage>>(null)
    val dishImage: LiveData<List<DishImage>>
        get() = _dishImage
    val isLoading = MutableLiveData(true)

    init {
        viewModelScope.launch {
            val list = mutableSetOf<DishImage>()
            for (i in 0..20) {
                val result = App.dishTypeRepository.fetchDishImage(id.name.lowercase())
                if (result != null) {
                    list.add(result.copy(type = id))
                }
            }
            _dishImage.postValue(list.toList())
            isLoading.postValue(false)
        }
    }

}