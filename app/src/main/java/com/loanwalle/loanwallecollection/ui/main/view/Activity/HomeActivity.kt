package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.loanwalle.loanwallecollection.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.nav_header_home.*

class HomeActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var  toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    var ln_todaylead:LinearLayout ?=null
    var btn_collection:LinearLayout ?=null
    var collection_layout:LinearLayout ?=null
    var Verification_layout:LinearLayout ?=null
    var collid:LinearLayout ?=null
    var btn_verification:LinearLayout ?=null
    var ir_contact:LinearLayout ?=null
    var veriid:LinearLayout ?=null
    var btncamera:CircleImageView ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        btn_collection = findViewById(R.id.btn_collection)
        collection_layout = findViewById(R.id.collection_layout)
        Verification_layout = findViewById(R.id.Verification_layout)
        btn_verification = findViewById(R.id.btn_verification)
        veriid = findViewById(R.id.veriid)
        collid = findViewById(R.id.collid)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        var toogle = ActionBarDrawerToggle(
            this, drawerLayout,toolbar,0,0)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerview = navigationView.getHeaderView(0)
        navigationView.setNavigationItemSelectedListener(this)
        ln_todaylead = headerview.findViewById<LinearLayout>(R.id.totallead)
        btncamera = headerview.findViewById<CircleImageView>(R.id.btncamera)
        ir_contact = headerview.findViewById<LinearLayout>(R.id.ir_contact)


        collection_layout!!.visibility=View.VISIBLE
        Verification_layout!!.visibility=View.GONE

        ln_todaylead!!.setOnClickListener{
        //    Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomeActivity, Total_Leads::class.java)
            startActivity(i)
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



        btncamera!!.setOnClickListener{
        //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomeActivity, Collection_Profile::class.java)
            startActivity(i)

        }


        collid!!.setOnClickListener{
        //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomeActivity, LoanDetailActivity::class.java)
            startActivity(i)

        }
        veriid!!.setOnClickListener{
        //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomeActivity, Verification::class.java)
            startActivity(i)

        }

        ir_contact!!.setOnClickListener{
        //  Toast.makeText(this@HomeActivity,"today leads",Toast.LENGTH_LONG).show()
            val i = Intent(this@HomeActivity, IRAActivity::class.java)
            startActivity(i)

        }



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.today_lead ->{
                Toast.makeText(this,"Today Lead Is 556",Toast.LENGTH_LONG).show()
                /*val intent = Intent(this,HomePageActivity::class.java)
                startActivity(intent)*/
            }
            R.id.lead ->{
               /* val inten = Intent(this,HomePageActivity::class.java)
                startActivity(inten)*/
                Toast.makeText(this,"Total Lead 1024",Toast.LENGTH_SHORT).show()
            }
            R.id.emergency->{
                /*val intent = Intent(this,IRAActivity::class.java)
                startActivity(intent)*/
                Toast.makeText(this,"Contac No. 1234567890",Toast.LENGTH_SHORT).show()
            }
        }


        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}