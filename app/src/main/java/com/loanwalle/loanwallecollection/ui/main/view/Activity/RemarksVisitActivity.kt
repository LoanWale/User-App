package com.loanwalle.loanwallecollection.ui.main.view.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_remarks_visit.*

class RemarksVisitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remarks_visit)

        back_remarks.setOnClickListener{
            onBackPressed()
        }
    }
}