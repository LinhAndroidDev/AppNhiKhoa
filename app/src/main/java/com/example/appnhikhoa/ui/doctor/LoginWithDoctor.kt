package com.example.appnhikhoa.ui.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.appnhikhoa.R
import kotlinx.android.synthetic.main.activity_login_with_doctor.*

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
    }
}