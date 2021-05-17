package com.loanwalle.loanwallecollection.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.loanwalle.loanwallecollection.Adaptor.UserAdapter
import com.loanwalle.loanwallecollection.Fragment.LeadFragment
import com.loanwalle.loanwallecollection.Fragment.VerificationFragment
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.ViewModel.UserViewModel
import com.loanwalle.loanwallecollection.databinding.ActivityHomePageBinding
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.*

class HomePageActivity : AppCompatActivity() {
    private lateinit var userAdapter : UserAdapter
    private lateinit var userViewModel : UserViewModel
    private lateinit var recyclerView: RecyclerView
    var binding: ActivityHomePageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        back_drawer.setOnClickListener{
            var intent = Intent(this,Navigation::class.java)
            startActivity(intent)
        }

        binding!!.collid.setOnClickListener{
            var intent1 = Intent(this,LoanDetailActivity::class.java)
            startActivity(intent1)
        }

    }

    }


