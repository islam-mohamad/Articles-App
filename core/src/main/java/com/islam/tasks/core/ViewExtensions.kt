package com.islam.tasks.core

import android.view.View


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.hide(gone: Boolean) {
    visibility = if (gone) View.GONE else View.VISIBLE
}