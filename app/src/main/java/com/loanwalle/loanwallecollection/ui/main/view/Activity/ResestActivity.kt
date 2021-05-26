package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_resest.*

class ResestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resest)

        send_pswrd_btn.setOnClickListener{
            var i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }
    }
}