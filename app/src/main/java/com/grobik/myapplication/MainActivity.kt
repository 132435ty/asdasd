package com.grobik.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    private lateinit var fragment3: Fragment3
    private lateinit var fragment4: Fragment4
    private lateinit var fragment5: Fragment5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()
        fragment4 = Fragment4()
        fragment5 = Fragment5()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment1)
            .hide(fragment1)
            .add(R.id.fragment_container, fragment2)
            .hide(fragment2)
            .add(R.id.fragment_container, fragment3)
            .hide(fragment3)
            .add(R.id.fragment_container, fragment4)
            .hide(fragment4)
            .add(R.id.fragment_container, fragment5)
            .hide(fragment5)
            .commit()

        val Button1 = findViewById<Button>(R.id.button1) as Button
        Button1.setOnClickListener {
            showPreviousFragment()
        }

        val Button2 = findViewById<Button>(R.id.button2) as Button
        Button2.setOnClickListener {
            showNextFragment()
        }
    }

    private fun showNextFragment() {
        val currentFragment = getCurrentFragment()
        val nextFragment = getNextFragment(currentFragment)

        supportFragmentManager.beginTransaction()
            .hide(currentFragment)
            .show(nextFragment)
            .commit()
    }
    private fun showPreviousFragment() {
        val currentFragment = getCurrentFragment()
        val previousFragment = getPreviousFragment(currentFragment)

        supportFragmentManager.beginTransaction()
            .hide(currentFragment) // Hide the current fragment
            .show(previousFragment) // Show the previous fragment
            .commit()
    }
    private fun getCurrentFragment(): Fragment {
        val fragmentList = listOf(fragment1, fragment2, fragment3, fragment4, fragment5)
        for (fragment in fragmentList) {
            if (fragment.isVisible) {
                return fragment
            }
        }
        return fragment1 // Default to Fragment1 if no fragment is visible
    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(fragment1)
            .hide(fragment2)
            .hide(fragment3)
            .hide(fragment4)
            .hide(fragment5)
            .show(fragment)
            .commit()
    }
    private fun getNextFragment(currentFragment: Fragment): Fragment {
        val fragmentList = listOf(fragment1, fragment2, fragment3, fragment4, fragment5)
        val currentIndex = fragmentList.indexOf(currentFragment)
        val nextIndex = (currentIndex + 1) % fragmentList.size
        return fragmentList[nextIndex]
    }

    private fun getPreviousFragment(currentFragment: Fragment): Fragment {
        val fragmentList = listOf(fragment1, fragment2, fragment3, fragment4, fragment5)
        val currentIndex = fragmentList.indexOf(currentFragment)
        val previousIndex = if (currentIndex == 0) fragmentList.size - 1 else currentIndex - 1
        return fragmentList[previousIndex]
    }

}
