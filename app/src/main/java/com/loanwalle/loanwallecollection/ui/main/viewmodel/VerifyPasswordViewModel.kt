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
import com.loanwalle.loanwallecollection.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.loanwallecollection.data.model.verifyPasswordOtp.VerifyPasswordResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class VerifyPasswordViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _verifyPasswordOTP = MutableLiveData<Event<Resource<VerifyPasswordResponse>>>()
    val userForgotPassword: LiveData<Event<Resource<VerifyPasswordResponse>>> = _verifyPasswordOTP


    fun verfiyPassword(body: VerifyPasswordOTPRequest.VerifyPasswordOTP) = viewModelScope.launch {
        callOtp(body)
    }




    private suspend fun callOtp(body: VerifyPasswordOTPRequest.VerifyPasswordOTP) {
        _verifyPasswordOTP.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.verfiyPassword(body)
                _verifyPasswordOTP.postValue(handleOtpResponse(response))
            } else {
                _verifyPasswordOTP.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _verifyPasswordOTP.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.network_failure
                                )
                            ))
                    )
                }
                else -> {
                    _verifyPasswordOTP.postValue(
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

    private fun handleOtpResponse(response: Response<VerifyPasswordResponse>): Event<Resource<VerifyPasswordResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}