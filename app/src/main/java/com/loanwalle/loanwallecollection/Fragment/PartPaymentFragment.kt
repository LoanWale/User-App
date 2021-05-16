package com.loanwalle.loanwallecollection.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_verification.view.*
import kotlinx.android.synthetic.main.fragment_part_payment.*


class PartPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_part_payment, container, false)

        var radio = view.findViewById<RadioButton>(R.id.radio_writeoff)





        return view
    }

}