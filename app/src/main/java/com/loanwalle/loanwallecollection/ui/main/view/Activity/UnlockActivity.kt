package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityUnlockBinding

class UnlockActivity : AppCompatActivity() {

    var binding : ActivityUnlockBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnlockBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.usePatt.setOnClickListener{
            var intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent)
        }


    }
}