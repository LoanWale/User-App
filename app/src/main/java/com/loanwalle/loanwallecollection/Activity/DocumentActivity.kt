package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityDocumentBinding
import com.loanwalle.loanwallecollection.databinding.ActivityIRABinding

class DocumentActivity : AppCompatActivity() {



    var bindig : ActivityDocumentBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindig= ActivityDocumentBinding.inflate(layoutInflater)
        setContentView(bindig!!.root)

        bindig!!.kyc.setOnClickListener {
            var int=Intent(this@DocumentActivity,AadharCard::class.java)
            startActivity(int)
        }
        //setContentView(R.layout.activity_document)
    }
}