package com.loanwalle.loanwallecollection.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Adaptor.ConVayAdapter
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R


class Covence_Report : AppCompatActivity() {

    private lateinit var recycler : RecyclerView
    private lateinit var userAdapter : ConVayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covence_report)




        recycler = findViewById(R.id.convey_recycler)

        userAdapter = ConVayAdapter(this, ArrayList())

        recycler.layoutManager = LinearLayoutManager(this)
        LinearLayoutManager.VERTICAL

        recycler.adapter = userAdapter
    }
}