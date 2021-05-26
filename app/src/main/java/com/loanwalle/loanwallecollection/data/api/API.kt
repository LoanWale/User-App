import com.loanwalle.loanwallecollection.data.model.loginResponse.LoginResponce
import com.loanwalle.loanwallecollection.data.model.loginResponse.RequestBodies
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @POST("User_login/login_api")
    suspend fun loginUser(@Body body: RequestBodies.LoginBody): Response<LoginResponce>


}