package com.loanwalle.loanwallecollection.Adaptor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Activity.LoanDetailActivity
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.home_page_layout.view.*

class UserAdapter(private val context: Context, private var userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repayment_layout
        ,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]


    }

    override fun getItemCount(): Int =
        userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    fun setData(userList: ArrayList<User>){
        this.userList = userList
    }

}