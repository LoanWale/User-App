package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.toast
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_payment_successfull.*

class PaymentSuccessfullActivity : AppCompatActivity() {

    var amount=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_successfull)

        amount = intent.getStringExtra(Constants.USER_Amount)!!.toString()
        payent_text_succ.setText("Payment of $amount done")

        succ_back.setOnClickListener{
            onBackPressed()
        }

        sharereceipt.setOnClickListener{
            // share receipt

            toast("Payment receipt share to customer email ")

        }

        close.setOnClickListener {

            SessionManegar().remove(this,Constants.RUNNING_LEAD_ID)
            SessionManegar().remove(this,Constants.COLLECTION_RUNNING)
            SessionManegar().remove(this,Constants.RUNNING_STATUS)
            SessionManegar().remove(this,Constants.USER_Follup_Address)
            SessionManegar().remove(this,Constants.USER_Follup_id)
            finish()
        }
    }

    override fun onBackPressed() {
        SessionManegar().remove(this,Constants.RUNNING_LEAD_ID)
        SessionManegar().remove(this,Constants.COLLECTION_RUNNING)
        SessionManegar().remove(this,Constants.RUNNING_STATUS)
        SessionManegar().remove(this,Constants.USER_Follup_Address)
        SessionManegar().remove(this,Constants.USER_Follup_id)
        super.onBackPressed()
    }
}