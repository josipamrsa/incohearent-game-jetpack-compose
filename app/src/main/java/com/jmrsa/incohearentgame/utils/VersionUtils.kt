package com.jmrsa.incohearentgame.utils

import android.os.Build
import android.os.Build.VERSION_CODES

fun checkBuildVersion(version: Int = VERSION_CODES.TIRAMISU) : Boolean = Build.VERSION.SDK_INT >= version