package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        upi.setBackground(getDrawable(R.drawable.upi_id_drawable))
        qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
        upi.setTextColor(getColor(R.color.white))

        qrcode.setOnClickListener{
            qrcode.setBackground(getDrawable(R.drawable.upi_id_drawable))
            upi.setBackground(getDrawable(R.drawable.qr_code_drawable))
            qrcode.setTextColor(getColor(R.color.white))
            upi.setTextColor(getColor(R.color.black))
        }
        upi.setOnClickListener{
            upi.setBackground(getDrawable(R.drawable.upi_id_drawable))
            qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
            upi.setTextColor(getColor(R.color.white))
            qrcode.setTextColor(getColor(R.color.black))
        }
    }
}