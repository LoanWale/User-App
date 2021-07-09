package com.loanwalle.loanwallecollection.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.checkpayment.Data


class ChcekPayment_ADP(context:Context, var listOfTasks: List<Data>) :
    RecyclerView.Adapter<ChcekPayment_ADP.MyViewHolder>(){
    private val examples: List<Data>
    private val context: Context

    init {
        this.examples = listOfTasks
        this.context = context
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var check_loanno: TextView = view.findViewById(R.id.check_loanno)
        var His_visit_date: TextView = view.findViewById(R.id.His_visit_date)
        var HS_paid_Am: TextView = view.findViewById(R.id.HS_paid_Am)
        var His_payment_type: TextView = view.findViewById(R.id.His_payment_type)
        var Status: TextView = view.findViewById(R.id.Status)
        var ref: TextView = view.findViewById(R.id.ref)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkpay_adp, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tasks = listOfTasks[position]

        holder.check_loanno.text = tasks.loan_no
        holder.His_visit_date.text = tasks.date_of_recived
        holder.HS_paid_Am.text = tasks.payment_amount
        holder.His_payment_type.text = tasks.payment_mode
        holder.Status.text = tasks.status
        holder.ref.text = tasks.refrence_no


    }
    override fun getItemCount(): Int {
        return listOfTasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun settodaylist(listOfTasks: List<Data>) {
        this.listOfTasks = listOfTasks
        notifyDataSetChanged()
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }
}
