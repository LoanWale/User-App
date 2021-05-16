package com.loanwalle.loanwallecollection.Activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_residance.*
import java.util.*

class ResidanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residance)


        spiner5.onItemSelectedListener
        spiner7!!.onItemSelectedListener
        owen_sp!!.onItemSelectedListener
        flat_sp!!.onItemSelectedListener
        easy_sp!!.onItemSelectedListener
        locality_spiner_sp!!.onItemSelectedListener
        total_mamber_spiner_sp!!.onItemSelectedListener
        earning_spiner_sp!!.onItemSelectedListener
        living_spiner_sp!!.onItemSelectedListener
        neighbr_spiner_sp!!.onItemSelectedListener
        status_spiner_sp!!.onItemSelectedListener

        var list_of_item = arrayOf("SELECT","CUSTOMER","OTHER")
        var list_of_relation = arrayOf("SELECT","SELF(DEFAULT IF CUSTOMER)","SPOUSE","PARENT","PARENT IN LAW","DAUGHTER","SON","BROTHER",
            "SISTER","NEIGHBOUR","LANDLORD","RELATIVE")
        var own_list = arrayOf("SELECT","OWNED","FAMILY OWNED","RENTED","SHARED")
        var flat_list = arrayOf("SELECT","FLAT","FLOOR","ROW-HOUSE","CHHAWL")
        var easy_list = arrayOf("SELECT","LOW","MODERATE","HIGH")
        var locality_list = arrayOf("SELECT","RURAL","URBAN")
        var mamber_list = arrayOf("SELECT","1","2","3","4","5",">5")
        var earning_list = arrayOf("SELECT","1","2","3","4","5",">5")
        var living_list = arrayOf("SELECT","LOW","MEDIUM","HIGH")
        var neigbhr_list = arrayOf("SELECT","POSITIVE","NEGATIVE")
        var status_list = arrayOf("SELECT","POSITIVE","NEGATIVE","REFERRED")

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        sincetext_layout!!.setOnClickListener{
            val dpd =
                DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->


                    clnder_textt!!.setText(""+dayOfMonth +"/"+ monthOfYear +"/"+ year)
                    clnder2_text.isVisible = false
                    clnder_textt.isVisible = true

                },year,month,day)

            dpd.show()
        }




        spiner5.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==2){
                    cstmer_layout2.isVisible = true
                    cstmer_layout3.isVisible = true
                }
                else {
                    cstmer_layout2.isVisible = false
                    cstmer_layout3.isVisible = false
                }
            }
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



        flat_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        easy_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        locality_spiner_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        total_mamber_spiner_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        earning_spiner_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        living_spiner_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        neighbr_spiner_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        status_spiner_sp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }




        spiner5!!.adapter =  ArrayAdapter(this,android.R.layout.simple_spinner_item,list_of_item)

        spiner7!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list_of_relation)
        owen_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,own_list)
        flat_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,flat_list)
        easy_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,easy_list)
        locality_spiner_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,locality_list)
        total_mamber_spiner_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,mamber_list)
        earning_spiner_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,earning_list)
        living_spiner_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,living_list)
        neighbr_spiner_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,neigbhr_list)
        status_spiner_sp!!.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,status_list)

    }
}