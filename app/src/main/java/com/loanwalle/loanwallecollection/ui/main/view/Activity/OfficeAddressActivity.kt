package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_office_address.*

class OfficeAddressActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_office_address)

        name_other.isVisible = false
        cst_mr.background = getDrawable(R.drawable.full_payment_drawable)
        cst_mr.setTextColor(R.color.white)
        mt_other.background = getDrawable(R.drawable.part_pyment_drawable)
        mt_other.setTextColor(R.color.black)
        back_office.setOnClickListener{
            onBackPressed()
        }
        cst_mr.setOnClickListener {
            cst_mr.background = getDrawable(R.drawable.full_payment_drawable)
            cst_mr.setTextColor(R.color.white)
            mt_other.background = getDrawable(R.drawable.part_pyment_drawable)
            mt_other.setTextColor(R.color.black)
            name_other.isVisible = false
        }






        mt_other.setOnClickListener {
            mt_other.background = getDrawable(R.drawable.full_payment_drawable)
            mt_other.setTextColor(R.color.white)
            cst_mr.background = getDrawable(R.drawable.part_pyment_drawable)
            cst_mr.setTextColor(R.color.black)
            val dilog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.other_family_layout,null)
            dilog.setCancelable(true)
            name_other.isVisible = true

            cst_mr.setOnClickListener {
                cst_mr.background = getDrawable(R.drawable.full_payment_drawable)
                cst_mr.setTextColor(R.color.white)
                mt_other.background = getDrawable(R.drawable.part_pyment_drawable)
                mt_other.setTextColor(R.color.black)
                dilog.dismiss()
                name_other.isVisible = false

            }


            val collo = view.findViewById<TextView>(R.id.spous)
            val spous = view.findViewById<TextView>(R.id.parent)
            val admin = view.findViewById<TextView>(R.id.parent_in_law)
            val gaurd = view.findViewById<TextView>(R.id.doughter)
            val recption = view.findViewById<TextView>(R.id.son)
            collo.setBackgroundColor(getColor(R.color.button_color))
            collo.setTextColor(R.color.white)

            collo.setOnClickListener {
                collo.setBackgroundColor(getColor(R.color.button_color))
                collo.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                admin.setBackgroundColor(getColor(R.color.white))
                admin.setTextColor(getColor(R.color.black))
                gaurd.setBackgroundColor(getColor(R.color.white))
                gaurd.setTextColor(getColor(R.color.black))
                recption.setBackgroundColor(getColor(R.color.white))
                recption.setTextColor(getColor(R.color.black))
                name_other.isVisible = true
            }
            spous.setOnClickListener {
                spous.setBackgroundColor(getColor(R.color.button_color))
                spous.setTextColor(getColor(R.color.white))
                collo.setBackgroundColor(getColor(R.color.white))
                collo.setTextColor(getColor(R.color.black))
                admin.setBackgroundColor(getColor(R.color.white))
                admin.setTextColor(getColor(R.color.black))
                gaurd.setBackgroundColor(getColor(R.color.white))
                gaurd.setTextColor(getColor(R.color.black))
                recption.setBackgroundColor(getColor(R.color.white))
                recption.setTextColor(getColor(R.color.black))
                name_other.isVisible = true
            }

            admin.setOnClickListener {
                admin.setBackgroundColor(getColor(R.color.button_color))
                admin.setTextColor(getColor(R.color.white))
                collo.setBackgroundColor(getColor(R.color.white))
                collo.setTextColor(getColor(R.color.black))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                gaurd.setBackgroundColor(getColor(R.color.white))
                gaurd.setTextColor(getColor(R.color.black))
                recption.setBackgroundColor(getColor(R.color.white))
                recption.setTextColor(getColor(R.color.black))
                name_other.isVisible = true
            }
            gaurd.setOnClickListener {
                gaurd.setBackgroundColor(getColor(R.color.button_color))
                gaurd.setTextColor(getColor(R.color.white))
                collo.setBackgroundColor(getColor(R.color.white))
                collo.setTextColor(getColor(R.color.black))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                admin.setBackgroundColor(getColor(R.color.white))
                admin.setTextColor(getColor(R.color.black))
                recption.setBackgroundColor(getColor(R.color.white))
                recption.setTextColor(getColor(R.color.black))
                name_other.isVisible = true
            }
            recption.setOnClickListener {
                recption.setBackgroundColor(getColor(R.color.button_color))
                recption.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                admin.setBackgroundColor(getColor(R.color.white))
                admin.setTextColor(getColor(R.color.black))
                gaurd.setBackgroundColor(getColor(R.color.white))
                gaurd.setTextColor(getColor(R.color.black))
                collo.setBackgroundColor(getColor(R.color.white))
                collo.setTextColor(getColor(R.color.black))
                name_other.isVisible = true
            }


            // on below line we are setting
            // content view to our view.
            dilog.setContentView(view)


            // on below line we are calling
            // a show method to display a dialog.
            dilog.show()
        }

        staff_spiner.onItemSelectedListener
        employes_sp.onItemSelectedListener

        var list_of_staff = arrayOf("SELECT","1-5","6-10","11-20",">20")
        var list_of_employes = arrayOf("SELECT","1-5","6-10","11-20","20-50","50-100",">100")

        staff_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        employes_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        staff_spiner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list_of_staff)
        employes_sp.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list_of_staff)
    }
}