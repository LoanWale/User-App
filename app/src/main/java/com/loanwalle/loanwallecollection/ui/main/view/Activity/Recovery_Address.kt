package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityRecoveryAddressBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_recovery_address.*

class Recovery_Address : AppCompatActivity() {
    var binding: ActivityRecoveryAddressBinding? = null


    lateinit var userProfileViewModel : UserProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        init()

        binding!!.backLayout.setOnClickListener {
           finish()
        }

        binding!!.startvisit.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
            collectnow!!.setBackgroundResource(R.color.applColor)

        }

       binding!!.collectnow.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@Recovery_Address, CollectionActivity::class.java)
            startActivity(i)
           finish()
        }


    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        userProfileViewModel = ViewModelProvider(this, factory).get(UserProfileViewModel::class.java)
    }



    fun hideProgressBar() {
        progress_add.visibility = View.GONE
    }
    fun showProgressBar() {
        progress_add.visibility = View.VISIBLE
    }
}