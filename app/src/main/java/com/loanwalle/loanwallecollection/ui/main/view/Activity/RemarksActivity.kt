package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_remarks.*
import java.util.*

class RemarksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remarks)

        payment_spiner.onItemSelectedListener
        part_payment_spiner.onItemSelectedListener

        var payment_list = arrayOf("SELECT PAYMENT TYPE","CASH","BANK TRANSFER","CHEQUE")
        var part_payment_list = arrayOf("SELECT PAYMENT","FULL PAYMENT","PART PAYMENT","NO PAYMENT")


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        clndr_pyment_layout.setOnClickListener{
            val dpd =
                    DatePickerDialog(this,
                            DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->


                                clndr_text_pyment.setText(""+dayOfMonth +"/"+ monthOfYear +"/"+ year)
                            },year,month,day)

            dpd.show()
        }


        payment_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long)
            {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        part_payment_spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                if (position==2 || position ==3){
                    clndr_pyment_layout.isVisible = true
                }else{
                    clndr_pyment_layout.isVisible = false
                    clndr_text_pyment.setText(" ")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        payment_spiner.adapter =  ArrayAdapter(this,android.R.layout.simple_spinner_item,payment_list)
        part_payment_spiner.adapter =  ArrayAdapter(this,android.R.layout.simple_spinner_item,part_payment_list)

    }
}