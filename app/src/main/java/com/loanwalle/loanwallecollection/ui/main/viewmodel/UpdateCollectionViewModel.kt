package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.startVisit.StartVisitRequestBodies
import com.loanwalle.loanwallecollection.data.model.startVisit.StartVisitResponse
import com.loanwalle.loanwallecollection.data.model.upDateFollowup.UpdateFollowupRequestBodies
import com.loanwalle.loanwallecollection.data.model.upDateFollowup.UpdateFollowupResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class UpdateCollectionViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _upDateCollec = MutableLiveData<Event<Resource<UpdateFollowupResponse>>>()
    val upDateCollec: LiveData<Event<Resource<UpdateFollowupResponse>>> = _upDateCollec


    fun updateCollec(body: UpdateFollowupRequestBodies.UpdateFollowupRequest) = viewModelScope.launch {
        updateCollection(body)
    }




    private suspend fun updateCollection(body:UpdateFollowupRequestBodies.UpdateFollowupRequest ) {
        _upDateCollec.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.updatefollowupColle(body)
                _upDateCollec.postValue(handleOtpResponse(response))
            } else {
                _upDateCollec.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _upDateCollec.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.network_failure
                                )
                            ))
                    )
                }
                else -> {
                    _upDateCollec.postValue(
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

    private fun handleOtpResponse(response: Response<UpdateFollowupResponse>): Event<Resource<UpdateFollowupResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}