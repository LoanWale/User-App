package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityRecoveryAddressBinding
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_home_page.btn_collection
import kotlinx.android.synthetic.main.activity_recovery_address.*

class Recovery_Address : AppCompatActivity() {
    var binding: ActivityRecoveryAddressBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecoveryAddressBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.backLayout.setOnClickListener {
           finish()
        }

        binding!!.startvisit.setOnClickListener {
            startvisit!!.setBackgroundResource(R.color.gray)
            collectnow!!.setBackgroundResource(R.color.applColor)


        }

       binding!!.collectnow.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@Recovery_Address, CollectionActivity::class.java)
            startActivity(i)
           finish()
        }


    }
}