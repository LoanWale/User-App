package com.loanwalle.loanwallecollection.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.Repositry.UserReprository

class UserViewModel : ViewModel() {

    val userLiveData : MutableLiveData<List<User>> = MutableLiveData()

    fun getData(){
        val response = UserReprository.setUserData()
        userLiveData.value = response
    }
}