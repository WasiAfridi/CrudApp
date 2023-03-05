package com.swistechnologies.crudapp

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swistechnologies.crudapp.dbClasses.Subscriber
import com.swistechnologies.crudapp.dbClasses.SubscribersRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData as LiveData1

class CrudViewModel(private val repository: SubscribersRepository) : ViewModel(), Observable {

    val allSubscribers = repository.getAllSubscribers

    private var isUpdateOrDelete = false

    private lateinit var subScriberToDeleteOrUpdate : Subscriber

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButton = MutableLiveData<String>()

    @Bindable
    val clearAllButton = MutableLiveData<String>()

//    private val statusMessage = MutableLiveData<Event<String>>()
//    var message = LiveData<Event<String>>
//        get() = statusMessage

    init {
        saveOrUpdateButton.value = "Save"
        clearAllButton.value = "Clear All"
    }

    fun saveOrUpdate() {
        if(isUpdateOrDelete){
            subScriberToDeleteOrUpdate.name = inputName.value!!
            subScriberToDeleteOrUpdate.email = inputEmail.value!!
            update(subScriberToDeleteOrUpdate)
        }else {
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Subscriber(0, name, email))
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllOrDelete() {
        if(isUpdateOrDelete){
            delete(subScriberToDeleteOrUpdate)
        }else{
        clearAll()
        }
    }

    private fun insert(subscriber: Subscriber) = viewModelScope.launch {
        repository.insert(subscriber)
        //statusMessage.value = Event("Subscriber Inserted Successfully")
    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch {
        repository.update(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButton.value = "Save"
        clearAllButton.value = "Clear All"
        //statusMessage.value = Event("Subscriber Updated Successfully")
    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch {
        repository.delete(subscriber)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButton.value = "Save"
        clearAllButton.value = "Clear All"
    }

    private fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subScriberToDeleteOrUpdate = subscriber
        saveOrUpdateButton.value = "Update"
        clearAllButton.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
