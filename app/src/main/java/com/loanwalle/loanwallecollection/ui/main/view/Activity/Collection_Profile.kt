package com.loanwalle.loanwallecollection.ui.main.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.loanwalle.loanwallecollection.data.model.getUserProfile.Data
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileBodies
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.loanwallecollection.data.repository.AppRepository
import com.loanwalle.loanwallecollection.databinding.ActivityCollectionProfileBinding
import com.loanwalle.loanwallecollection.ui.base.ViewModelProviderFactory
import com.loanwalle.loanwallecollection.ui.main.viewmodel.GetUserProfileViewModel
import com.loanwalle.loanwallecollection.ui.main.viewmodel.NewPasswordViewModel
import com.loanwalle.loanwallecollection.utils.Resource
import com.loanwalle.loanwallecollection.utils.errorSnack
import kotlinx.android.synthetic.main.activity_collection_profile.*
import kotlinx.android.synthetic.main.activity_resest.*

class Collection_Profile : AppCompatActivity() {

    lateinit var getUserProfileViewModel: GetUserProfileViewModel
    var binding : ActivityCollectionProfileBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        verifyClick()
        back_layout_coll.setOnClickListener{
            onBackPressed()
        }

        binding= ActivityCollectionProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
       // setContentView(R.layout.activity_collection_profile)

        binding!!.changpass.setOnClickListener {
            val i = Intent(this@Collection_Profile, ForgotPasswordActivity::class.java)
            startActivity(i)
        }




    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        getUserProfileViewModel = ViewModelProvider(this, factory).get(GetUserProfileViewModel::class.java)
    }

    fun verifyClick() {
        val userid = 113
        if (userid!=null) {
            val body = GetUserProfileBodies.GetUserProfileRequest(
                userid
            )



            getUserProfileViewModel.getUserProfile(body)



            getUserProfileViewModel.getUserProfile.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is Resource.Success -> {
                            hideProgressBar()


                            response.data?.let { verifyResponse ->
                                val message:String= verifyResponse.message
                                Log.e("Resopncelogin",message);
                                if (message.equals("success")&&verifyResponse.status.equals("200"))
                                {

                                    full_name.setText(verifyResponse.data.get(0).name)
                                    phoneno.setText(verifyResponse.data.get(0).user_id)
                                    alterno.setText(verifyResponse.data.get(0).mobile)
                                    emailid.setText(verifyResponse.data.get(0).email)
                                    fathername.setText(verifyResponse.data.get(0).dob)
                                    dob.setText(verifyResponse.data.get(0).created_on)
                                    bloodGroup.setText(verifyResponse.data.get(0).created_on)



                                    //progress9.errorSnack(message, Snackbar.LENGTH_LONG)


                                }
                                else

                                {
                                    progress9.errorSnack(message, Snackbar.LENGTH_LONG)
                                }


                            }
                        }

                        is Resource.Error -> {
                            hideProgressBar()
                            response.message?.let { message ->
                                progress9.errorSnack(message, Snackbar.LENGTH_LONG)
                            }
                        }

                        is Resource.Loading -> {
                            showProgressBar()
                        }
                    }
                }
            })
        }

    }

    fun hideProgressBar() {
        progress9.visibility = View.GONE
    }
    fun showProgressBar() {
        progress9.visibility = View.VISIBLE
    }
}