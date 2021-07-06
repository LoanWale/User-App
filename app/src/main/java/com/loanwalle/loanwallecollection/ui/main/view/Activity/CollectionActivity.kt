package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.LoanDetailsViewModal
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_loan.*

class CollectionActivity : AppCompatActivity() {

    lateinit var loanDetailsViewModal : LoanDetailsViewModal
    var sessionManegar = SessionManegar()
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setupViewModel()

        nil_payment.setBackground(getDrawable(R.drawable.part_pyment_drawable))
        part_payment.setTextColor(getColor(R.color.white))
        part_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)
        full_payment.setTextColor(getColor(R.color.black))
        full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
        imageView6.isVisible = true
        next_sch_date.isVisible = true

        part_payment.setOnClickListener{
            part_payment.background =resources.getDrawable(R.drawable.full_payment_drawable)
            part_payment.setTextColor(getColor(R.color.white))

            full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
            nil_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
            full_payment.setTextColor(getColor(R.color.black))
            nil_payment.setTextColor(getColor(R.color.black))

            imageView6.isVisible = true
            next_sch_date.isVisible = true
        }

        full_payment.setOnClickListener{
            part_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
            nil_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
            full_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)

            full_payment.setTextColor(getColor(R.color.white))
            nil_payment.setTextColor(getColor(R.color.black))
            part_payment.setTextColor(getColor(R.color.black))

            next_sch_date.isVisible = false
            imageView6.isVisible = false



        }

        nil_payment.setOnClickListener{
            nil_payment.setTextColor(getColor(R.color.white))
            nil_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)

            full_payment.setTextColor(getColor(R.color.black))
            full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)

            part_payment.setTextColor(getColor(R.color.black))
            part_payment.background = getDrawable(R.drawable.part_pyment_drawable)

            next_sch_date.isVisible = true
            imageView6.isVisible = true
        }

    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        loanDetailsViewModal = ViewModelProvider(this, factory).get(LoanDetailsViewModal::class.java)
        getloandetails()
    }
    fun getloandetails() {
        var user = sessionManegar.getString(this,"userid")

        val userid = user
        if (userid!=null) {
            val body = LoanDetailsReq(
                "2","2457","2",userid)
            loanDetailsViewModal.userProfile(body)
            Log.e("BODY",body.toString())
            loanDetailsViewModal.userProfileResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message:String= otpResponse.message
                                Log.e("Resopncelogin7",message)
                                if (message.equals("success")&&otpResponse.status.equals("200"))
                                {
                                    Log.e("Resopncelogin5",otpResponse.data.loan_no)
                                     text_balnce.text = "Total Blance "+ otpResponse.data.payable_amount.toString()
                                    val bal = otpResponse.data.payable_amount.toString()
                                    prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                    var amount = editText6.text.toString()
                                    if (amount >= bal){
                                        part_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
                                        nil_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
                                        full_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)

                                        full_payment.setTextColor(getColor(R.color.white))
                                        nil_payment.setTextColor(getColor(R.color.black))
                                        part_payment.setTextColor(getColor(R.color.black))

                                        next_sch_date.isVisible = false
                                        imageView6.isVisible = false
                                    }else if (amount < bal){
                                        part_payment.background =resources.getDrawable(R.drawable.full_payment_drawable)
                                        part_payment.setTextColor(getColor(R.color.white))

                                        full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
                                        nil_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
                                        full_payment.setTextColor(getColor(R.color.black))
                                        nil_payment.setTextColor(getColor(R.color.black))

                                        imageView6.isVisible = true
                                        next_sch_date.isVisible = true
                                    }else if (amount.equals(0)){
                                        nil_payment.setTextColor(getColor(R.color.white))
                                        nil_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)

                                        full_payment.setTextColor(getColor(R.color.black))
                                        full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)

                                        part_payment.setTextColor(getColor(R.color.black))
                                        part_payment.background = getDrawable(R.drawable.part_pyment_drawable)

                                        next_sch_date.isVisible = true
                                        imageView6.isVisible = true
                                    }
                                }
                                else
                                {
                                    prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                prog_ress.errorSnack(message, Snackbar.LENGTH_LONG)
                                Log.e("Resopncelogin6",message);
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
        prog_ress.visibility = View.GONE
    }
    fun showProgressBar() {
        prog_ress.visibility = View.VISIBLE
    }
}