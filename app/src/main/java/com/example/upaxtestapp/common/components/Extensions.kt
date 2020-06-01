package com.example.upaxtestapp.common.components

import android.view.View

private const val passwordDot = '\u2022'
private const val serverDateFormat = "yyyy-MM-dd"
private const val localDateFormat = "dd/MM/yyyy"

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener =
        SafeClickListener {
            onSafeClick(it)
        }
    setOnClickListener(safeClickListener)
}
