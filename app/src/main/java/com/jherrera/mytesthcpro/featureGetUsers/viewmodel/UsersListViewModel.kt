package com.jherrera.mytesthcpro.featureGetUsers.viewmodel

import androidx.lifecycle.*
import com.jherrera.domain.common.Result
import com.jherrera.domain.entities.User
import com.jherrera.mytesthcpro.featureGetUsers.interactor.GetUsersInteractor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UsersListViewModel(private val interactor: GetUsersInteractor): ViewModel() {

    private val _list = MutableLiveData<List<User>?>()
    val list = _list

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    val _events = MutableSharedFlow<UsersEvens>()
    val events = _events.asSharedFlow()

    fun triggerEvent(event: UsersEvens) = viewModelScope.launch {
        _events.emit(event)
    }

    fun getUsers() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = interactor.getUsers.invoke()){
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _list.postValue(result.data)
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    fun getPosts(id: Int, _posts: MutableLiveData<Int?>) {
        viewModelScope.launch {
            when(val result = interactor.getPosts.invoke(id)){
                is Result.Success -> {
                    _posts.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    class UsersListViewModelFactory(private val interactor: GetUsersInteractor): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UsersListViewModel(interactor) as T
        }
    }
}