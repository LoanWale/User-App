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
    var sessionManegar = SessionManegar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        startone.animate().translationX(-385f).setDuration(3000).startDelay = 0
        start2.animate().translationX(-375f).setDuration(3000).startDelay = 0
        merque2.animate().translationX(-360f).setDuration(3000).startDelay = 0
        merque3.animate().translationX(-350f).setDuration(3000).startDelay = 500
        merque4.animate().translationX(-340f).setDuration(3000).startDelay = 1000
        merque5.animate().translationX(-315f).setDuration(3000).startDelay = 1500
        merque6.animate().translationX(-290f).setDuration(3000).startDelay = 2000
        merque7.animate().translationX(-260f).setDuration(3000).startDelay = 2500
       Handler().postDelayed({
           val intnt = Intent()
           val state = sessionManegar.getInt(this@SplashActivity,sessionManegar.LOGIN_STATE)
           val ulock = sessionManegar.getString(this,sessionManegar.SWITCH_BUTTON)


           if (state== ConstantsSave.LoginFlow.LOGINSCREEN){
               intnt.setClass(this@SplashActivity,LoginActivity::class.java)
               finish()
           }
           else if (state == ConstantsSave.LoginFlow.Otpscreen){
               intnt.setClass(this@SplashActivity,OtpActivity::class.java)
               finish()
           } else if (ulock!= null){
               intnt.setClass(this@SplashActivity,HomePageActivity::class.java)
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