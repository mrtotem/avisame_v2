package com.mrtotem.avisame.managers

import android.view.View
import com.mrtotem.avisame.http.AvisameApiClient
import com.mrtotem.avisame.http.catalogs.LoginCatalog
import com.mrtotem.avisame.http.catalogs.RegisterCatalog
import com.mrtotem.avisame.http.catalogs.UserCatalog
import com.mrtotem.avisame.http.interfaces.UserService
import com.mrtotem.avisame.models.User
import com.mrtotem.avisame.models.responses.*
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import retrofit2.Call

class UserOperationsManager {

    var loginNotification: PublishSubject<SubjectItem<String>>
    private var userService: UserService? = null
    private val loginCatalog: LoginCatalog = LoginCatalog()
    private val userCatalog: UserCatalog = UserCatalog()
    private val registerCatalog: RegisterCatalog = RegisterCatalog()

    private lateinit var user: User

    init {
        ++myInstancesCount
        loginNotification = PublishSubject.create()

        userService = AvisameApiClient().getClient()?.create<UserService>(UserService::class.java)
    }


    companion object {
        //Debuggable field to check instance count
        private var myInstancesCount = 0
        private val mInstance: UserOperationsManager = UserOperationsManager()

        const val LOGIN_RESPONSE: String = "LOGIN_RESPONSE"
        const val GET_USER_RESPONSE: String = "GET_USER_RESPONSE"

        @Synchronized
        fun getInstance(): UserOperationsManager {
            return mInstance
        }
    }

    fun callToLogin(view: View, username: String, password: String, userObserver: Consumer<SubjectItem<String>>) {
        loginNotification
                .bindToLifecycle(view)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userObserver)

        val userCall: Call<LoginResponse>? = userService?.loginToUser(
                username,
                password)

        loginCatalog.subscribeToUserSubject(Consumer {
            if (it) {
                loginNotification.onNext(SubjectItem(LOGIN_RESPONSE, null, loginCatalog.userId))
            } else {
                loginNotification.onNext(SubjectItem(LOGIN_RESPONSE, SubjectError("Login Error"), loginCatalog.userId))
            }
        })
        userCall?.enqueue(loginCatalog)
    }

    fun getLoggedUser(view: View, userObserver: Consumer<SubjectItem<String>>) {
        loginNotification
                .bindToLifecycle(view)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userObserver)

        val userCall: Call<UserResponse>? = userService?.getUser("Bearer " + loginCatalog.token, loginCatalog.userId)

        userCatalog.subscribeToUserSubject(Consumer {
            if (it) {
                loginNotification.onNext(SubjectItem(GET_USER_RESPONSE, null, "Bienvenido " + userCatalog.user?.getFirstName()))
            } else {
                loginNotification.onNext(SubjectItem(GET_USER_RESPONSE, SubjectError("Get User Error"), null))
            }
        })
        userCall?.enqueue(userCatalog)
    }

    fun callToRegister(view: View, username: String, email: String, password: String, registerObserver: Consumer<SubjectItem<String>>) {
        loginNotification
                .bindToLifecycle(view)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registerObserver)

        val userCall: Call<RegisterResponse>? = userService?.registerAnUser(registerCatalog.email, registerCatalog.password)
    }
}