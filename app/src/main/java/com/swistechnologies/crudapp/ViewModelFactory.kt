package com.swistechnologies.crudapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swistechnologies.crudapp.dbClasses.SubscribersRepository

class ViewModelFactory(private val repository: SubscribersRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CrudViewModel::class.java)) {
            CrudViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unkhnown model")
        }
    }
}