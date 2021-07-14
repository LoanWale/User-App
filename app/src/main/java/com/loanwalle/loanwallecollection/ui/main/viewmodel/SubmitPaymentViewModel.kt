package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import android.util.EventLog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.submitPayment.SubmitPaymentRequest
import com.loanwalle.loanwallecollection.data.model.submitPayment.SubmitPaymentResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Error

class SubmitPaymentViewModel(
    private val appRepository: AppRepository,
    app: Application): AndroidViewModel(app) {
        private val _submitPay = MutableLiveData<Event<Resource<SubmitPaymentResponse>>>()
    val submitpay : LiveData<Event<Resource<SubmitPaymentResponse>>> = _submitPay

    fun paymentSub(body: SubmitPaymentRequest) = viewModelScope.launch{
        submit(body)
    }

     private suspend fun submit(body: SubmitPaymentRequest) {
         _submitPay.postValue(Event(Resource.Loading()))
         try {
             if (Utils.hasInternetConnection(getApplication<MyApplication>())){
                 val response = appRepository.submitPayment(body)
                 _submitPay.postValue(handlePicsResponse(response))
             }else{
                 _submitPay.postValue(Event(Resource.
                 Error(getApplication<MyApplication>()
                     .getString(R.string.no_internet_connection))))
             }
         }catch (t:Throwable){
             when (t) {
                 is IOException -> {
                     _submitPay.postValue(
                         Event(
                             Resource.Error(
                                 getApplication<MyApplication>().getString(
                                     R.string.network_failure
                                 )
                             ))
                     )
                 }
                 else -> {
                     _submitPay.postValue(
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

    private fun handlePicsResponse(response: Response<SubmitPaymentResponse>): Event<Resource<SubmitPaymentResponse>>? {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
               return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }


}