package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.irContact.Ir_Request
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityIRABinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.IRViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoanDetailsViewModal
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.ConstantsSave
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_i_r_a.*
import kotlinx.android.synthetic.main.activity_login.*

class IRAActivity : AppCompatActivity() {
    var binding: ActivityIRABinding? = null
   var IrViewModal: IRViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityIRABinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupViewModel()
        back_layout_docs.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        IrViewModal = ViewModelProvider(this, factory).get(IRViewModel::class.java)
        getIrContact()
    }

    fun getIrContact() {
        val usrid : String? =  "44"//SessionManegar().getString(this,"userid")
      //  val userid= Constants.USER_ID
        usrid?.let { Log.e("useridircont", it) }
        if (usrid != null) {
            if (usrid.isNotEmpty() && usrid.isNotEmpty()) {
                val body = Ir_Request(usrid)
                IrViewModal?.loginUser(body)
                IrViewModal?.loginResponse?.observe(this, Observer { event ->
                    event.getContentIfNotHandled()?.let { response ->
                        when (response) {
                            is Resource.Success -> {
                                hideProgressBar()
                                response.data?.let { loginResponse ->
                                    val message:String= loginResponse.message
                                    Log.e("IRContact",message);
                                    if (message.equals("success"))
                                    {
                                        ircontactName.text=loginResponse.data.name
                                        ir_email.text=loginResponse.data.email
                                        ir_mobile.text=loginResponse.data.mobile
                                        val picurl = loginResponse.data.profile_pic
                                        Glide.with(this).load(picurl).into(circularPhoto)

                                       // ir_progress.errorSnack(message, Snackbar.LENGTH_LONG)

                                    }else

                                    {
                                        ir_progress.errorSnack(message, Snackbar.LENGTH_LONG)
                                    }


                                }
                            }

                            is Resource.Error -> {
                                hideProgressBar()
                                response.message?.let { message ->
                                    ir_progress.errorSnack(message, Snackbar.LENGTH_LONG)
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
    }

    private fun hideProgressBar() {
        ir_progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        ir_progress.visibility = View.VISIBLE
    }
}