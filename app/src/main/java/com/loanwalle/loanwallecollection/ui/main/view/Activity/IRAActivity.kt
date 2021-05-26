package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityIRABinding

class IRAActivity : AppCompatActivity() {
    var binding: ActivityIRABinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityIRABinding.inflate(layoutInflater)
        setContentView(binding!!.root)



    }
}