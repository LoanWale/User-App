package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.loanwalle.loanwallecollection.Fragment.AddressFragment
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityLoanDetailBinding
import kotlinx.android.synthetic.main.activity_loan_detail.*

class LoanDetailActivity : AppCompatActivity() {

    var binding:ActivityLoanDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        officeadd.setOnClickListener{
            var i = Intent(this,OfficeAddressActivity::class.java)
            startActivity(i)
        }
    }
}