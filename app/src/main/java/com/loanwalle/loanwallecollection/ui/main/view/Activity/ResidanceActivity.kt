package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.GetFollowupViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.UpdateCollectionViewModel
import kotlinx.android.synthetic.main.activity_office_address.*
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

        cs_family.isVisible = false
        customer_select_family.background = getDrawable(R.drawable.full_payment_drawable)
        other_met_other.background = getDrawable(R.drawable.part_pyment_drawable)

        customer_select_family.setTextColor(R.color.white)
        other_met_other.setTextColor(R.color.black)

        name_other___enter.isVisible = false

        customer_select.setTextColor(R.color.white)
        other_met.setTextColor(R.color.black)
        customer_select.setOnClickListener {
            cs_family.isVisible = false
            customer_select.background = getDrawable(R.drawable.full_payment_drawable)
            other_met.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select.setTextColor(R.color.white)
            other_met.setTextColor(R.color.black)
            name_other___enter.isVisible = false
        }

        other_met.setOnClickListener {
            customer_select.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met.background = getDrawable(R.drawable.full_payment_drawable)
            cs_family.isVisible = true
            name_other___enter.isVisible = false
            customer_select_family.setTextColor(R.color.black)
            customer_select_family.background = getDrawable(R.drawable.part_pyment_drawable)

            other_met_other.setTextColor(R.color.black)
            other_met_other.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select.setTextColor(R.color.black)
            other_met.setTextColor(R.color.white)
        }

        customer_select_family.setOnClickListener {
            customer_select_family.background = getDrawable(R.drawable.full_payment_drawable)
            other_met_other.background = getDrawable(R.drawable.part_pyment_drawable)

            customer_select_family.setTextColor(R.color.white)
            other_met_other.setTextColor(R.color.black)
            name_other___enter.isVisible = true

            val dilog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.residance_other_layout,null)
            dilog.setCancelable(true)

            val spous = view.findViewById<TextView>(R.id.spous)
            val parent = view.findViewById<TextView>(R.id.parent)
            val in_law = view.findViewById<TextView>(R.id.parent_in_law)
            val doughter = view.findViewById<TextView>(R.id.doughter)
            val son = view.findViewById<TextView>(R.id.son)
            val bro = view.findViewById<TextView>(R.id.brother)
            val sis = view.findViewById<TextView>(R.id.sister)

            spous.setBackgroundColor(getColor(R.color.button_color))
            spous.setTextColor(getColor(R.color.white))
            parent.setBackgroundColor(getColor(R.color.white))
            parent.setTextColor(getColor(R.color.black))
            in_law.setBackgroundColor(getColor(R.color.white))
            in_law.setTextColor(getColor(R.color.black))
            doughter.setBackgroundColor(getColor(R.color.white))
            doughter.setTextColor(getColor(R.color.black))
            son.setBackgroundColor(getColor(R.color.white))
            son.setTextColor(getColor(R.color.black))
            bro.setBackgroundColor(getColor(R.color.white))
            bro.setTextColor(getColor(R.color.black))
            sis.setBackgroundColor(getColor(R.color.white))
            sis.setTextColor(getColor(R.color.black))

            spous.setOnClickListener {
                spous.setBackgroundColor(getColor(R.color.button_color))
                spous.setTextColor(getColor(R.color.white))
                parent.setBackgroundColor(getColor(R.color.white))
                parent.setTextColor(getColor(R.color.black))
                in_law.setBackgroundColor(getColor(R.color.white))
                in_law.setTextColor(getColor(R.color.black))
                doughter.setBackgroundColor(getColor(R.color.white))
                doughter.setTextColor(getColor(R.color.black))
                son.setBackgroundColor(getColor(R.color.white))
                son.setTextColor(getColor(R.color.black))
                bro.setBackgroundColor(getColor(R.color.white))
                bro.setTextColor(getColor(R.color.black))
                sis.setBackgroundColor(getColor(R.color.white))
                sis.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true
            }

            in_law.setOnClickListener {
                in_law.setBackgroundColor(getColor(R.color.button_color))
                in_law.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                parent.setBackgroundColor(getColor(R.color.white))
                parent.setTextColor(getColor(R.color.black))
                doughter.setBackgroundColor(getColor(R.color.white))
                doughter.setTextColor(getColor(R.color.black))
                son.setBackgroundColor(getColor(R.color.white))
                son.setTextColor(getColor(R.color.black))
                bro.setBackgroundColor(getColor(R.color.white))
                bro.setTextColor(getColor(R.color.black))
                sis.setBackgroundColor(getColor(R.color.white))
                sis.setTextColor(getColor(R.color.black))
                 name_other___enter.isVisible = true
            }

            parent.setOnClickListener {
                parent.setBackgroundColor(getColor(R.color.button_color))
                parent.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                in_law.setBackgroundColor(getColor(R.color.white))
                in_law.setTextColor(getColor(R.color.black))
                doughter.setBackgroundColor(getColor(R.color.white))
                doughter.setTextColor(getColor(R.color.black))
                son.setBackgroundColor(getColor(R.color.white))
                son.setTextColor(getColor(R.color.black))
                bro.setBackgroundColor(getColor(R.color.white))
                bro.setTextColor(getColor(R.color.black))
                sis.setBackgroundColor(getColor(R.color.white))
                sis.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true
            }

            doughter.setOnClickListener {
                doughter.setBackgroundColor(getColor(R.color.button_color))
                doughter.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                in_law.setBackgroundColor(getColor(R.color.white))
                in_law.setTextColor(getColor(R.color.black))
                parent.setBackgroundColor(getColor(R.color.white))
                parent.setTextColor(getColor(R.color.black))
                son.setBackgroundColor(getColor(R.color.white))
                son.setTextColor(getColor(R.color.black))
                bro.setBackgroundColor(getColor(R.color.white))
                bro.setTextColor(getColor(R.color.black))
                sis.setBackgroundColor(getColor(R.color.white))
                sis.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true
            }

            son.setOnClickListener {
                son.setBackgroundColor(getColor(R.color.button_color))
                son.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                in_law.setBackgroundColor(getColor(R.color.white))
                in_law.setTextColor(getColor(R.color.black))
                parent.setBackgroundColor(getColor(R.color.white))
                parent.setTextColor(getColor(R.color.black))
                doughter.setBackgroundColor(getColor(R.color.white))
                doughter.setTextColor(getColor(R.color.black))
                bro.setBackgroundColor(getColor(R.color.white))
                bro.setTextColor(getColor(R.color.black))
                sis.setBackgroundColor(getColor(R.color.white))
                sis.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true
            }

            bro.setOnClickListener {
                bro.setBackgroundColor(getColor(R.color.button_color))
                bro.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                in_law.setBackgroundColor(getColor(R.color.white))
                in_law.setTextColor(getColor(R.color.black))
                parent.setBackgroundColor(getColor(R.color.white))
                parent.setTextColor(getColor(R.color.black))
                doughter.setBackgroundColor(getColor(R.color.white))
                doughter.setTextColor(getColor(R.color.black))
                son.setBackgroundColor(getColor(R.color.white))
                son.setTextColor(getColor(R.color.black))
                sis.setBackgroundColor(getColor(R.color.white))
                sis.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true
            }

            sis.setOnClickListener {
                sis.setBackgroundColor(getColor(R.color.button_color))
                sis.setTextColor(getColor(R.color.white))
                spous.setBackgroundColor(getColor(R.color.white))
                spous.setTextColor(getColor(R.color.black))
                in_law.setBackgroundColor(getColor(R.color.white))
                in_law.setTextColor(getColor(R.color.black))
                parent.setBackgroundColor(getColor(R.color.white))
                parent.setTextColor(getColor(R.color.black))
                doughter.setBackgroundColor(getColor(R.color.white))
                doughter.setTextColor(getColor(R.color.black))
                son.setBackgroundColor(getColor(R.color.white))
                son.setTextColor(getColor(R.color.black))
                bro.setBackgroundColor(getColor(R.color.white))
                bro.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true
            }



            // on below line we are setting
            // content view to our view.
            dilog.setContentView(view)


            // on below line we are calling
            // a show method to display a dialog.
            dilog.show()
        }

        other_met_other.setOnClickListener {
            customer_select_family.background = getDrawable(R.drawable.part_pyment_drawable)
            other_met_other.background = getDrawable(R.drawable.full_payment_drawable)

            customer_select_family.setTextColor(R.color.black)
            other_met_other.setTextColor(R.color.white)
            val dilog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.residance_family_layout,null)
            dilog.setCancelable(true)

            name_other___enter.isVisible = true

            val neighbr = view.findViewById<TextView>(R.id.neighbour)
            val landrod = view.findViewById<TextView>(R.id.parent)
            val relative = view.findViewById<TextView>(R.id.parent_in_law)

            neighbr.setBackgroundColor(getColor(R.color.button_color))
            neighbr.setTextColor(getColor(R.color.white))
            landrod.setBackgroundColor(getColor(R.color.white))
            landrod.setTextColor(getColor(R.color.black))
            relative.setBackgroundColor(getColor(R.color.white))
            relative.setTextColor(getColor(R.color.black))

            neighbr.setOnClickListener {
                neighbr.setBackgroundColor(getColor(R.color.button_color))
                neighbr.setTextColor(getColor(R.color.white))
                landrod.setBackgroundColor(getColor(R.color.white))
                landrod.setTextColor(getColor(R.color.black))
                relative.setBackgroundColor(getColor(R.color.white))
                relative.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true

            }

            landrod.setOnClickListener {
                landrod.setBackgroundColor(getColor(R.color.button_color))
                landrod.setTextColor(getColor(R.color.white))
                neighbr.setBackgroundColor(getColor(R.color.white))
                neighbr.setTextColor(getColor(R.color.black))
                relative.setBackgroundColor(getColor(R.color.white))
                relative.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true

            }

            relative.setOnClickListener {
                relative.setBackgroundColor(getColor(R.color.button_color))
                relative.setTextColor(getColor(R.color.white))
                neighbr.setBackgroundColor(getColor(R.color.white))
                neighbr.setTextColor(getColor(R.color.black))
                landrod.setBackgroundColor(getColor(R.color.white))
                landrod.setTextColor(getColor(R.color.black))
                name_other___enter.isVisible = true

            }

            // on below line we are setting
            // content view to our view.
            dilog.setContentView(view)


            // on below line we are calling
            // a show method to display a dialog.
            dilog.show()
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



