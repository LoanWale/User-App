package com.loanwalle.loanwallecollection.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding
import com.loanwalle.loanwallecollection.databinding.ActivityRecoveryAddressBinding

class Recovery_Address : AppCompatActivity() {
    var binding: ActivityRecoveryAddressBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.backLayout.setOnClickListener {
           finish()
        }
    }
}