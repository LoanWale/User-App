package com.loanwalle.loanwallecollection.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoginViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.OtpViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.VerifyOtpViewModel

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(OtpViewModel::class.java)) {
            return OtpViewModel(app, appRepository) as T
        }

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(app, appRepository) as T
        }
        if (modelClass.isAssignableFrom(VerifyOtpViewModel::class.java)){
            return VerifyOtpViewModel(app,appRepository) as T
        }
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)){
            return UserProfileViewModel(app,appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }




}