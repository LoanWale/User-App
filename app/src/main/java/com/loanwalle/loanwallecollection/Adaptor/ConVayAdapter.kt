package com.loanwalle.loanwallecollection.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Model.User
import com.loanwalle.loanwallecollection.R

class ConVayAdapter(private val context: Context, private var userList: ArrayList<User>): RecyclerView.Adapter<ConVayAdapter.ConveyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConveyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.convay_report_layout,
            parent,false)
        return ConveyViewHolder(v)
    }

    override fun onBindViewHolder(holder: ConveyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int =
        userList.size


    class ConveyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    fun setsData(userList: ArrayList<User>){
        this.userList = userList
    }

}