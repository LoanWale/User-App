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
import com.loanwalle.loanwallecollection.data.model.convenc.Data


class Convenc_ADP(context:Context, var listOfTasks: List<Data>) :
    RecyclerView.Adapter<Convenc_ADP.MyViewHolder>(){
    private val examples: List<Data>
    private val context: Context

    init {
        this.examples = listOfTasks
        this.context = context
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var creatdate: TextView = view.findViewById(R.id.creatdate)
        var loanno_conv: TextView = view.findViewById(R.id.loanno_conv)
        var starttime: TextView = view.findViewById(R.id.starttime)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.convay_report_layout, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tasks = listOfTasks[position]

        if (tasks.followup_date.isNullOrEmpty())
        {
            holder.creatdate.text = "2021-06-20"
            holder.loanno_conv.text = "LW1222"
            holder.starttime.text = "2021-06-20 10:30"
        }else
            holder.creatdate.text = tasks.followup_started_at
        holder.loanno_conv.text = "LW1222"
        holder.starttime.text = tasks.followup_started_at

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
