package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotRequestBodies
import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotResponse
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.sendOtp.ResponseOtp
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ForgotPasswordViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _userForgotPassword = MutableLiveData<Event<Resource<ForgotResponse>>>()
    val userForgotPassword: LiveData<Event<Resource<ForgotResponse>>> = _userForgotPassword


    fun forgotPassword(body: ForgotRequestBodies.ForgotRequest) = viewModelScope.launch {
        callOtp(body)
    }




    private suspend fun callOtp(body: ForgotRequestBodies.ForgotRequest) {
        _userForgotPassword.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.forgotPassword(body)
                _userForgotPassword.postValue(handleOtpResponse(response))
            } else {
                _userForgotPassword.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _userForgotPassword.postValue(
                        Event(
                            Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _userForgotPassword.postValue(
                        Event(
                            Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.conversion_error
                            )
                        ))
                    )
                }
            }
        }
    }

    private fun handleOtpResponse(response: Response<ForgotResponse>): Event<Resource<ForgotResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}