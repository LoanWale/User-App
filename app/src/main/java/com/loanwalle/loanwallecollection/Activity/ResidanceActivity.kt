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