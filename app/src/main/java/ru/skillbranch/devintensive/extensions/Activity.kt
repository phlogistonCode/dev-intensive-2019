package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

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