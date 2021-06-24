package com.loanwalle.loanwallecollection.data.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsBroadcastReceiver : BroadcastReceiver() {

var smsBroadcastReceiverListner : SmsBroadcastReceiverListner? = null

    override fun onReceive(context: Context?, intent: Intent?) {

        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action){

            val extras = intent.extras

            val smsRetriveStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when(smsRetriveStatus.statusCode){
                CommonStatusCodes.SUCCESS ->{
                   // val messageIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)

                    //smsBroadcastReceiverListner?.onSuccess(messageIntent)
                }
                CommonStatusCodes.TIMEOUT ->{
                    smsBroadcastReceiverListner?.onFailure()

                }
            }
        }

    }

    interface SmsBroadcastReceiverListner{

       fun onSuccess(intent: Intent?)

       fun onFailure()
    }
}