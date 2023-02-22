package com.example.appnhikhoa.ui.login

import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.res.ResourcesCompat
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.CheckShowTutorial
import com.example.appnhikhoa.ui.giaodien.GiaoDien
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : BaseActivity() {
    var SHOW_TUTORIAL : String ="SHOW_TUTORIAL"
    lateinit var checkShowTutorial : CheckShowTutorial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        checkShowTutorial = CheckShowTutorial(this@IntroActivity)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        var animationIconIntro : Animation = AnimationUtils.loadAnimation(this@IntroActivity,R.anim.animation_icon_nhi_khoa)
        var animationTxtIntro : Animation = AnimationUtils.loadAnimation(this@IntroActivity,R.anim.animation_txt_intro)
        var animationTxtInfo : Animation = AnimationUtils.loadAnimation(this@IntroActivity,R.anim.animation_info)

        val androgune : Typeface? =ResourcesCompat.getFont(this@IntroActivity,R.font.svn_androgyne)
        txtIntro.typeface = androgune

        var countDownTimer : CountDownTimer = object : CountDownTimer(4000,4000){
            override fun onTick(millisUntilFinished: Long) {
                iconNhiKhoa.startAnimation(animationIconIntro)
                txtIntro.startAnimation(animationTxtIntro)
                txtInfo.startAnimation(animationTxtInfo)
            }

            override fun onFinish() {
                if(checkShowTutorial.getBooleanValue(SHOW_TUTORIAL)) {
                    val intent: Intent = Intent(this@IntroActivity, GiaoDien::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    var intent : Intent = Intent(this@IntroActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }

    override fun onBackPressed() {

    }
}