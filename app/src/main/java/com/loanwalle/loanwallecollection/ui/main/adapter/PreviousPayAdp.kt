package com.loanwalle.loanwallecollection.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.previousPayment.Data
import com.loanwalle.loanwallecollection.ui.main.view.Activity.LoanDetailActivity
import com.loanwalle.loanwallecollection.util.Constants
import de.hdodenhof.circleimageview.CircleImageView

class PreviousPayAdp(context: Context, var listOfTasks: List<Data>) :
    RecyclerView.Adapter<PreviousPayAdp.MyViewHolder>() {
    private val examples: List<Data>
    private val context: Context

    init {
        this.examples = listOfTasks
        this.context = context
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var HS_paid_Am: TextView = view.findViewById(R.id.HS_paid_Am)
        var lon_no: TextView = view.findViewById(R.id.His_loanno)
        var visit_date: TextView = view.findViewById(R.id.His_visit_date)
        var next_date: TextView = view.findViewById(R.id.His_next_date)
        var payment_sts: TextView = view.findViewById(R.id.His_payment_type)
        var wavier: TextView = view.findViewById(R.id.His_wavier_type)
        var payment_s: TextView = view.findViewById(R.id.His_payment_status)
        var reject_ty: TextView = view.findViewById(R.id.His_reject_type)
        var visist_ty: TextView = view.findViewById(R.id.His_visit_type)


    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.repayment_layout, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tasks = listOfTasks[position]

        if (tasks.loan_no != null){
         holder.lon_no.setText( tasks.loan_no)
        }else{
            holder.lon_no.setText("NFPL003456")
        }
        if (tasks.paid_amount != null){
            holder.HS_paid_Am.setText( tasks.paid_amount)
        }else{
            holder.HS_paid_Am.setText( "6,345")
        }

        if (tasks.created_at != null){
            holder.visit_date.setText(tasks.created_at)
        }else{
            holder.visit_date.setText( "2021-06-21")
        }

        if (tasks.next_visit_date != null){
            val date = tasks.next_visit_date.toString().substring(0,10)
            holder.next_date.setText(date)
        }else{
            holder.next_date.setText( "2021-07-22")
        }

        if (tasks.payment_type != null){
            holder.payment_sts.setText(tasks.payment_type)
        }else{
            holder.payment_sts.setText( "part")
        }





        if (tasks.wavier_status!= null){
        if (tasks.wavier_status.equals("1")){
            holder.wavier.setText("Regular")
        }else if (tasks.wavier_status.equals("2")){
            holder.wavier.setText("Stallment")
        }else if (tasks.wavier_status.equals("3")){
            holder.wavier.setText("Write Off")
        }else if (tasks.wavier_status.equals("4")){
            holder.wavier.setText("Discount")
        }
        }else{
            holder.wavier.setText("No Data Found")
        }


        if (tasks.payment_status != null){
            holder.payment_s.setText(tasks.payment_status)
        }else{
            holder.payment_s.setText( "part")
        }

        if (tasks.reject_reason != null){
            holder.reject_ty.setText(tasks.reject_reason)
        }else{
            holder.reject_ty.setText( "Not Found")
        }

        if (tasks.visit_address != null){
            holder.visist_ty.setText(tasks.visit_address)
        }else{
            holder.visist_ty.setText( "Noida")
        }




    }

    override fun getItemCount(): Int {
        return listOfTasks.size
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }
}
