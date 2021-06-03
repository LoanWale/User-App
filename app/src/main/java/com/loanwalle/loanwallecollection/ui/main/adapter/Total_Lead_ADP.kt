package com.loanwalle.loanwallecollection.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.totalleadadp.view.*

class Total_Lead_ADP : RecyclerView.Adapter<Total_Lead_ADP.TotalViewHolder>() {

    inner class TotalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<com.loanwalle.loanwallecollection.data.model.totalLead.Data>() {
        override fun areItemsTheSame(oldItem: com.loanwalle.loanwallecollection.data.model.totalLead.Data, newItem: com.loanwalle.loanwallecollection.data.model.totalLead.Data): Boolean {
            return oldItem.lead_id == newItem.lead_id
        }

        override fun areContentsTheSame(oldItem: com.loanwalle.loanwallecollection.data.model.totalLead.Data, newItem: com.loanwalle.loanwallecollection.data.model.totalLead.Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TotalViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.totalleadadp,
            parent,
            false
        )
    )
    override fun getItemCount() =  differ.currentList.size

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) {
        val picItem = differ.currentList[position]
        holder.itemView.apply {
            lead_name.text = picItem.name
        }
    }
}