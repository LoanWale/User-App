package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_verify_otpactivity.*

class VerifyOTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otpactivity)

        var intent = intent
        var num = intent.getStringExtra("number")

        number_phon.text = num


        complete_btn_otp.setOnClickListener{
            var intent = Intent(this,ResestActivity::class.java)
            startActivity(intent)

        }
    }
}