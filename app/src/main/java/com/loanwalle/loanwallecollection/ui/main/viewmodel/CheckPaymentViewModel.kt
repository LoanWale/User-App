package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.checkpayment.CheckPaymentR_Request
import com.loanwalle.loanwallecollection.data.model.checkpayment.CheckPayment_Response
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class CheckPaymentViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _irRespnse = MutableLiveData<Event<Resource<CheckPayment_Response>>>()
    val loginResponse:LiveData<Event<Resource<CheckPayment_Response>>> = _irRespnse


    fun loginUser(body:CheckPaymentR_Request) = viewModelScope.launch {
        login(body)
    }

    private suspend fun login(body: CheckPaymentR_Request) {
        _irRespnse.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getCheckPayment(body)
               _irRespnse.postValue(handlePicsResponse(response))
            } else {
                _irRespnse.postValue(Event(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection))))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _irRespnse.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _irRespnse.postValue(
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

    private fun handlePicsResponse(response: Response<CheckPayment_Response>): Event<Resource<CheckPayment_Response>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }


}