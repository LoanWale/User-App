package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.convenc.ConvenReque
import com.loanwalle.loanwallecollection.data.model.todaylead.TodayleadRequ
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityCovenceReportBinding
import com.loanwalle.loanwallecollection.databinding.ActivityHomePageBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.adapter.Convenc_ADP
import com.loanwalle.loanwallecollection.ui.main.adapter.TodayLeadAdp
import com.loanwalle.loanwallecollection.ui.main.viewmodel.ConcenceViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.TodayLeadViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UserProfileViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_covence_report.*
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_home_page.rec_todaycollection
import kotlinx.android.synthetic.main.activity_previous_payment.*
import java.text.SimpleDateFormat
import java.util.*


class Covence_Report : AppCompatActivity() {


    var binding: ActivityCovenceReportBinding? = null
    private lateinit var viewModel: ConcenceViewModel
    var currentDateandTime=""
    val sessionManegar = SessionManegar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covence_report)
        binding = ActivityCovenceReportBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
        back_layout_conv.setOnClickListener{
            onBackPressed()
        }

        val cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "yyyy-MM-dd" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            fromdate.text = ""+sdf.format(cal.time)

        }
        val dateSetListener_to = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "yyyy-MM-dd" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            text_todate.text = ""+sdf.format(cal.time)

        }



        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
         currentDateandTime = sdf.format(Date())
        text_todate.setText(currentDateandTime)
        fromdate.setText(currentDateandTime)

        datefrom_btn.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }


        from_date_btn.setOnClickListener {
            DatePickerDialog(this, dateSetListener_to,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }


        
    }

    private fun init() {
        convey_recycler.setHasFixedSize(true)
        convey_recycler.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(ConcenceViewModel::class.java)
        getConve()
    }



    fun getConve() {
        val userid = sessionManegar.getString(this,"userid")
        if (userid!=null) {
            val body = ConvenReque("2","2021-06-30","2","2021-07-07","44")
            viewModel.todaylead(body)
            viewModel.leadResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse!!.message
                                Log.e("Resopncelogin",otpResponse.toString());
                                val status = otpResponse!!.data
                                val picsAdapter = status?.let {
                                    Convenc_ADP(this@Covence_Report, it)
                                }
                                //progresst6.errorSnack(message, Snackbar.LENGTH_LONG)
                                convey_recycler.adapter = picsAdapter

                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progresst6.errorSnack(message, Snackbar.LENGTH_LONG)
                            }
                        }

                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                }
            })
        }

    }

    fun hideProgressBar() {
        progresst6.visibility = View.GONE
    }
    fun showProgressBar() {
        progresst6.visibility = View.VISIBLE
    }

}