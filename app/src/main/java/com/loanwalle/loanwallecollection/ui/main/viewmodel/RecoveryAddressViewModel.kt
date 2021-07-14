package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.recoveryaddress.RecoveryRequest
import com.loanwalle.loanwallecollection.data.model.recoveryaddress.RecoveryResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class RecoveryAddressViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {


    private val _RecoverResponse = MutableLiveData<Event<Resource<RecoveryResponse>>>()
    val recoveryResponse:LiveData<Event<Resource<RecoveryResponse>>> = _RecoverResponse


    fun recoveryAddress(body:RecoveryRequest) = viewModelScope.launch {
        login(body)
    }

    private suspend fun login(body: RecoveryRequest) {
        _RecoverResponse.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getRecoveryAddress(body)
               _RecoverResponse.postValue(handlePicsResponse(response))
            } else {
                _RecoverResponse.postValue(Event(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection))))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _RecoverResponse.postValue(
                        Event(Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.network_failure
                            )
                        ))
                    )
                }
                else -> {
                    _RecoverResponse.postValue(
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

    private fun handlePicsResponse(response: Response<RecoveryResponse>): Event<Resource<RecoveryResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }


}