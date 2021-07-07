package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityLoanDetailBinding
import com.loanwalle.loanwallecollection.util.Constants
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_loan_detail.*

class LoanDetailActivity : AppCompatActivity() {

    var binding: ActivityLoanDetailBinding? = null
    var userpic: String? = null
    var name: String? = null
    var loanNumber: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        back_layout_lo_det.setOnClickListener{
            onBackPressed()
        }

        if (intent.getStringExtra(Constants.USER_NAME) != null) {
            name = intent.getStringExtra(Constants.USER_PIC)!!.toString()
            userpic = intent.getStringExtra(Constants.USER_PIC)!!.toString()
            name = intent.getStringExtra(Constants.USER_NAME)!!.toString()
            loanNumber = intent.getStringExtra(Constants.USER_LOAN_NUMBER)!!.toString()

            name_user.text = name
            loan_user.text = loanNumber

        }

        // residance_add = 1
        // office_add = 2
        // pereferred_add = 3

        binding!!.currentAddText.setOnClickListener {
            val inten = Intent(this, CurrentRecoveryAddressActivity::class.java)
            inten.putExtra(Constants.RESIDANCE_ADD, "RESIDANCE ADDRESS")
            startActivity(inten)
        }
        binding!!.officeadd.setOnClickListener {
            val inten = Intent(this, CurrentRecoveryAddressActivity::class.java)
            inten.putExtra(Constants.OFFICE_ADD, "OFFICE ADDRESS")
            startActivity(inten)
        }
        binding!!.preefredadd.setOnClickListener {
            val inten = Intent(this, CurrentRecoveryAddressActivity::class.java)
            inten.putExtra(Constants.PREFERRED_ADD, "PREFERRED ADDRESS")
            startActivity(inten)
        }
        Glide.with(this).load(userpic).error(R.drawable.userimage).into(binding!!.imageUs)


//        binding!!.officeAddText.setOnClickListener{
//            var intent = Intent(this,Recovery_Address::class.java)
//            startActivity(intent)
//        }
//        binding!!.permanentAddText.setOnClickListener{
//            var intent = Intent(this,Recovery_Address::class.java)
//            startActivity(intent)
//        }
//
//        binding!!.preferdeAddText.setOnClickListener{
//            var intent1 = Intent(this,Recovery_Address::class.java)
//            startActivity(intent1)
//        }

        binding!!.loanDetailText.setOnClickListener {
            val inte = Intent(this, LoanActivity::class.java)
            startActivity(inte)
        }

        binding!!.documents.setOnClickListener {
            val i = Intent(this, DocumentActivity::class.java)
            startActivity(i)
        }

        binding!!.repayments.setOnClickListener {
            val inten = Intent(this, PreviousPayment::class.java)
            startActivity(inten)
        }
    }
}