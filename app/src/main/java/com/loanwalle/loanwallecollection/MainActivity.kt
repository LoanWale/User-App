package com.loanwalle.loanwallecollection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.Activity.LoanDetailActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // hello from

        val i = Intent(this@MainActivity, LoanDetailActivity::class.java)
        startActivity(i)
    }
}