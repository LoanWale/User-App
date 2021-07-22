package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.model.getFollowupCollection.GetFollowupRequest
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.GetFollowupViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UpdateCollectionViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_residance.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class ResidanceActivity : AppCompatActivity() {
    lateinit var getFollowupViewModel: GetFollowupViewModel
    lateinit var updateCollectionViewModel: UpdateCollectionViewModel
    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_residance)
        customer_select.background = getDrawable(R.drawable.full_payment_drawable)
        other_met.background = getDrawable(R.drawable.part_pyment_drawable)

        customer_select_family.background = getDrawable(R.drawable.full_payment_drawable)
        other_met_other.background = getDrawable(R.drawable.part_pyment_drawable)

        customer_select_family.setTextColor(R.color.white)
        other_met_other.setTextColor(R.color.black)

        customer_select.setTextColor(R.color.white)
        other_met.setTextColor(R.color.black)
        customer_select.setOnClickListener {
            customer_select.background = getDrawable(R.drawable.full_payment_drawable)
            other_met.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select.setTextColor(R.color.white)
            other_met.setTextColor(R.color.black)
        }

        other_met.setOnClickListener {
            customer_select.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met.background = getDrawable(R.drawable.full_payment_drawable)

            customer_select.setTextColor(R.color.black)
            other_met.setTextColor(R.color.white)
        }

        customer_select_family.setOnClickListener {
            customer_select_family.background = getDrawable(R.drawable.full_payment_drawable)
            other_met_other.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select_family.setTextColor(R.color.white)
            other_met_other.setTextColor(R.color.black)
        }

        other_met_other.setOnClickListener {
            customer_select_family.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met_other.background = getDrawable(R.drawable.full_payment_drawable)

            customer_select_family.setTextColor(R.color.black)
            other_met_other.setTextColor(R.color.white)
        }
        customer_select_family_residance.setOnClickListener {
            customer_select_family_residance.background = getDrawable(R.drawable.full_payment_drawable)
            other_met_other_residance.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select_family_residance.setTextColor(R.color.white)
            other_met_other_residance.setTextColor(R.color.black)
        }
        other_met_other_residance.setOnClickListener {
            customer_select_family_residance.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met_other_residance.background = getDrawable(R.drawable.full_payment_drawable)

            customer_select_family_residance.setTextColor(R.color.black)
            other_met_other_residance.setTextColor(R.color.white)
        }
        customer_select_family_residance_house.setOnClickListener {
            customer_select_family_residance_house.background = getDrawable(R.drawable.full_payment_drawable)
            other_met_other_residance_house.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select_family_residance_house.setTextColor(R.color.white)
            other_met_other_residance_house.setTextColor(R.color.black)

        }

        other_met_other_residance_house.setOnClickListener {
            customer_select_family_residance_house.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met_other_residance_house.background = getDrawable(R.drawable.full_payment_drawable)

            customer_select_family_residance_house.setTextColor(R.color.black)
            other_met_other_residance_house.setTextColor(R.color.white)
        }

        customer_identity.setOnClickListener {
            customer_identity.background = getDrawable(R.drawable.full_payment_drawable)
            other_met_identity.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_identity.setTextColor(R.color.white)
            other_met_identity.setTextColor(R.color.black)
        }

        other_met_identity.setOnClickListener {
            customer_identity.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met_identity.background = getDrawable(R.drawable.full_payment_drawable)

            customer_identity.setTextColor(R.color.black)
            other_met_identity.setTextColor(R.color.white)
        }
        customer_select_family_residance_house33.setOnClickListener {
            customer_select_family_residance_house33.background = getDrawable(R.drawable.full_payment_drawable)
            other_met_other_residance_house33.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select_family_residance_house33.setTextColor(R.color.white)
            other_met_other_residance_house33.setTextColor(R.color.black)

        }
        other_met_other_residance_house33.setOnClickListener {
            customer_select_family_residance_house33.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met_other_residance_house33.background = getDrawable(R.drawable.full_payment_drawable)

            customer_select_family_residance_house33.setTextColor(R.color.black)
            other_met_other_residance_house33.setTextColor(R.color.white)
        }

        cllnddr.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c[Calendar.YEAR]
            val month = c[Calendar.MONTH]
            val day = c[Calendar.DAY_OF_MONTH]


            val dpd = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth -> // Display Selected date in textbox
                    clndr_text__.setText("" + dayOfMonth + "- " + MONTHS.id + "- " + year)
                }, year, month, day
            )
            dpd.show()
        }

        plus.setOnClickListener {
            increaseInteger()
        }
        minus.setOnClickListener {
            decreceInteger()
        }

        init1()
//        verifyClick1()

        back_residance.setOnClickListener{
            onBackPressed()
        }


    }

    private fun decreceInteger() {

        display(plusminus.text.toString().toInt() + 1)
    }

    private fun increaseInteger() {

        display(plusminus.text.toString().toInt() - 1)
    }

    private fun display(number: Int) {
        plusminus.setText(number)
    }

    private fun init1() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        updateCollectionViewModel = ViewModelProvider(this, factory).get(UpdateCollectionViewModel::class.java)
    }
    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        getFollowupViewModel = ViewModelProvider(this, factory).get(GetFollowupViewModel::class.java)
    }
    fun verifyClick() {
        val followup_id = 1
        val lead_id = 2457
        val user_id = 44
        val company_id = 2
        val product_id = 2
        if (followup_id!= null && lead_id!= null && user_id!=null && company_id !=null && product_id !=null) {
            val body = GetFollowupRequest.GetFollowupRequest(
                followup_id ,
                company_id.toString(),
                lead_id.toString(),
                user_id,
                product_id

            )

            getFollowupViewModel.getCollec(body)

            getFollowupViewModel.getCollec.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()

                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success")&&verifyResponse.status.equals("200"))
                                {
                                    //ppprrrooggess.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                                else
                                {
                                    ppprrrooggess.errorSnack(message, Snackbar.LENGTH_LONG)
                                }
                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                ppprrrooggess.errorSnack(message, Snackbar.LENGTH_LONG)
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

//    fun verifyClick1() {
//        val followup_id = 1
//        val paid_amount = 2457
//        val followup_remark = ""
//        val followup_date = 2
//        val updated_by = 2
//        val total_distance = ""
//        val executive_ending_latitude = ""
//        val executive_ending_longitude = ""
//        val followup_ended_at = ""
//        if (followup_id!= null && paid_amount!= null &&
//            followup_remark!=null && followup_date !=null &&
//            updated_by !=null && total_distance !=null
//            &&executive_ending_latitude != null && executive_ending_longitude != null
//            && followup_ended_at !=null) {
//            val body = UpdateFollowupRequestBodies.UpdateFollowupRequest(
//                followup_id.toString() ,
//                paid_amount.toString(),
//                followup_remark,
//                followup_date.toString(),
//                updated_by.toString(),
//                total_distance,
//                executive_ending_latitude,
//                executive_ending_longitude,
//                followup_ended_at
//            )
//
//
//
//            updateCollectionViewModel.updateCollec(body)
//
//            updateCollectionViewModel.upDateCollec.observe(this, Observer { event ->
//                event.getContentIfNotHandled()?.let { response ->
//                    when (response) {
//                        is Resource.Success -> {
//                            hideProgressBar()
//
//
//                            response.data?.let { verifyResponse ->
//                                val message:String= verifyResponse.message
//                                Log.e("Resopncelogin",message);
//                                if (message.equals("Data updated Successfully")&&verifyResponse.status.equals("200"))
//                                {
//                                    ppprrrooggess.errorSnack(message, Snackbar.LENGTH_LONG)
//
//                                }
//                                else
//                                {
//                                    ppprrrooggess.errorSnack(message, Snackbar.LENGTH_LONG)
//                                }
//
//
//                            }
//                        }
//
//                        is Resource.Error -> {
//                            hideProgressBar()
//                            response.message?.let { message ->
//                                ppprrrooggess.errorSnack(message, Snackbar.LENGTH_LONG)
//                            }
//                        }
//
//                        is Resource.Loading -> {
//                            showProgressBar()
//                        }
//                    }
//                }
//            })
//        }
//
//    }

    fun hideProgressBar() {
        ppprrrooggess.visibility = View.GONE
    }
    fun showProgressBar() {
        ppprrrooggess.visibility = View.VISIBLE
    }
    
    }



