package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityNavigationBinding
import com.loanwalle.loanwallecollection.utils.SessionManegar
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlin.system.exitProcess

class Navigation : AppCompatActivity() {

    var binding: ActivityNavigationBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var sessionManegar = SessionManegar()
        binding= ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        //setContentView(R.layout.activity_navigation)


        convey_reprt.setOnClickListener{
            var i = Intent(this,Covence_Report::class.java)
            startActivity(i)
        }

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


        logout.setOnClickListener{

            //sessionManegar.clearAll(this)

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            exitProcess(-1)
            finish()

        }

    }
}