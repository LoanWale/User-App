package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.loanwalle.loanwallecollection.databinding.ActivitySplashBinding
import com.loanwalle.loanwallecollection.utils.ConstantsSave
import com.loanwalle.loanwallecollection.utils.SessionManegar
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    var binding : ActivitySplashBinding? = null

    var topA: Animation? = null
    var botA: Animation? = null
    var botb: Animation? = null
    var botbb: Animation? = null
    var botbbb: Animation? = null
    var botbbbb: Animation? = null
    var bot5: Animation? = null
    var bot6: Animation? = null
    var sessionManegar = SessionManegar()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


        merque1.animate().translationX(235f).setDuration(3000).startDelay = 0
        merque2.animate().translationX(-375f).setDuration(3000).startDelay = 0
        merque3.animate().translationX(-375f).setDuration(3000).startDelay = 500
        merque4.animate().translationX(-340f).setDuration(3000).startDelay = 1000
        merque5.animate().translationX(-315f).setDuration(3000).startDelay = 1500
        merque6.animate().translationX(-290f).setDuration(3000).startDelay = 2000
        merque7.animate().translationX(-260f).setDuration(3000).startDelay = 2500



       Handler().postDelayed({

           var intnt = Intent()
           var state = sessionManegar.getInt(this@SplashActivity,sessionManegar.LOGIN_STATE)

           if (state== ConstantsSave.LoginFlow.LOGINSCREEN){
               intnt.setClass(this@SplashActivity,LoginActivity::class.java)
               finish()
           }
           else if (state == ConstantsSave.LoginFlow.Otpscreen){
               intnt.setClass(this@SplashActivity,OtpActivity::class.java)
               finish()
           }
           else{
               intnt.setClass(this@SplashActivity,HomePageActivity::class.java)
               finish()
           }

           startActivity(intnt)
           finish()
       }, 6000)

    }


}