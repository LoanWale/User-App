package com.loanwalle.loanwallecollection.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Adaptor.UserAdapter
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R


class PreviousPayment : AppCompatActivity() {

    private lateinit var recycler : RecyclerView
    private lateinit var userAdapter : UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_payment)


        recycler = findViewById(R.id.re_pymnt_recycler_view)

        userAdapter = UserAdapter(this, ArrayList())

        recycler.layoutManager = LinearLayoutManager(this)
        LinearLayoutManager.VERTICAL

        recycler.adapter = userAdapter
    }
}