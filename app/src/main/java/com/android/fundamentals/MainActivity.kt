package com.android.fundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.fundamentals.workshop01.solution.Workshop1SolutionFragment
import com.android.fundamentals.workshop02.solution.Workshop2SolutionFragment
import com.android.fundamentals.workshop03.solution.Workshop3SolutionFragment

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment(SelectWorkshopFragment.newInstance(), addToBackStack = false)
        }
    }

    override fun openWorkshop1() = openFragment(Workshop1SolutionFragment.newInstance())

    override fun openWorkshop2() = openFragment(Workshop2SolutionFragment.newInstance())

    override fun openWorkshop3() = openFragment(Workshop3SolutionFragment.newInstance())

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.name)
        }

        transaction.commit()
    }
}