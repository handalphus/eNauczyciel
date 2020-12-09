package com.example.enauczyciel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.enauczyciel.R

class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment:Fragment

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit()
        currentFragment = fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val fragmentPowitalny = ekranPowitalny()
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentPowitalny).commit()
//

    }

}