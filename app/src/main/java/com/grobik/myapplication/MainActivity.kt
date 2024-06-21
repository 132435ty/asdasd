package com.grobik.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    private lateinit var fragment3: Fragment3 // привязываем фрагмент
    private lateinit var fragment4: Fragment4
    private lateinit var fragment5: Fragment5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3() // называем фрагменты
        fragment4 = Fragment4()
        fragment5 = Fragment5()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment1) // проявляем фрагмент
            .hide(fragment1) // прячем фрагмент
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
        Button1.setOnClickListener {                             // делаем кнопку нажимаемой
            showPreviousFragment()
        }

        val Button2 = findViewById<Button>(R.id.button2) as Button
        Button2.setOnClickListener {                             // делаем кнопку нажимаемой
            showNextFragment()
        }
    }

    private fun showNextFragment() {
        val currentFragment = getCurrentFragment()
        val nextFragment = getNextFragment(currentFragment)

        supportFragmentManager.beginTransaction()
            .hide(currentFragment) // прячет текущ. фрагмент
            .show(nextFragment) // показывает следующий
            .commit()
    }
    private fun showPreviousFragment() {
        val currentFragment = getCurrentFragment()
        val previousFragment = getPreviousFragment(currentFragment)

        supportFragmentManager.beginTransaction()
            .hide(currentFragment) // прячет текущ. фрагмент
            .show(previousFragment) // показывает предыдущий фрагмент
            .commit()
    }
    private fun getCurrentFragment(): Fragment {
        val fragmentList = listOf(fragment1, fragment2, fragment3, fragment4, fragment5) // лист фрагментов
        for (fragment in fragmentList) {
            if (fragment.isVisible) {
                return fragment // возврат текущего фрагмента
            }
        }
        return fragment1 // возврат к 1 фрагменту
    }

    private fun getNextFragment(currentFragment: Fragment): Fragment {
        val fragmentList = listOf(fragment1, fragment2, fragment3, fragment4, fragment5) // лист
        val currentIndex = fragmentList.indexOf(currentFragment) // приравнение к идексу фрагмента в списке
        val nextIndex = (currentIndex + 1) % fragmentList.size // приравнение к идексу следующего фрагмента в списке
        return fragmentList[nextIndex]
    }

    private fun getPreviousFragment(currentFragment: Fragment): Fragment {
        val fragmentList = listOf(fragment1, fragment2, fragment3, fragment4, fragment5)
        val currentIndex = fragmentList.indexOf(currentFragment) // приравнение к идексу фрагмента в списке
        val previousIndex = if (currentIndex == 0) fragmentList.size - 1 else currentIndex - 1 // приравнение к идексу  предыдущего фрагмента в списке или первого фрагмента
        return fragmentList[previousIndex]
    }

}
