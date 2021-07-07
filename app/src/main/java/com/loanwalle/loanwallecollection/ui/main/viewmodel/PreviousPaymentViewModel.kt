package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.previousPayment.PreviousPaymentRequest
import com.loanwalle.loanwallecollection.data.model.previousPayment.PreviousPaymentResponse
import com.loanwalle.loanwallecollection.data.model.token.TokenRequest
import com.loanwalle.loanwallecollection.data.model.token.TokenResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PreviousPaymentViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _previousPayment = MutableLiveData<Event<Resource<PreviousPaymentResponse>>>()
    val paymentResponse: LiveData<Event<Resource<PreviousPaymentResponse>>> = _previousPayment


    fun paymetUser(body: PreviousPaymentRequest) = viewModelScope.launch {
        login(body)
    }




    private suspend fun login(body: PreviousPaymentRequest) {
        _previousPayment.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.previousPayment(body)
                _previousPayment.postValue(handlePicsResponse(response))
            } else {
                _previousPayment.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _previousPayment.postValue(
                        Event(
                            Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _previousPayment.postValue(
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

    private fun handlePicsResponse(response: Response<PreviousPaymentResponse>): Event<Resource<PreviousPaymentResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }



}