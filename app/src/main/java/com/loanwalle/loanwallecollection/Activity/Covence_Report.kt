package com.loanwalle.loanwallecollection.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Adaptor.ConVayAdapter
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.ViewModel.UserViewModel

class Covence_Report : AppCompatActivity() {

    private lateinit var recycler : RecyclerView
    private lateinit var userAdapter : ConVayAdapter
    private lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covence_report)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getData()

        userViewModel.userLiveData.observe(this, androidx.lifecycle.Observer {
            userAdapter.setsData(it as ArrayList<User>)
        })
        recycler = findViewById(R.id.convey_recycler)

        userAdapter = ConVayAdapter(this, ArrayList())

        recycler.layoutManager = LinearLayoutManager(this)
        LinearLayoutManager.VERTICAL

        recycler.adapter = userAdapter
    }
}