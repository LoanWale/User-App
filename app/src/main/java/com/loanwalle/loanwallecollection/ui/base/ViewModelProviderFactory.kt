package com.loanwalle.loanwallecollection.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.main.viewmodel.*

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(OtpViewModel::class.java)) {
            return OtpViewModel(app, appRepository) as T
        }


  if (modelClass.isAssignableFrom(LoanDetailsViewModal::class.java)) {
            return LoanDetailsViewModal(app, appRepository) as T
        }

        if (modelClass.isAssignableFrom(TodayLeadViewModel::class.java)) {
            return TodayLeadViewModel(app, appRepository) as T
        }

        if (modelClass.isAssignableFrom(TotalLeadViewModel::class.java)) {
            return TotalLeadViewModel(app, appRepository) as T
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

        if (modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java)){
            return ForgotPasswordViewModel(app,appRepository) as T
        }
        if (modelClass.isAssignableFrom(VerifyPasswordViewModel::class.java)){
            return VerifyPasswordViewModel(app,appRepository) as T
        }
        if (modelClass.isAssignableFrom(NewPasswordViewModel::class.java)){
            return NewPasswordViewModel(app,appRepository) as T
        }

        if (modelClass.isAssignableFrom(GetUserProfileViewModel::class.java)){
            return GetUserProfileViewModel(app,appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }




}