package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivityNavigationBinding

class Navigation : AppCompatActivity() {

    var binding: ActivityNavigationBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //setContentView(R.layout.activity_navigation)



        binding!!.profile.setOnClickListener{
            var intent1 = Intent(this,Collection_Profile::class.java)
            startActivity(intent1)
        }


           binding!!.todayld.setOnClickListener{
            var intent1 = Intent(this,HomePageActivity::class.java)
            startActivity(intent1)
        }
           binding!!.totaled.setOnClickListener{
            var intent1 = Intent(this,Total_Leads::class.java)
            startActivity(intent1)
        }
           binding!!.ircontact.setOnClickListener{
            var intent1 = Intent(this,IRAActivity::class.java)
            startActivity(intent1)
        }


        binding!!.figureprint.setOnClickListener{
            var intent1 = Intent(this,UnlockActivity::class.java)
            startActivity(intent1)
        }



    }
}