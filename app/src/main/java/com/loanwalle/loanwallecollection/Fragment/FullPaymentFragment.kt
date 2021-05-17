package com.loanwalle.loanwallecollection.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.loanwalle.loanwallecollection.Activity.LoanDetailActivity
import com.loanwalle.loanwallecollection.Activity.PaymentActivity
import com.loanwalle.loanwallecollection.R

class FullPaymentFragment : Fragment() {

var submit:Button ?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_full_payment, container, false)
        submit=view.findViewById(R.id.submit)


        submit!!.setOnClickListener {
            var intent1 = Intent(activity, PaymentActivity::class.java)
            startActivity(intent1)

        }



    return view;

    }
}