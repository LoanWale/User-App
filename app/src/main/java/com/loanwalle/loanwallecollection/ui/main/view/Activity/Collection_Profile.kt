package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityCollectionProfileBinding

class Collection_Profile : AppCompatActivity() {

    var binding : ActivityCollectionProfileBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityCollectionProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
       // setContentView(R.layout.activity_collection_profile)

        binding!!.changpass.setOnClickListener {
            val i = Intent(this@Collection_Profile, ForgotPasswordActivity::class.java)
            startActivity(i)
        }



    }
}