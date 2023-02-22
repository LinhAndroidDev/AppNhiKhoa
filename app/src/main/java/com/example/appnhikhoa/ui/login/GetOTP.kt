package com.example.appnhikhoa.ui.login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.appnhikhoa.ui.giaodien.GiaoDien
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.NhanVien
import com.example.appnhikhoa.ui.giaodien.DoctorActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_get_otp.*
import kotlinx.android.synthetic.main.activity_nhan_tin.*

class GetOTP : BaseActivity() {
    var id:String = ""
    var phoneNumber:String = ""
    var auth:FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)

        setSupportActionBar(toolBarOTP)
        toolBarOTP.setNavigationIcon(R.drawable.back)
        toolBarOTP.setNavigationOnClickListener {
            this.finish()
        }

        GetDataIntent()

        NhapOTP.setOnClickListener {
//            var strNhapOTP:String = otp.text.toString().trim()
//            onClickNhapOTP(strNhapOTP)
            var intent:Intent = Intent(this@GetOTP, GiaoDien::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun GetDataIntent(){
        id = intent.getStringExtra("ID").toString()
        phoneNumber = intent.getStringExtra("phone number").toString()
    }

    private fun onClickNhapOTP(strNhapOTP: String) {
        val credential = PhoneAuthProvider.getCredential(id, strNhapOTP)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    if (user != null) {
                        goToGiaoDien(user.phoneNumber)
                    }
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this@GetOTP,"Error", Toast.LENGTH_SHORT).show()
                    }
                    // Update UI
                }
            }
    }

    private fun goToGiaoDien(phoneNumber: String?) {
        var intent:Intent = Intent(this@GetOTP, GiaoDien::class.java)
        intent.putExtra("phone number",phoneNumber)
        startActivity(intent)
        this.finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}