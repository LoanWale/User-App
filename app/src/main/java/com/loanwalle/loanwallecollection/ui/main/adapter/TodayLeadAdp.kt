package com.loanwalle.loanwallecollection.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.todaylead.Data
import com.loanwalle.loanwallecollection.ui.main.view.Activity.LoanDashboardActivity
import com.loanwalle.loanwallecollection.util.Constants
import de.hdodenhof.circleimageview.CircleImageView

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
        var mobileno: TextView = view.findViewById(R.id.mobileno)
        var address: TextView = view.findViewById(R.id.address)
        var loanno: TextView = view.findViewById(R.id.loanno)
        var lner: LinearLayout = view.findViewById(R.id.lner)
        var image_user: CircleImageView = view.findViewById(R.id.image_user)


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
            holder.mobileno.text = tasks.mobile
            holder.address.text = tasks.present_address_line1
            holder.loanno.text = "Loan No. "+tasks.loan_no
        Glide.with(context).load(tasks.file).error(R.drawable.userimage).into(holder.image_user)


        holder.lner.setOnClickListener {

            if (tasks.file.isNullOrEmpty())
            {
                val intent1 = Intent(context, LoanDashboardActivity::class.java)
                intent1.putExtra(Constants.USER_PIC,tasks.file)
                intent1.putExtra(Constants.USER_NAME,holder.lead_name.text.toString())
                intent1.putExtra(Constants.USER_LOAN_NUMBER,tasks.loan_no)
                intent1.putExtra(Constants.USER_LEAD_ID,tasks.lead_id)
                context.startActivity(intent1)
            }else
            {
                val intent1 = Intent(context, LoanDashboardActivity::class.java)
                intent1.putExtra(Constants.USER_PIC,tasks.file)
                context.startActivity(intent1)
            }

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
