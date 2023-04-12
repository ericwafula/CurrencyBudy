package tech.ericwathome.presentation.onboarding_screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tech.ericwathome.onboarding_domain.auth.AuthService
import tech.ericwathome.onboarding_domain.util.FirebaseResource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val service: AuthService
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<FirebaseResource<FirebaseUser>?>(null)
    val loginFlow : StateFlow<FirebaseResource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<FirebaseResource<FirebaseUser>?>(null)
    val signupFlow : StateFlow<FirebaseResource<FirebaseUser>?> = _signupFlow

    val currentUser : FirebaseUser?
        get() = service.currentUser

    init {
        if (service.currentUser != null){
            _loginFlow.value = FirebaseResource.Success(service.currentUser!!)
        }
    }

    fun login(email : String, password : String){
        viewModelScope.launch {
            _loginFlow.value = FirebaseResource.Loading
            val result = service.login(email, password)
            _loginFlow.value = result
        }
    }

    fun signup(name : String, email : String, password : String){
        viewModelScope.launch {
            _signupFlow.value = FirebaseResource.Loading
            val result = service.signUp(email, name, password)
            _signupFlow.value = result

        }
    }

    fun logout(){
        service.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}
