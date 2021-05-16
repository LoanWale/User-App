package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    var binding: ActivityOtpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.otpSubmit.setOnClickListener({
            //Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,UnlockActivity::class.java)
            startActivity(intent)
        })
    }
}