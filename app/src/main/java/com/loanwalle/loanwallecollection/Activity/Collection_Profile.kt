package com.loanwalle.loanwallecollection.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityCollectionProfileBinding

class Collection_Profile : AppCompatActivity() {

    var binding : ActivityCollectionProfileBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityCollectionProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
       // setContentView(R.layout.activity_collection_profile)
    }
}