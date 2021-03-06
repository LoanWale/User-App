package com.loanwalle.personalloan.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.personalloan.R
import com.loanwalle.personalloan.app.MyApplication
import com.loanwalle.personalloan.data.model.sendOtp.RequestOtpBody
import com.loanwalle.personalloan.data.model.sendOtp.ResponseOtp
import com.loanwalle.personalloan.data.repository.AppRepository
import com.loanwalle.personalloan.utils.Event
import com.loanwalle.personalloan.utils.Resource
import com.loanwalle.personalloan.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class OtpViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _otpResponse = MutableLiveData<Event<Resource<ResponseOtp>>>()
    val otpResponse: LiveData<Event<Resource<ResponseOtp>>> = _otpResponse
    fun loginOtp(body: RequestOtpBody.RequestOtp) = viewModelScope.launch {
        callOtp(body)
    }
    private suspend fun callOtp(body: RequestOtpBody.RequestOtp) {
        _otpResponse.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.loginOtp(body)
                _otpResponse.postValue(handleOtpResponse(response))
            } else {
                _otpResponse.postValue(Event(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection))))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _otpResponse.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _otpResponse.postValue(
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

    private fun handleOtpResponse(response: Response<ResponseOtp>): Event<Resource<ResponseOtp>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}