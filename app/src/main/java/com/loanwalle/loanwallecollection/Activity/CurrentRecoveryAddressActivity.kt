package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityCurrentRecoveryAddressBinding
import com.loanwalle.loanwallecollection.databinding.ActivityRecoveryAddressBinding
import kotlinx.android.synthetic.main.activity_current_recovery_address.*
import kotlinx.android.synthetic.main.activity_recovery_address.*
import kotlinx.android.synthetic.main.activity_recovery_address.startvisit

class CurrentRecoveryAddressActivity : AppCompatActivity() {
     var binding :ActivityCurrentRecoveryAddressBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.backLayout.setOnClickListener {
            finish()
        }

        binding!!.startvisit.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
           starttnow.setBackgroundResource(R.color.applColor)



        }

        binding!!.starttnow.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@CurrentRecoveryAddressActivity, ResidanceActivity::class.java)
            startActivity(i)
            finish()
        }


    }
}