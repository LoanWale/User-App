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

        var fragmentMAnger = supportFragmentManager
        val leadFragment = LeadFragment()
        fragmentMAnger.beginTransaction().replace(R.id.frame,leadFragment).commit()
        binding!!.layoutCollection.setBackground(getDrawable(R.drawable.btn_drawble))
        binding!!.layoutVerification.setBackground(getDrawable(R.drawable.layout_drawable))
        binding!!.collectionBtn.setTextColor(getResources().getColor(R.color.white))
        binding!!.verificationBtn.setTextColor(getResources().getColor(R.color.black))

      /*  val fragmentManager = supportFragmentManager
        val loginFragment = LeadFragment()
        fragmentManager.beginTransaction().replace(R.id.frame, loginFragment).addToBackStack("back")
            .commit()*/



      /*  initRecyclerView()

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getData()
        userViewModel.userLiveData.observe(this, androidx.lifecycle.Observer {
            userAdapter.setData(it as ArrayList<User>)
        })*/



        binding!!.collectionBtn.setOnClickListener{


            binding!!.layoutCollection.setBackground(getDrawable(R.drawable.btn_drawble))
            binding!!.layoutVerification.setBackground(getDrawable(R.drawable.layout_drawable))

            binding!!.collectionBtn.setTextColor(getResources().getColor(R.color.white))
            binding!!.verificationBtn.setTextColor(getResources().getColor(R.color.black))

            var fragmentMAnger = supportFragmentManager
            val leadFragment = LeadFragment()
            fragmentMAnger.beginTransaction().replace(R.id.frame,leadFragment).commit()


           /* val layoutManger = LinearLayoutManager(this)
            layoutManger.orientation = LinearLayoutManager.VERTICAL
            recycler_view.layoutManager = layoutManger

            val adapter = HomePageAdapter(this, TodayCollection.Coll.todaycollection)
            recycler_view.adapter = adapter*/
        }

        binding!!.verificationBtn.setOnClickListener {

            binding!!.layoutCollection.setBackground(getDrawable(R.drawable.layout_drawable))
            binding!!.layoutVerification.setBackground(getDrawable(R.drawable.btn_drawble))

            binding!!.collectionBtn.setTextColor(getResources().getColor(R.color.black))
            binding!!.verificationBtn.setTextColor(getResources().getColor(R.color.white))

          var fragment = supportFragmentManager
            var vf = VerificationFragment()
            fragment.beginTransaction().replace(R.id.frame,vf).commit()

        }


    }


}