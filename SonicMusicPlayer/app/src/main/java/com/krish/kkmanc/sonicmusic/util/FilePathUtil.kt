package com.krish.kkmanc.sonicmusic.util

import android.os.Environment

object FilePathUtil {
    fun blacklistFilePaths(): List<String> {
        return listOf(
            getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS),
            getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES),
            getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)
        ).map {
            FileUtil.safeGetCanonicalPath(it)
        }
    }
}