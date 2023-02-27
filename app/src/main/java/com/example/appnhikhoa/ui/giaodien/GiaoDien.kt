package com.example.appnhikhoa.ui.giaodien

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appnhikhoa.R
import com.example.appnhikhoa.model.BaseActivity
import com.example.appnhikhoa.model.CheckShowTutorial
import com.example.appnhikhoa.ui.login.MainActivity
import kotlinx.android.synthetic.main.activity_giao_dien.*

@Suppress("DEPRECATION")
class GiaoDien : BaseActivity() {
    var SHOW_TUTORIAL : String ="SHOW_TUTORIAL"
    lateinit var checkShowTutorial : CheckShowTutorial

    override fun onResume() {
        var animation_tim: Animation = AnimationUtils.loadAnimation(this@GiaoDien,
            R.anim.animation_tim)
        tim.animation = animation_tim
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giao_dien)

        checkShowTutorial = CheckShowTutorial(this@GiaoDien)
        checkShowTutorial.putBooleanValue(SHOW_TUTORIAL,true)

        setSupportActionBar(toolBarGiaoDien)
        toolBarGiaoDien.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logout -> {
                    var alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@GiaoDien)
                    alertDialog.setTitle("Thông báo")
                    alertDialog.setIcon(R.drawable.fewgewg)
                    alertDialog.setMessage("Bạn có muốn đăng xuất không?")
                    alertDialog.setPositiveButton("Có",object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            var intent : Intent = Intent(this@GiaoDien,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    })
                    alertDialog.setNegativeButton("Không",object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {

                        }

                    })
                    alertDialog.show()
                }
                else->{}
            }
            true
        }

        replaceFragment(Fragment_Lichhen())

        floatingActionButton.setOnClickListener {
            replaceFragment(Fragment_Bacsi())
            tim.visibility = View.VISIBLE
            floatingActionButton.visibility = View.INVISIBLE
            bottomNavigation.menu.findItem(R.id.icon_bacsi).setChecked(true)
        }

        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.icon_bacsi -> {
                    replaceFragment(Fragment_Bacsi())
                    floatingActionButton.visibility = View.INVISIBLE
                }
                else -> {
                    tim.visibility = View.INVISIBLE
                    replaceFragment(Fragment_Lichhen())
                    floatingActionButton.visibility = View.VISIBLE
                }
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.ChangeFragment,fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_giaodien,menu)
        return true
    }
}