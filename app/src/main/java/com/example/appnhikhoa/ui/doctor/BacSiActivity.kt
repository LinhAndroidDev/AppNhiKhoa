package com.example.appnhikhoa.ui.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appnhikhoa.R
import com.example.appnhikhoa.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_bac_si.*

@Suppress("DEPRECATION")
class BacSiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bac_si)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val viewPagerAdapter : ViewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        viewPagerBacSi.adapter = viewPagerAdapter
        tabLayoutBacSi.setupWithViewPager(viewPagerBacSi)
    }
}