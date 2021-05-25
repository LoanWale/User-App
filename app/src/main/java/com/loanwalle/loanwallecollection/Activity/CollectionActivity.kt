package com.loanwalle.loanwallecollection.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.loanwalle.loanwallecollection.Fragment.FullPaymentFragment
import com.loanwalle.loanwallecollection.Fragment.PartPaymentFragment
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.fragment_part_payment.*

class CollectionActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

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

//        var fragment = supportFragmentManager
//        var partPaymentFragment = PartPaymentFragment()
//        fragment.beginTransaction().replace(R.id.part_frame,partPaymentFragment).commit()
//        part_payment.background =resources.getDrawable(R.drawable.full_payment_drawable)
//        nil_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//        full_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//        part_payment.setTextColor(getColor(R.color.white))
//        full_payment.setTextColor(getColor(R.color.black))
//        nil_payment.setTextColor(getColor(R.color.black))
//
//
//
//        part_payment.setOnClickListener{
//            var fragment2 = supportFragmentManager
//            var partPaymentFragment2 = PartPaymentFragment()
//            fragment2.beginTransaction().replace(R.id.part_frame,partPaymentFragment2).commit()
//            part_payment.background =resources.getDrawable(R.drawable.full_payment_drawable)
//            nil_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//            full_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//            part_payment.setTextColor(getColor(R.color.white))
//            full_payment.setTextColor(getColor(R.color.black))
//            nil_payment.setTextColor(getColor(R.color.black))
//
//        }
//
//        full_payment.setOnClickListener{
//            var fragment1 = supportFragmentManager
//            var fullPaymentFragment = FullPaymentFragment()
//            fragment1.beginTransaction().replace(R.id.part_frame,fullPaymentFragment).commit()
//            full_payment.background =resources.getDrawable(R.drawable.full_payment_drawable)
//            part_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//            nil_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//
//            part_payment.setTextColor(getColor(R.color.black))
//            full_payment.setTextColor(getColor(R.color.white))
//            nil_payment.setTextColor(getColor(R.color.black))
//
//
//        }
//
//        nil_payment.setOnClickListener{
//            var fragment3 = supportFragmentManager
//            var partPaymentFragment3 = PartPaymentFragment()
//            fragment3.beginTransaction().replace(R.id.part_frame,partPaymentFragment3).commit()
//            full_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//            part_payment.background =resources.getDrawable(R.drawable.part_pyment_drawable)
//            nil_payment.background =resources.getDrawable(R.drawable.full_payment_drawable)
//            nil_payment.setTextColor(getColor(R.color.white))
//            full_payment.setTextColor(getColor(R.color.black))
//            part_payment.setTextColor(getColor(R.color.black))
//        }
//    }

    }
}