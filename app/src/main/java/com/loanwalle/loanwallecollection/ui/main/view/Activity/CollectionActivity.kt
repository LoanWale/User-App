package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityCollectionBinding
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_total_leads.*

class CollectionActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")

    var binding :ActivityCollectionBinding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        nil_payment.setBackground(getDrawable(R.drawable.part_pyment_drawable))
        part_payment.setTextColor(getColor(R.color.white))
        part_payment.background = resources.getDrawable(R.drawable.full_payment_drawable)
        full_payment.setTextColor(getColor(R.color.black))
        full_payment.background = resources.getDrawable(R.drawable.part_pyment_drawable)
        imageView6.isVisible = true
        next_sch_date.isVisible = true


        submit.setOnClickListener {
            val intent=Intent(this,PaymentActivity::class.java)
            startActivity(intent)
        }

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
}