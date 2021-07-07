package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.loanwalle.loanwallecollection.R
import com.loanwalle.loanwallecollection.databinding.ActivitySplashBinding
import com.loanwalle.loanwallecollection.util.Constants
import com.loanwalle.loanwallecollection.utils.ConstantsSave
import com.loanwalle.loanwallecollection.utils.SessionManegar
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    var binding : ActivitySplashBinding? = null
    var sessionManegar = SessionManegar()
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW)
            )
        }

        intent.extras?.let {
            for (key in it.keySet()) {
                val value = intent.extras?.get(key)
                Log.d("TAG", "Key: $key Value: $value")
            }
        }


        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Log.e("TAG", msg)
               // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }



        Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.e("Token", msg)
          //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()

            sessionManegar.saveString(this,Constants.USER_TOKEN,token)
           // toast(token)


        })
        // [END log_reg_token]




        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("TAG", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })*/


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
       }, 1)
    }
}