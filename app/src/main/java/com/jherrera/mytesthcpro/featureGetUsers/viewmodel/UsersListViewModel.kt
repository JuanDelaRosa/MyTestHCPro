package com.jherrera.mytesthcpro.featureGetUsers.viewmodel

import androidx.lifecycle.*
import com.jherrera.domain.common.Result
import com.jherrera.domain.entities.User
import com.jherrera.domain.use_cases.GetPostsUC
import com.jherrera.domain.use_cases.GetUsersUC
import kotlinx.coroutines.launch

class UsersListViewModel(private val useCase: GetUsersUC, private val postUC: GetPostsUC): ViewModel() {

    private val _list = MutableLiveData<List<User>>()
    val list = _list

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _dataLoading = MutableLiveData(true)

    val dataLoading: LiveData<Boolean> = _dataLoading

    private lateinit var tempList : List<User>

    fun getUsers() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = useCase.invoke()){
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    tempList = result.data
                    tempList.forEach {
                        getPosts(it.id)
                    }
                    _list.postValue(tempList)
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    fun getPosts(id: Int) {
        viewModelScope.launch {
            when(val result = postUC.invoke(id)){
                is Result.Success -> {
                    tempList.single { user -> user.id == id }.postCount = result.data
                }
                is Result.Error -> {
                    _error.postValue(result.exception.message)
                }
            }
        }
    }


    class UsersListViewModelFactory(private val usersUC: GetUsersUC, private val postUC: GetPostsUC): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UsersListViewModel(usersUC, postUC) as T
        }
    }
}