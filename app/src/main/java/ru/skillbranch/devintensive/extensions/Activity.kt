package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val keyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyBoard.hideSoftInputFromWindow(view.windowToken, 0)
    } else {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}

fun Activity.isKeyboardOpen() {
    val view = this.currentFocus
    if (view != null) {
        val keyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyBoard.hideSoftInputFromWindow(view.windowToken, 0)
    } else {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}

fun Activity.isKeyboardClosed() {
    val view = this.currentFocus
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.requestFocus()
    imm.showSoftInput(view, 0)
}
