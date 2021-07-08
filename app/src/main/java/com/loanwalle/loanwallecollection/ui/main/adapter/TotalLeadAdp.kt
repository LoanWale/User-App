package com.loanwalle.loanwallecollection.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.totalLead.Data
import com.loanwalle.loanwallecollection.ui.main.view.Activity.LoanDashboardActivity
import com.loanwalle.loanwallecollection.util.Constants


class TotalLeadAdp(context:Context, var listOfTasks: List<Data>) :
    RecyclerView.Adapter<TotalLeadAdp.MyViewHolder>(){
    private val examples: List<Data>
    private val context: Context

    init {
        this.examples = listOfTasks
        this.context = context
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lead_name: TextView = view.findViewById(R.id.lead_name)
        var mobileno: TextView = view.findViewById(R.id.mob_number)
        var address: TextView = view.findViewById(R.id.loc_total)
        var loanno: TextView = view.findViewById(R.id.loan_total)
        var collid: CardView = view.findViewById(R.id.collid_total)
        var image: ImageView = view.findViewById(R.id.total_image)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.totalleadadp, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tasks = listOfTasks[position]
            holder.lead_name.text = tasks.borrower_name+" "+tasks.middle_name+""+tasks.surname
            holder.mobileno.text = tasks.mobile
            holder.address.text = tasks.present_address_line1
            holder.loanno.text = "Loan No. "+tasks.loan_no
        val lnmber = tasks.loan_no
        Log.e("loan_number",tasks.loan_no)
        val im = holder.image
        Glide.with(context).load(tasks.file).error(R.drawable.userimage).into(im)


        holder.collid.setOnClickListener {
            val intent1 = Intent(context, LoanDashboardActivity::class.java)
            intent1.putExtra(Constants.USER_PIC,tasks.file)
            intent1.putExtra(Constants.USER_NAME,holder.lead_name.text.toString())
            intent1.putExtra(Constants.USER_LOAN_NUMBER,tasks.loan_no)

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
