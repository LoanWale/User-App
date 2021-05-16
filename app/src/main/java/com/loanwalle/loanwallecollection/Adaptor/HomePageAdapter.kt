package com.loanwalle.loanwallecollection.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Activity.HomePageActivity
import com.loanwalle.loanwallecollection.Model.TodayCollection
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.home_page_layout.view.*

class HomePageAdapter(context: Context, val todatcoll: List<TodayCollection>) : RecyclerView.Adapter<HomePageAdapter.HomepageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomepageHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_page_layout,
        parent,false)
        return HomepageHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomepageHolder, position: Int) {
        val todc = todatcoll[position]
        holder.setData(todc,position)
    }

    override fun getItemCount(): Int{
        return todatcoll.size
    }

    class HomepageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(todc:TodayCollection?,pos:Int){
            itemView.textView7.text = todc!!.name
            itemView.textView8.text = todc!!.numbr
        }
    }

}