package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.totalLead.TotalLeadRequest
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityTotalLeadsBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.adapter.Total_Lead_ADP
import com.loanwalle.loanwallecollection.ui.main.viewmodel.TotalLeadViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.activity_total_leads.*
import kotlinx.android.synthetic.main.activity_total_leads.progress

class Total_Leads : AppCompatActivity() {

    var binding:ActivityTotalLeadsBinding? = null
    private lateinit var viewModel: TotalLeadViewModel
    lateinit var picsAdapter: Total_Lead_ADP
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTotalLeadsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }

    private fun init() {
        rvPics.setHasFixedSize(true)
        rvPics.layoutManager = LinearLayoutManager(this)
        picsAdapter = Total_Lead_ADP()
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(TotalLeadViewModel::class.java)
        getPictures()
    }



    fun getPictures() {
        val userid = "5"
        if (userid!=null) {
            val body = TotalLeadRequest.LeadRequest(userid)
            viewModel.totalLeads(body)
            viewModel.leadResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin",message);
                                picsAdapter.differ.submitList(otpResponse.data)
                                rvPics.adapter = picsAdapter
//                                if (message.equals("OTP sent Successfully")&&otpResponse.user_id != null)
//                                {
//                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)
//
//                                }
//                                else
//
//                                {
//                                    progress.errorSnack(message, Snackbar.LENGTH_LONG)
//                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress.errorSnack(message, Snackbar.LENGTH_LONG)
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


    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }


    fun onProgressClick(view: View) {
        //Preventing Click during loading
    }
}