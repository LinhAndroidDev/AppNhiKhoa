package com.example.appnhikhoa.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.appnhikhoa.ui.giaodien.GiaoDien
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.CheckShowTutorial
import com.example.appnhikhoa.ui.doctor.LoginWithDoctor
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {
    var SHOW_TUTORIAL : String ="SHOW_TUTORIAL"
    lateinit var checkShowTutorial : CheckShowTutorial
    var auth:FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        checkShowTutorial = CheckShowTutorial(this@MainActivity)

        checkShowTutorial.putBooleanValue(SHOW_TUTORIAL,false)

        login.setOnClickListener {
//            var strPhoneNumber:String = account.text.toString()
//            onClickDangNhap(strPhoneNumber)
            val intent:Intent = Intent(this@MainActivity, GetOTP::class.java)
            startActivity(intent)
            finish()
        }

        loginWithDoctor.setOnClickListener{
            val intent : Intent = Intent(this, LoginWithDoctor::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun onClickDangNhap(strPhoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(strPhoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(p0)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Toast.makeText(this@MainActivity,"Error Firebase",Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    gotoGetOtp(strPhoneNumber,p0)
                }
            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    goToGiaoDien(user?.phoneNumber)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this@MainActivity,"Error Sign",Toast.LENGTH_SHORT).show()
                    }
                    // Update UI
                }
            }
    }

    private fun gotoGetOtp(strPhoneNumber: String, p0: String) {
        var intent:Intent = Intent(this@MainActivity, GetOTP::class.java)
        intent.putExtra("phone number",strPhoneNumber)
        intent.putExtra("ID",p0)
        startActivity(intent)
        finish()
    }

    private fun goToGiaoDien(phoneNumber: String?) {
        var intent:Intent = Intent(this@MainActivity, GiaoDien::class.java)
        intent.putExtra("phone number",phoneNumber)
        startActivity(intent)
        finish()
    }
}