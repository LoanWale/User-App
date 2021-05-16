package com.loanwalle.loanwallecollection.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Activity.HomePageActivity
import com.loanwalle.loanwallecollection.Adaptor.HomePageAdapter
import com.loanwalle.loanwallecollection.Adaptor.UserAdapter
import com.loanwalle.loanwallecollection.Model.TodayCollection
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_lead.*

class LeadFragment() : Fragment() {

    private lateinit var userAdapter : UserAdapter
    private lateinit var userViewModel : UserViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_lead, container, false)


        //initRecyclerView()

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getData()
        userViewModel.userLiveData.observe(this, androidx.lifecycle.Observer {
            userAdapter.setData(it as ArrayList<User>)
        })

        recyclerView = view.findViewById(R.id.recycler_view)

        val  activity = activity as Context
        userAdapter = UserAdapter(activity, ArrayList())
         recyclerView.layoutManager = LinearLayoutManager(activity)
        LinearLayoutManager.VERTICAL

        recyclerView.adapter = userAdapter


        return view



    }
    private fun initRecyclerView(){
        recyclerView = view?.findViewById(R.id.recycler_view)!!
        userAdapter = context?.let { UserAdapter(it, ArrayList()) }!!
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

    }
}