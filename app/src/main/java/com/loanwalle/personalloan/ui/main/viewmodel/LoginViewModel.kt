package com.loanwalle.personalloan.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.personalloan.R
import com.loanwalle.personalloan.app.MyApplication
import com.loanwalle.personalloan.data.model.loginResponse.LoginResponce
import com.loanwalle.personalloan.data.model.loginResponse.RequestBodies
import com.loanwalle.personalloan.data.repository.AppRepository
import com.loanwalle.personalloan.utils.Event
import com.loanwalle.personalloan.utils.Resource
import com.loanwalle.personalloan.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class LoginViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _loginResponse = MutableLiveData<Event<Resource<LoginResponce>>>()
    val loginResponse:LiveData<Event<Resource<LoginResponce>>> = _loginResponse


    fun loginUser(body: RequestBodies.LoginBody) = viewModelScope.launch {
        login(body)
    }




    private suspend fun login(body: RequestBodies.LoginBody) {
        _loginResponse.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.loginUser(body)
               _loginResponse.postValue(handlePicsResponse(response))
            } else {
                _loginResponse.postValue(Event(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection))))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _loginResponse.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _loginResponse.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.conversion_error
                            )
                        ))
                    )
                }
            }
        }
    }

    private fun handlePicsResponse(response: Response<LoginResponce>): Event<Resource<LoginResponce>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }


}