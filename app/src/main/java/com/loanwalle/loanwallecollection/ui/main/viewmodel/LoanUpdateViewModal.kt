package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.collection.Collection_Request
import com.loanwalle.loanwallecollection.data.model.collection.Collection_Response
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsResponse
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class LoanUpdateViewModal(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _loan_UpdateResponse = MutableLiveData<Event<Resource<Collection_Response>>>()
    val userProfileResponse: LiveData<Event<Resource<Collection_Response>>> = _loan_UpdateResponse


    fun userProfile(body: Collection_Request) = viewModelScope.launch {
        callProfile(body)
    }


    private suspend fun callProfile(body: Collection_Request) {
        _loan_UpdateResponse.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.loanupdate(body)
                _loan_UpdateResponse.postValue(handlePicsResponse(response))
            } else {
                _loan_UpdateResponse.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _loan_UpdateResponse.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.network_failure
                                )
                            ))
                    )
                }
                else -> {
                    _loan_UpdateResponse.postValue(
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


    private fun handlePicsResponse(response: Response<Collection_Response>): Event<Resource<Collection_Response>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }


}