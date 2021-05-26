package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    var binding: ActivityOtpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.otpSubmit.setOnClickListener({
            //Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent)
        })
    }
}