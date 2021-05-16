package com.loanwalle.loanwallecollection.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loanwalle.loanwallecollection.R
class AddressFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val  view =inflater.inflate(R.layout.fragment_address, container, false)

        return view
    }

}