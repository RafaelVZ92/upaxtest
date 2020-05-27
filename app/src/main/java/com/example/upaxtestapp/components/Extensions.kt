package com.example.upaxtestapp.components

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.util.Base64
import android.util.Base64.decode
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.Normalizer
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val passwordDot = '\u2022'
private const val serverDateFormat = "yyyy-MM-dd"
private const val localDateFormat = "dd/MM/yyyy"

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
