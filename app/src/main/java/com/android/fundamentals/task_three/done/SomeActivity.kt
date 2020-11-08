package com.android.fundamentals.task_three.done

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R

class SomeActivity : AppCompatActivity(), SomeFragmentContract {

    private var someFragment: SomeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_some)

        if (savedInstanceState == null) {
            someFragment = SomeFragment.newInstance("Android Academy")
            someFragment?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, this, SOME_FRAGMENT_TAG)
                    .commit()
            }
        }
    }

    override fun onNextClicked() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, OtherFragment())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val SAMPLE_DIALOG_TAG = "SampleDialogFragent"
        const val SOME_FRAGMENT_TAG = "SomeFragment"
    }

}