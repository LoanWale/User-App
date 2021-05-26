package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityHomePageBinding
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.*

class HomePageActivity : AppCompatActivity() {
    var binding: ActivityHomePageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        collection_layout!!.visibility= View.VISIBLE
        Verification_layout!!.visibility= View.GONE




        binding!!.collid.setOnClickListener{
            var intent1 = Intent(this,LoanDetailActivity::class.java)
            startActivity(intent1)
        }


        btn_collection!!.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()

            collection_layout!!.visibility=View.VISIBLE
            Verification_layout!!.visibility=View.GONE
            btn_collection!!.setBackgroundResource(R.drawable.paytmbutton)
            btn_verification!!.setBackgroundResource(R.drawable.graybackground)

        }



        btn_verification!!.setOnClickListener{
            //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            collection_layout!!.visibility=View.GONE
            Verification_layout!!.visibility=View.VISIBLE
            btn_verification!!.setBackgroundResource(R.drawable.paytmbutton)
            btn_collection!!.setBackgroundResource(R.drawable.graybackground)



        }



        back_layout!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, Navigation::class.java)
            startActivity(i)

        }


        collid!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, LoanDetailActivity::class.java)
            startActivity(i)

        }
        verify!!.setOnClickListener{
            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomePageActivity, Verification::class.java)
            startActivity(i)

        }

//        ir_contact!!.setOnClickListener{
//            //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
//            val i = Intent(this@HomeActivity, IRAActivity::class.java)
//            startActivity(i)
//
//        }

    }

    }


