package com.loanwalle.loanwallecollection.Activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_office_address.*
import kotlinx.android.synthetic.main.activity_residance.*
import kotlinx.android.synthetic.main.activity_residance.employes_sp
import kotlinx.android.synthetic.main.activity_residance.staff_spiner
import java.util.*

class ResidanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residance)

        cstmer_layout2.isVisible = false
        cstmer_layout3.isVisible = false
        residance_layout.isVisible = false
        house_layout.isVisible = false
        customer_select.background = resources.getDrawable(R.drawable.full_payment_drawable)
        other_met.background = resources.getDrawable(R.drawable.part_pyment_drawable)
        customer_select.setTextColor(getColor(R.color.white))
        other_met.setTextColor(getColor(R.color.black))

        staff_spiner.onItemSelectedListener
        employes_sp.onItemSelectedListener
        spiner7.onItemSelectedListener
        owen_sp!!.onItemSelectedListener
        flat_sp!!.onItemSelectedListener


        var own_list = arrayOf("SELECT","OWNED","FAMILY OWNED","RENTED","SHARED")
        var list_of_relation = arrayOf("SELECT","SELF(DEFAULT IF CUSTOMER)","SPOUSE","PARENT","PARENT IN LAW","DAUGHTER","SON","BROTHER",
            "SISTER","NEIGHBOUR","LANDLORD","RELATIVE")
        var flat_list = arrayOf("SELECT","FLAT","FLOOR","ROW-HOUSE","CHHAWL")

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

        customer_select.setOnClickListener{
            customer_select.background = resources.getDrawable(R.drawable.full_payment_drawable)
            other_met.background = resources.getDrawable(R.drawable.part_pyment_drawable)

            customer_select.setTextColor(getColor(R.color.white))
            other_met.setTextColor(getColor(R.color.black))

            cstmer_layout2.isVisible = false
            cstmer_layout3.isVisible = false
            residance_layout.isVisible = false
            house_layout.isVisible = false
        }

        other_met.setOnClickListener{
            customer_select.background = resources.getDrawable(R.drawable.part_pyment_drawable)
            other_met.background = resources.getDrawable(R.drawable.full_payment_drawable)

            customer_select.setTextColor(getColor(R.color.black))
            other_met.setTextColor(getColor(R.color.white))

            cstmer_layout2.isVisible = true
            cstmer_layout3.isVisible = true
            residance_layout.isVisible = true
            house_layout.isVisible = true
        }

        spiner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        owen_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        flat_sp!!.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        flat_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,flat_list)
        owen_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,own_list)
        spiner7!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list_of_relation)
    }
}