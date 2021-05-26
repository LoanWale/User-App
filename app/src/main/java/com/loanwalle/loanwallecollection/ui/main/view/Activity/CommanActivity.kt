package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityCommanBinding

class CommanActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        var binding : ActivityCommanBinding? = null
        super.onCreate(savedInstanceState)
        binding = ActivityCommanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}