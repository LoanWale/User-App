package com.loanwalle.loanwallecollection.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.todaylead.Data
import com.loanwalle.loanwallecollection.ui.main.view.Activity.LoanDetailActivity


class TodayLeadAdp(context:Context, var listOfTasks: List<Data>) :
    RecyclerView.Adapter<TodayLeadAdp.MyViewHolder>(){
    private val examples: List<Data>
    private val context: Context

    init {
        this.examples = listOfTasks
        this.context = context
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lead_name: TextView = view.findViewById(R.id.lead_name)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todaylead_adp, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tasks = listOfTasks[position]
            holder.lead_name.text = tasks.borrower_name+" "+tasks.middle_name+""+tasks.surname

        holder.lead_name.setOnClickListener {
            val intent1 = Intent(context, LoanDetailActivity::class.java)
            context.startActivity(intent1)
        }
    }
    override fun getItemCount(): Int {
        return listOfTasks.size
    }

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
