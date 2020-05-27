package com.example.upaxtestapp.components

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.res.ResourcesCompat
import com.example.upaxtestapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class InputFieldCustom :
    TextInputLayout {
    lateinit var editText: TextView
    private lateinit var textViewError: TextView
    private lateinit var textViewHelp: TextView
    private val compoundDrawablePadding = 16
    private var backgroundNormal = 0
    private var backgroundFull = 0
    private var backgroundError = 0
    private var isValid = true

    constructor(context: Context) : super(context) {
        createBackground(context)
        createEditText(context)
        createTextViewError(context)
        createTextViewHelp(context)
        setFonts(context)

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        createBackground(context, attrs)
        createEditText(context, attrs)
        createTextViewError(context, attrs)
        createTextViewHelp(context, attrs)
        setFonts(context)

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        createBackground(context, attrs)
        createEditText(context, attrs)
        createTextViewError(context, attrs)
        createTextViewHelp(context, attrs)
        setFonts(context)
    }

    private fun createBackground(context: Context, attrs: AttributeSet? = null) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.InputFieldCustom)
        backgroundNormal = a.getResourceId(R.styleable.InputFieldCustom_backgroundNormal, 0)
        backgroundFull =
            a.getResourceId(R.styleable.InputFieldCustom_backgroundFull, backgroundNormal)
        backgroundError = a.getResourceId(R.styleable.InputFieldCustom_backgroundError, 0)
        this.setBackgroundResource(backgroundNormal)

        a.recycle()
    }

    private fun createEditText(context: Context, attrs: AttributeSet? = null) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.InputFieldCustom)
        val inputStyle = a.getResourceId(R.styleable.InputFieldCustom_inputStyle, 0)
        val newContext = if (inputStyle != 0) ContextThemeWrapper(context, inputStyle) else context

        editText = TextInputEditText(newContext)
        editText.background = null
        editText.text = a.getString(R.styleable.InputFieldCustom_inputText)
        editText.setTextColor(
            a.getColor(
                R.styleable.InputFieldCustom_inputTextColor,
                resources.getColor(R.color.colorPrimary, resources.newTheme())
            )
        )
        editText.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            a.getDimension(R.styleable.InputFieldCustom_inputTextSize, 0f)
        )
        editText.setCompoundDrawablesWithIntrinsicBounds(
            a.getDrawable(R.styleable.InputFieldCustom_inputDrawableLeft),
            null,
            a.getDrawable(R.styleable.InputFieldCustom_inputDrawableRight),
            null
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            editText.importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO
        }
        editText.compoundDrawablePadding = compoundDrawablePadding
        editText.setTextAppearance(a.getResourceId(R.styleable.InputFieldCustom_inputStyle, 0))

        this.addView(editText)
        a.recycle()
    }

    private fun createTextViewError(context: Context, attrs: AttributeSet? = null) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.InputFieldCustom)
        val inputStyle = a.getResourceId(R.styleable.InputFieldCustom_errorStyle, 0)
        val newContext = if (inputStyle != 0) ContextThemeWrapper(context, inputStyle) else context

        textViewError = TextView(newContext)
        textViewError.visibility = View.GONE
        this.addView(textViewError)
        a.recycle()
    }

    private fun createTextViewHelp(context: Context, attrs: AttributeSet? = null) {
        textViewHelp = TextView(context)

        val a = context.obtainStyledAttributes(attrs, R.styleable.InputFieldCustom)
        textViewHelp.text = a.getString(R.styleable.InputFieldCustom_helpText)
        textViewHelp.visibility = if (textViewHelp.text.isNullOrEmpty()) View.GONE else View.VISIBLE
        textViewHelp.setTextColor(
            a.getColor(
                R.styleable.InputFieldCustom_helpTextColor,
                resources.getColor(R.color.colorPrimary, resources.newTheme())
            )
        )
        textViewHelp.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            a.getDimension(R.styleable.InputFieldCustom_helpTextSize, 0f)
        )
        textViewHelp.setCompoundDrawablesWithIntrinsicBounds(
            a.getDrawable(R.styleable.InputFieldCustom_helpDrawableLeft),
            null,
            a.getDrawable(R.styleable.InputFieldCustom_helpDrawableRight),
            null
        )
        textViewHelp.compoundDrawablePadding = compoundDrawablePadding

        this.addView(textViewHelp)
        a.recycle()
    }

    private fun setFonts(context: Context) {
        val typefaceBook = ResourcesCompat.getFont(context, R.font.itc_fg_std_book)
        val typefaceMed = ResourcesCompat.getFont(context, R.font.itc_fg_std_med)
        this.typeface = typefaceMed
        this.editText.typeface = typefaceMed
        this.textViewHelp.typeface = typefaceBook
        this.textViewError.typeface = typefaceBook
    }

    private fun validateBackground() {
        if (isValid) {
            if (editText.hasFocus()) {
                setBackgroundResource(backgroundNormal)
            } else if (!editText.text.isNullOrEmpty()) {
                setBackgroundResource(backgroundFull)
            } else {
                setBackgroundResource(backgroundNormal)
            }
        } else {
            setBackgroundResource(backgroundError)
        }

    }

    override fun setError(errorText: CharSequence?) {
        if (!TextUtils.isEmpty(errorText)) {
            textViewError.text = errorText
            textViewError.visibility = View.VISIBLE
            this.setBackgroundResource(backgroundError)
            this.editText.setHintTextColor(context.getColor(R.color.dark_hot_red))
            isValid = false

        } else {
            textViewError.text = errorText
            textViewError.visibility = View.GONE
            this.setBackgroundResource(backgroundNormal)
            this.editText.setHintTextColor(context.getColor(R.color.bright_blue))
            isValid = true
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.isCursorVisible = false
        editText.isClickable = true
        editText.setSafeOnClickListener { l?.onClick(editText) }
    }

    fun validateStyle() {
        GlobalScope.launch {
            delay(100)
            defaultHintTextColor = if (isValid) {
                if (editText.hasFocus()) {
                    ColorStateList.valueOf(context.getColor(R.color.bright_blue))
                } else if (!editText.text.isNullOrEmpty()) {
                    ColorStateList.valueOf(context.getColor(R.color.bright_blue))
                } else {
                    ColorStateList.valueOf(context.getColor(R.color.black_two))
                }
            } else {
                if (editText.hasFocus() || !editText.text.isNullOrEmpty()) {
                    ColorStateList.valueOf(context.getColor(R.color.dark_hot_red))
                } else {
                    ColorStateList.valueOf(context.getColor(R.color.black_two))
                }
            }
        }
        validateBackground()
    }

    fun isValid(): Boolean {
        return isValid
    }
}

