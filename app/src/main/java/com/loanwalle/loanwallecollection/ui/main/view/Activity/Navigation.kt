package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loanwalle.loanwallecollection.databinding.ActivityNavigationBinding
import com.loanwalle.loanwallecollection.utils.SessionManegar
import kotlinx.android.synthetic.main.activity_navigation.*

class Navigation : AppCompatActivity() {

    var binding: ActivityNavigationBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sessionManegar = SessionManegar()
        binding= ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        convey_reprt.setOnClickListener{
            val i = Intent(this,Covence_Report::class.java)
            startActivity(i)
        }

        binding!!.profile.setOnClickListener{
            val intent1 = Intent(this,Collection_Profile::class.java)
            startActivity(intent1)
        }


        binding!!.menu.setOnClickListener { finish() }

           binding!!.todayld.setOnClickListener{
            val intent1: Intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent1)
        }
           binding!!.totaled.setOnClickListener{
            val intent1 = Intent(this,Total_Leads::class.java)
            startActivity(intent1)
        }
           binding!!.ircontact.setOnClickListener{
            val intent1 = Intent(this,IRAActivity::class.java)
            startActivity(intent1)
        }


        binding!!.figureprint.setOnClickListener{
            val intent1 = Intent(this,UnlockActivity::class.java)
            startActivity(intent1)
        }

        logout.setOnClickListener{
            sessionManegar.clearAll(this)
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}