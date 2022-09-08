package com.jherrera.mytesthcpro.featureGetUsers.viewmodel

import androidx.lifecycle.*
import com.jherrera.domain.common.Result
import com.jherrera.domain.entities.User
import com.jherrera.domain.use_cases.GetUsersUC
import kotlinx.coroutines.launch

class UsersListViewModel(private val useCase: GetUsersUC): ViewModel() {

    private val _list = MutableLiveData<List<User>>()
    val list = _list

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getUsers() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = useCase.invoke()){
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

    class UsersListViewModelFactory(private val useCase: GetUsersUC): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UsersListViewModel(useCase) as T
        }
    }
}