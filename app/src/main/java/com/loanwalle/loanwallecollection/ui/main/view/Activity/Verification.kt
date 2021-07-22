package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityVerification2Binding
import com.loanwalle.loanwallecollection.util.Constants
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_verification2.*

class Verification : AppCompatActivity() {

    var binding : ActivityVerification2Binding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVerification2Binding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.loanDetailText.setOnClickListener {
            val i = Intent(this@Verification, LoanActivity::class.java)
            i.putExtra(Constants.LOAN_TYPE, "2")
            startActivity(i)
        }

        veri_back.setOnClickListener{
            onBackPressed()
        }

        binding!!.veriBack.setOnClickListener { finish() }

        binding!!.residece.setOnClickListener{
            val intent = Intent(this,Varification_Location_Activity::class.java)
            intent.putExtra(Constants.RESIDANCE_ADD, "RESIDANCE ADDRESS")
            startActivity(intent)
        }


        binding!!.officeadd.setOnClickListener{
           val ins = Intent(this,Varification_Location_Activity::class.java)
            ins.putExtra(Constants.RESIDANCE_ADD, "OFFICE ADDRESS")
            startActivity(ins)
        }
    }
}