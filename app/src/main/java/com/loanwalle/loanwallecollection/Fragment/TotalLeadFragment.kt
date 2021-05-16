package com.loanwalle.loanwallecollection.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Adaptor.UserAdapter
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.ViewModel.UserViewModel

class TotalLeadFragment : Fragment() {


    private lateinit var userAdapter : UserAdapter
    private lateinit var userViewModel : UserViewModel
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_total_lead, container, false)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getData()
        userViewModel.userLiveData.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)
        })

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        LinearLayoutManager.VERTICAL

        return view
    }


}