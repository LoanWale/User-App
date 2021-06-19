package com.loanwalle.loanwallecollection.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.app.MyApplication
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileBodies
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileResponse
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordResponse
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.utils.Event
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class GetUserProfileViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _getUserProfile = MutableLiveData<Event<Resource<GetUserProfileResponse>>>()
    val getUserProfile: LiveData<Event<Resource<GetUserProfileResponse>>> = _getUserProfile


    fun getUserProfile(body: GetUserProfileBodies.GetUserProfileRequest) = viewModelScope.launch {
        callOtp(body)
    }




    private suspend fun callOtp(body: GetUserProfileBodies.GetUserProfileRequest) {
        _getUserProfile.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getUserProfile(body)
                _getUserProfile.postValue(handleOtpResponse(response))
            } else {
                _getUserProfile.postValue(
                    Event(
                        Resource.Error(getApplication<MyApplication>().getString(
                            R.string.no_internet_connection)))
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _getUserProfile.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.network_failure
                                )
                            ))
                    )
                }
                else -> {
                    _getUserProfile.postValue(
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

    private fun handleOtpResponse(response: Response<GetUserProfileResponse>): Event<Resource<GetUserProfileResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

}