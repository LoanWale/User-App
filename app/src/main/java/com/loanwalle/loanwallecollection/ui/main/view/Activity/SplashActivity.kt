package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.loanwalle.loanwallecollection.databinding.ActivitySplashBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        //topA = AnimationUtils.loadAnimation(this, R.anim.top_animation)


       /* merque1.setAnimation(topA)
        merque2.setAnimation(botA)
        merque3.setAnimation(botb)
        merque4.setAnimation(botbb)
        merque5.setAnimation(botbbb)
        merque6.setAnimation(botbbbb)*/


        merque1.animate().translationX(235f).setDuration(3000).startDelay = 0
        merque2.animate().translationX(-375f).setDuration(3000).startDelay = 0
        merque3.animate().translationX(-375f).setDuration(3000).startDelay = 500
        merque4.animate().translationX(-340f).setDuration(3000).startDelay = 1000
        merque5.animate().translationX(-315f).setDuration(3000).startDelay = 1500
        merque6.animate().translationX(-290f).setDuration(3000).startDelay = 2000
        merque7.animate().translationX(-260f).setDuration(3000).startDelay = 2500

        //hellllllllooooooo

       Handler().postDelayed({
           var intent = Intent(this, LoginActivity::class.java)
           startActivity(intent)
           finish()
       }, 6000)

    }
}