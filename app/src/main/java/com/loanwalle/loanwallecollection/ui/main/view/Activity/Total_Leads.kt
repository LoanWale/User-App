package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityTotalLeadsBinding

class Total_Leads : AppCompatActivity() {

    var binding:ActivityTotalLeadsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTotalLeadsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)




    }
}