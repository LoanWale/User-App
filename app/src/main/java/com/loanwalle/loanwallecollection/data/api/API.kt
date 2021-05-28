import com.loanwalle.loanwallecollection.data.model.loginResponse.LoginResponce
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.sendOtp.ResponseOtp
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileResponse
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyRequestBody
import com.loanwalle.loanwallecollection.data.model.vierifyOtp.VerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST("User_login/login_api")
    suspend fun loginUser(@Body body: RequestBodies.LoginBody): Response<LoginResponce>



    @POST("User_login/send_otp")
    suspend fun loginOtp(@Body body: RequestOtpBody.RequestOtp): Response<ResponseOtp>

    @POST("User_login/verify_otp")
    suspend fun verifyOtp(@Body body:VerifyRequestBody.VerifyRequest): Response<VerifyResponse>


    @POST("User/get_profile")
    suspend fun userProfile(@Body body:UserProfileBody.UserProfileRequest): Response<UserProfileResponse>



}