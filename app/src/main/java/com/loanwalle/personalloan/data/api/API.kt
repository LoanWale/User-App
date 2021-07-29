


import com.loanwalle.personalloan.data.model.loginResponse.LoginResponce
import com.loanwalle.personalloan.data.model.loginResponse.RequestBodies

import com.loanwalle.personalloan.data.model.sendOtp.RequestOtpBody
import com.loanwalle.personalloan.data.model.sendOtp.ResponseOtp

import com.loanwalle.personalloan.data.model.token.TokenRequest
import com.loanwalle.personalloan.data.model.token.TokenResponse
import com.loanwalle.personalloan.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.personalloan.data.model.verifyPasswordOtp.VerifyPasswordResponse
import com.loanwalle.personalloan.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.personalloan.data.model.vierifyOtp.VerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST("User_login/login_api")
    suspend fun loginUser(@Body body: RequestBodies.LoginBody): Response<LoginResponce>
    @POST("User_login/send_otp")
    suspend fun loginOtp(@Body body: RequestOtpBody.RequestOtp): Response<ResponseOtp>
    @POST("User_login/Verify_OTP")
    suspend fun verifyOtp(@Body body: VerifyRequestBody.VerifyRequest): Response<VerifyResponse>




}