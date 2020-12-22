package com.android.academy.fundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.ws01.WS01Fragment
import com.android.academy.fundamentals.ws01.solution.WS01SolutionFragment
import com.android.academy.fundamentals.ws02.WS02Fragment
import com.android.academy.fundamentals.ws02.solution.WS02SolutionFragment
import com.android.academy.fundamentals.ws03.WS03Fragment
import com.android.academy.fundamentals.ws03.solution.WS03SolutionFragment

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        if (savedInstanceState == null) {
            routeToWorkshop(Workshops.WS01_SOL) // <--- change fragment here
        }
    }
    
    private fun routeToWorkshop(wsNum: Workshops) {
        when (wsNum) {
            Workshops.WS01 -> {
                startFragment(WS01Fragment.create())
            }
            Workshops.WS02 -> {
                startFragment(WS02Fragment.create())
            }
            Workshops.WS03 -> {
                startFragment(WS03Fragment.create())
            }
            Workshops.WS01_SOL -> {
                startFragment(WS01SolutionFragment.create())
            }
            Workshops.WS02_SOL -> {
                startFragment(WS02SolutionFragment.create())
            }
            Workshops.WS03_SOL -> {
                startFragment(WS03SolutionFragment.create())
            }
        }
    }
    
    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.simpleName)
            .commit()
    }
}