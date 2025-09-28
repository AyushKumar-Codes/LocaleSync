package io.github.ayushkumar.localSync.presentation

import android.content.Context
import dagger.hilt.android.EntryPointAccessors

fun Context.getLocalizedString(key: String, vararg args: Any): String {
    val entryPoint = EntryPointAccessors.fromApplication(
        this,
        LocalizationEntryPoint::class.java
    )
    return entryPoint.bsStringManager().getString(key, *args)
}