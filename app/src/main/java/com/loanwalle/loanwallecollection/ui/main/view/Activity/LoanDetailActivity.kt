package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityLoanDetailBinding
import com.loanwalle.loanwallecollection.util.Constants

class LoanDetailActivity : AppCompatActivity() {

    var binding:ActivityLoanDetailBinding? = null
     var userpic:String? =null
     var name:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoanDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        if (intent.getStringExtra(Constants.USER_NAME)!=null)
        {
            name=intent.getStringExtra(Constants.USER_PIC)!!.toString()
            userpic=  intent.getStringExtra(Constants.USER_PIC)!!.toString()

        }

        binding!!.currentAddText.setOnClickListener{
          val inten = Intent(this,CurrentRecoveryAddressActivity::class.java)
            startActivity(inten)
        }
        Glide.with(this).load(userpic).error(R.drawable.userimage).into(binding!!.imageView4)


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

        binding!!.loanDetailText.setOnClickListener{
            val inte = Intent(this,LoanActivity::class.java)
            startActivity(inte)
        }

        binding!!.documents.setOnClickListener{
            val i = Intent(this,DocumentActivity::class.java)
            startActivity(i)
        }

        binding!!.repayments.setOnClickListener{
            val inten = Intent(this,PreviousPayment::class.java)
            startActivity(inten)
        }
    }
}