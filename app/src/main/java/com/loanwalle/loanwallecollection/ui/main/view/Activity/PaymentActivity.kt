package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.checkpayment.CheckPaymentR_Request
import com.loanwalle.loanwallecollection.data.model.submitPayment.SubmitPaymentRequest
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityPaymentBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.adapter.ChcekPayment_ADP
import com.loanwalle.loanwallecollection.ui.main.viewmodel.CheckPaymentViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.SubmitPaymentViewModel
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.SessionManegar
import com.loanwalle.loanwallecollection.utils.errorSnack
import com.loanwalle.loanwallecollection.utils.toast
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_layout.*


class PaymentActivity : AppCompatActivity() {
    var bindig: ActivityPaymentBinding? = null
    var paymentmode: String = ""
    var paymentRecivedfrom: String = ""
    lateinit var viewUpdatePaymetn: CheckPaymentViewModel
    lateinit var submitPaymentViewModel: SubmitPaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(bindig!!.root)

        init()
        paymentmode = "1"
        paymentRecivedfrom = "Website"
        checkpayment_btn.isVisible = true
        uploaddoc.isVisible = false


        val amount: String? = SessionManegar().getString(this, Constants.REQUESTED_AMOUNT)
        total_payble.setText(amount)
        paymenthistory_lnr.isVisible = false
        paymentmode_lner.isVisible = true


        back_layout_pay.setOnClickListener {
            paymenthistory_lnr.isVisible = false
            paymentmode_lner.isVisible = true
        }

        back_layout_coll.setOnClickListener {

            onBackPressed()
        }

        Ra_web.setBackground(getDrawable(R.drawable.upi_id_drawable))
        Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))

        radiogrupuuu.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.Ra_online) {

                Ra_web.visibility = View.VISIBLE
                Ra_qrcode.visibility = View.VISIBLE
                //  Ra_upi.visibility = View.VISIBLE
                Ra_Cash.visibility = View.GONE
                paymentmode = "1"
                uploaddoc.isVisible = false

                //do work when radioButton1 is active
            } else if (checkedId == R.id.Ra_offline) {
                //do work when radioButton2 is active
                Ra_web.visibility = View.GONE
                Ra_qrcode.visibility = View.GONE
                //  Ra_upi.visibility = View.GONE
                Ra_Cash.visibility = View.VISIBLE
                paymentmode = "2"
                paymentRecivedfrom = "Cash"
                paymenthistory_lnr.isVisible = false
                checkpayment_btn.isVisible = false
                uploaddoc.isVisible = false

            }
        })


        checkpayment_btn.setOnClickListener {
            CheckPaymentStatus()

        }

        reinitilize.setOnClickListener {
            SessionManegar().remove(this, Constants.COLLECTION_RUNNING)
            SessionManegar().remove(this, Constants.REQUESTED_AMOUNT)
            val int = Intent(this, CollectionActivity::class.java)
            startActivity(int)
            finish()

        }





        submitpage.setOnClickListener {


            if (paymentmode.isNullOrEmpty()) {
                toast("Please Select Payment Mode")

            } else if (paymentRecivedfrom.isEmpty()) {
                toast("Please Select Payement source")

            } else if (total_payble.text.toString().isEmpty()) {
                toast("Please Enter Amount")
            } else {
                setupViewModel()
                SubmitPage()

            }


        }


        Ra_web.setOnClickListener {
            Ra_web.setBackground(getDrawable(R.drawable.upi_id_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
            //   Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))
            paymentRecivedfrom = "Website"
            // paymenthistory_lnr.isVisible=true
            checkpayment_btn.isVisible = true
            uploaddoc.isVisible = false


        }

        Ra_qrcode.setOnClickListener {
            Ra_web.setBackground(getDrawable(R.drawable.qr_code_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.upi_id_drawable))
            //  Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))
            paymentRecivedfrom = "QR CODE"
            val intt = Intent(this, QrCodeActivity::class.java)
            startActivity(intt)
            paymenthistory_lnr.isVisible = false
            checkpayment_btn.isVisible = false
            uploaddoc.isVisible = true



        }


//        Ra_upi.setOnClickListener{
//            Ra_web.setBackground(getDrawable(R.drawable.qr_code_drawable))
//            Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
//            Ra_upi.setBackground(getDrawable(R.drawable.upi_id_drawable))
//        }


    }


    private fun init() {
        rec_checkpayment.setHasFixedSize(true)
        rec_checkpayment.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewUpdatePaymetn = ViewModelProvider(this, factory).get(CheckPaymentViewModel::class.java)
        submitPaymentViewModel =
            ViewModelProvider(this, factory).get(SubmitPaymentViewModel::class.java)

    }

    @SuppressLint("ResourceAsColor")
    fun CheckPaymentStatus() {
        val user = "44"// sessionManegar.getString(this, Constants.USER_ID)
        val userid = user
        if (userid != null) {
            val body = CheckPaymentR_Request(
                "55546"
            )
            viewUpdatePaymetn.loginUser(body)
            Log.e("BODY", body.toString())
            viewUpdatePaymetn.loginResponse.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message: String = otpResponse.message
                                Log.e("Checkpayment", otpResponse.toString())
                                if (otpResponse.status.equals("200")) {


                                    if (otpResponse.data.size == 0) {
                                        //paymenthistory_lnr.isVisible = false
                                        // paymentmode_lner.isVisible = true

                                        toast("No Payment History found ")

                                    } else {
                                        //  paymenthistory_lnr.isVisible = true
                                        //  paymentmode_lner.isVisible = false
                                        checkpayment_btn.setText("Payment Successfully Done")
                                        paymenthistory_lnr.isVisible = true
                                        checkpayment_btn.isVisible = true
                                        check_loanno.text = otpResponse.data[0].loan_no
                                        His_visit_date.text = otpResponse.data[0].date_of_recived
                                        HS_paid_Am.setText( " \u20B9 "+otpResponse.data[0].payment_amount.replace(".00",""))
                                        His_payment_type.text = otpResponse.data[0].payment_mode
                                        Status.text = otpResponse.data[0].status
                                        ref.text = otpResponse.data[0].refrence_no


                                    }


//                                    val status = otpResponse!!.data
//                                    val picsAdapter = status?.let {
//                                        ChcekPayment_ADP(this@PaymentActivity, it)
//                                    }
//                                    //progress4.errorSnack(message, Snackbar.LENGTH_LONG)
//                                    rec_checkpayment.adapter = picsAdapter

                                } else {
                                    Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
                                Log.e("Resopncelogin6", message);
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

    fun SubmitPage() {
        val followup_id = SessionManegar().getString(this, Constants.USER_Follup_id)
        val payment_method = Ra_web.text.toString()
        val payment_mode = Ra_online.text.toString()
        val recieved_amount =
            total_payble.text.toString()// sessionManegar.getString(this, Constants.USER_ID)

        if (followup_id != null && payment_method != null && payment_mode != null && recieved_amount != null) {
            val body = SubmitPaymentRequest(
                followup_id, payment_method, payment_mode, recieved_amount
            )
            submitPaymentViewModel.paymentSub(body)
            Log.e("BODY", body.toString())
            submitPaymentViewModel.submitpay.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()
                            response.data?.let { otpResponse ->
                                val message: String = otpResponse.message
                                Log.e("Checkpayment", otpResponse.toString())
                                if (otpResponse.message.equals("Data updated Successfully") && otpResponse.status.equals(
                                        "200"
                                    )
                                ) {
                                    val intt = Intent(this, PaymentSuccessfullActivity::class.java)
                                    intt.putExtra(
                                        Constants.USER_Amount,
                                        total_payble.text.toString().trim()
                                    )
                                    startActivity(intt)
                                    finish()
                                    // final submit

                                } else {
                                    Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                Check_progr.errorSnack(message, Snackbar.LENGTH_LONG)
                                Log.e("Resopncelogin6", message);
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
        Check_progr.visibility = View.GONE
    }

    fun showProgressBar() {
        Check_progr.visibility = View.VISIBLE
    }

}