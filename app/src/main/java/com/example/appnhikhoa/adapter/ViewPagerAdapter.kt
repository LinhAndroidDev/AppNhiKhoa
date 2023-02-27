package com.example.appnhikhoa.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.appnhikhoa.ui.doctor.FragmentBenhNhan
import com.example.appnhikhoa.ui.doctor.FragmentLichKham
import com.example.appnhikhoa.ui.doctor.FragmentNhanVien

enum class Page(val title : String, val fragmentClass: Class<out Fragment>){
    BENH_NHAN("Danh sách bệnh nhân", FragmentBenhNhan::class.java),
    LICH_KHAM("Danh sách lịch khám", FragmentLichKham::class.java),
    NHAN_VIEN("Danh sách nhân viên", FragmentNhanVien::class.java)
}

@Suppress("DEPRECATION")
class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    private val pages : List<Page> = arrayListOf<Page>().apply {
        addAll(Page.values())
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position].fragmentClass.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}