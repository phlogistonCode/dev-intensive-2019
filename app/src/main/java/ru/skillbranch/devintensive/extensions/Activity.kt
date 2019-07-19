package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.MeasureSpec
import android.widget.FrameLayout
import android.widget.LinearLayout


class SoftKeyboardDetector : LinearLayout {

    private var listener: SoftKeyboardListener? = null

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
    }

    constructor(context: Context) : super(context) {}

    fun setSoftKeyboardListener(listener: SoftKeyboardListener) {
        this.listener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val activity = context as Activity
        val rect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(rect)
        val visibleDisplayFrameHeight = rect.top
        val screenHeight = activity.windowManager.defaultDisplay
            .height
        val diff = screenHeight - visibleDisplayFrameHeight - height
        if (listener != null) {
            if (diff > 100) {
                listener!!.onSoftKeyboardShow()
            } else {
                listener!!.onSoftKeyboardHide()
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    interface SoftKeyboardListener {
        fun onSoftKeyboardShow()
        fun onSoftKeyboardHide()
    }
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(et_message.windowToken, 0)
}

fun Activity.isKeyboardOpen() : Boolean {
    val screenHeight = this.windowManager.defaultDisplay.height
    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)
    val keypadHeight = rect.height()
    return screenHeight-keypadHeight > 100
}


fun Activity.isKeyboardClosed() : Boolean {
    val screenHeight = this.windowManager.defaultDisplay.height
    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)
    val keypadHeight = rect.height()
    return screenHeight-keypadHeight <= 100
}