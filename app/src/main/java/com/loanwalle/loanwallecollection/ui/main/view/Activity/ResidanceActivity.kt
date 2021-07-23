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
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.GetFollowupViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UpdateCollectionViewModel
import kotlinx.android.synthetic.main.activity_residance.*


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
//        customer_select_family_residance_house33.setOnClickListener {
//            customer_select_family_residance_house33.background = getDrawable(R.drawable.full_payment_drawable)
//            other_met_other_residance_house33.background = getDrawable(R.drawable.part_pyment_drawable)
//
//            customer_select_family_residance_house33.setTextColor(R.color.white)
//            other_met_other_residance_house33.setTextColor(R.color.black)
//
//        }
//        other_met_other_residance_house33.setOnClickListener {
//            customer_select_family_residance_house33.background = getDrawable(R.drawable.part_pyment_drawable)
//            other_met_other_residance_house33.background = getDrawable(R.drawable.full_payment_drawable)
//
//            customer_select_family_residance_house33.setTextColor(R.color.black)
//            other_met_other_residance_house33.setTextColor(R.color.white)
//        }
//
//        cllnddr.setOnClickListener {
//            val c = Calendar.getInstance()
//            val year = c[Calendar.YEAR]
//            val month = c[Calendar.MONTH]
//            val day = c[Calendar.DAY_OF_MONTH]
//
//
//            val dpd = DatePickerDialog(
//                this,
//                { view, year, monthOfYear, dayOfMonth -> // Display Selected date in textbox
//                    clndr_text__.setText("" + dayOfMonth + "- " + MONTHS.id + "- " + year)
//                }, year, month, day
//            )
//            dpd.show()
//        }
//
//        plus.setOnClickListener {
//            increaseInteger()
//        }
//        minus.setOnClickListener {
//            decreceInteger()
//        }

        init1()
//        verifyClick1()

        back_residance.setOnClickListener{
            onBackPressed()
        }


    }

//    private fun decreceInteger() {
//
//        display(plusminus.text.toString().toInt() + 1)
//    }
//
//    private fun increaseInteger() {
//
//        display(plusminus.text.toString().toInt() - 1)
//    }
//
//    private fun display(number: Int) {
//        plusminus.setText(number)
//    }

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



    fun hideProgressBar() {
        ppprrrooggess.visibility = View.GONE
    }
    fun showProgressBar() {
        ppprrrooggess.visibility = View.VISIBLE
    }
    
    }



