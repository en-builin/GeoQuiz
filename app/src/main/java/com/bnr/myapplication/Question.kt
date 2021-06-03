package com.bnr.myapplication

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)