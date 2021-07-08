package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_payment.*
class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        Ra_web.setBackground(getDrawable(R.drawable.upi_id_drawable))
        Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
        Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))

        Ra_online.setOnClickListener {
            Ra_web.visibility = View.VISIBLE
            Ra_qrcode.visibility = View.VISIBLE
            Ra_upi.visibility = View.VISIBLE
            Ra_Cash.visibility = View.GONE
        }

        Ra_offline.setOnClickListener {
            Ra_web.visibility = View.GONE
            Ra_qrcode.visibility = View.GONE
            Ra_upi.visibility = View.GONE
            Ra_Cash.visibility = View.VISIBLE
        }





        submitpage.setOnClickListener {
            val intt= Intent(this,PaymentSuccessfullActivity::class.java)
        startActivity(intt)
        }


        Ra_web.setOnClickListener {
            Ra_web.setBackground(getDrawable(R.drawable.upi_id_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
            Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))

        }

        Ra_qrcode.setOnClickListener{
            Ra_web.setBackground(getDrawable(R.drawable.qr_code_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.upi_id_drawable))
            Ra_upi.setBackground(getDrawable(R.drawable.qr_code_drawable))
            val intt= Intent(this,QrCodeActivity::class.java)
            startActivity(intt)
        }
        Ra_upi.setOnClickListener{
            Ra_web.setBackground(getDrawable(R.drawable.qr_code_drawable))
            Ra_qrcode.setBackground(getDrawable(R.drawable.qr_code_drawable))
            Ra_upi.setBackground(getDrawable(R.drawable.upi_id_drawable))
        }
    }
}