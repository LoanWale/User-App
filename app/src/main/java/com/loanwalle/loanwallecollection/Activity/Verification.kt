package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityVerification2Binding
import com.loanwalle.loanwallecollection.databinding.ActivityVerificationBinding

class Verification : AppCompatActivity() {

    var binding : ActivityVerification2Binding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVerification2Binding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.loanDetailText.setOnClickListener {
            val i = Intent(this@Verification, LoanActivity::class.java)
            startActivity(i)
        }



        binding!!.residece.setOnClickListener{
            val intent = Intent(this,CurrentRecoveryAddressActivity::class.java)
            startActivity(intent)
        }


        binding!!.officeadd.setOnClickListener{
           val ins = Intent(this,OfficeAddressActivity::class.java)
            startActivity(ins)
        }
    }
}