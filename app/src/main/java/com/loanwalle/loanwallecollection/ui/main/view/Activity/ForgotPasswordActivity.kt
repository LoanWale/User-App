package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        otp_send.setOnClickListener{

           var number = phone_num_forgot.text.toString()

            if (number.isEmpty()){
                Toast.makeText(this,"Please Enter Mobile no.",Toast.LENGTH_SHORT).show()
            }else if (number.length <10){
                Toast.makeText(this,"Please Enter Valid number",Toast.LENGTH_SHORT).show()
            }else{
                var intent = Intent(this,VerifyOTPActivity::class.java)
                intent.putExtra("number",number)
                startActivity(intent)
            }


        }
    }
}