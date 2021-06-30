package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.getFollowupCollection.GetFollowupRequest
import com.loanwalle.loanwallecollection.data.model.getFollowupCollection.GetFollowupResponse
import com.loanwalle.loanwallecollection.data.model.upDateFollowup.UpdateFollowupRequestBodies
import com.loanwalle.loanwallecollection.data.model.upDateFollowup.UpdateFollowupResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class GetFollowupViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _getCollec = MutableLiveData<Event<Resource<GetFollowupResponse>>>()
    val getCollec: LiveData<Event<Resource<GetFollowupResponse>>> = _getCollec


    fun getCollec(body: GetFollowupRequest.GetFollowupRequest) = viewModelScope.launch {
        getCollection(body)
    }




    private suspend fun getCollection(body:GetFollowupRequest.GetFollowupRequest ) {
        _getCollec.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getfollowupColle(body)
                _getCollec.postValue(handleOtpResponse(response))
            } else {
                _getCollec.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _getCollec.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.network_failure
                                )
                            ))
                    )
                }
                else -> {
                    _getCollec.postValue(
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

    private fun handleOtpResponse(response: Response<GetFollowupResponse>): Event<Resource<GetFollowupResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}
