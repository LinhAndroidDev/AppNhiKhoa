package com.example.appnhikhoa.ui.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.WindowManager
import com.example.appnhikhoa.R
import kotlinx.android.synthetic.main.activity_login_with_doctor.*
import java.net.PasswordAuthentication

class LoginWithDoctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_with_doctor)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        loginDoctor.setOnClickListener {
            val intent : Intent = Intent(this,BacSiActivity::class.java)
            startActivity(intent)
        }

        showPassword.setOnClickListener {
            hidePassword()
        }
    }

    private fun hidePassword() {
        if(edtPasswordDoctor.transformationMethod == PasswordTransformationMethod.getInstance()){
            edtPasswordDoctor.transformationMethod = null
            showPassword.setBackgroundResource(R.drawable.icon_show_password_grey)
        }else if(edtPasswordDoctor.transformationMethod == null){
            edtPasswordDoctor.transformationMethod = PasswordTransformationMethod.getInstance()
            showPassword.setBackgroundResource(R.drawable.icon_hint_grey)
        }
    }
}