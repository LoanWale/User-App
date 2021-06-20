import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotRequestBodies
import com.loanwalle.loanwallecollection.data.model.forgotPassword.ForgotResponse
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileBodies
import com.loanwalle.loanwallecollection.data.model.getUserProfile.GetUserProfileResponse
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsReq
import com.loanwalle.loanwallecollection.data.model.loanDetails.LoanDetailsResponse
import com.loanwalle.loanwallecollection.data.model.loginResponse.LoginResponce
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordRequestBodies
import com.loanwalle.loanwallecollection.data.model.newPassword.NewPasswordResponse
import com.loanwalle.loanwallecollection.data.model.sendOtp.RequestOtpBody
import com.loanwalle.loanwallecollection.data.model.sendOtp.ResponseOtp
import com.loanwalle.loanwallecollection.data.model.todaylead.TodayLeadResponce
import com.loanwalle.loanwallecollection.data.model.todaylead.TodayleadRequ
import com.loanwalle.loanwallecollection.data.model.totalLead.TotalLeadResponce
import com.loanwalle.loanwallecollection.data.model.totalLead.TotalLeadRequest
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileBody
import com.loanwalle.loanwallecollection.data.model.userProfile.UserProfileResponse
import com.loanwalle.loanwallecollection.data.model.verifyPasswordOtp.VerifyPasswordOTPRequest
import com.loanwalle.loanwallecollection.data.model.verifyPasswordOtp.VerifyPasswordResponse
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


    @POST("User_login/Verify_OTP")
    suspend fun verifyOtp(@Body body:VerifyRequestBody.VerifyRequest): Response<VerifyResponse>



    @POST("user/get-current-address")
    suspend fun userProfile(@Body body:UserProfileBody.UserProfileRequest): Response<UserProfileResponse>


    @POST("user/get-old-collection-leads")
    suspend fun totalLeads(@Body body:TotalLeadRequest.LeadRequest): Response<TotalLeadResponce>

    @POST("user/get-todays-collection-leads")
    suspend fun todayLeads(@Body body:TodayleadRequ.LeadRequest): Response<TodayLeadResponce>

    @POST("login/forget-password")
    suspend fun forgotPassword(@Body body:ForgotRequestBodies.ForgotRequest): Response<ForgotResponse>

    @POST("user/verify-password-otp")
    suspend fun verfiyPassword(@Body body:VerifyPasswordOTPRequest.VerifyPasswordOTP): Response<VerifyPasswordResponse>

    @POST("user/update-password")
    suspend fun newPassword(@Body body:NewPasswordRequestBodies.NewPasswordRequest): Response<NewPasswordResponse>

    @POST("User/get_profile")
    suspend fun getUserProfile(@Body body:GetUserProfileBodies.GetUserProfileRequest): Response<GetUserProfileResponse>

    @POST("user/get-loan-deatils-by-id")
    suspend fun getLoanDetails(@Body body: LoanDetailsReq): Response<LoanDetailsResponse>




}