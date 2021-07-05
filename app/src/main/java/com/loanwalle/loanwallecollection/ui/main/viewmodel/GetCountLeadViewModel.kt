package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.getTodayCountLead.GetCountLeadRequest
import com.loanwalle.loanwallecollection.data.model.getTodayCountLead.GetCountLeadResponse
import com.loanwalle.loanwallecollection.data.model.upDateFollowup.UpdateFollowupRequestBodies
import com.loanwalle.loanwallecollection.data.model.upDateFollowup.UpdateFollowupResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class GetCountLeadViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _getCountlead = MutableLiveData<Event<Resource<GetCountLeadResponse>>>()
    val getCountlead: LiveData<Event<Resource<GetCountLeadResponse>>> = _getCountlead


    fun getCountslead(body: GetCountLeadRequest) = viewModelScope.launch {
        updateCollection(body)
    }




    private suspend fun updateCollection(body: GetCountLeadRequest ) {
        _getCountlead.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getTodayCountLead(body)
                _getCountlead.postValue(handleOtpResponse(response))
            } else {
                _getCountlead.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _getCountlead.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.network_failure
                                )
                            ))
                    )
                }
                else -> {
                    _getCountlead.postValue(
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

    private fun handleOtpResponse(response: Response<GetCountLeadResponse>): Event<Resource<GetCountLeadResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}