package com.loanwalle.loanwallecollection.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityIRABinding
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding

class IRAActivity : AppCompatActivity() {
    var binding: ActivityIRABinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityIRABinding.inflate(layoutInflater)
        setContentView(binding!!.root)



    }
}