package com.example.upaxtestapp.core.base

import androidx.appcompat.app.AppCompatActivity
import com.example.upaxtestapp.common.components.LoaderDialog

open class BaseActivity : AppCompatActivity() {
    private val mLoader by lazy {
        LoaderDialog(this)
    }

    fun showProgressLoader() {
        if (!mLoader.isShowing) {
            mLoader.show()
        }
    }

    fun hideProgressLoader() {
        if (mLoader.isShowing) {
            mLoader.dismiss()
        }
    }
}