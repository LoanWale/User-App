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