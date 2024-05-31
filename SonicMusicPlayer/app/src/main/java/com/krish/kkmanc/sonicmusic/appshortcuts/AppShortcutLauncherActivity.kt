/*
 * Copyright (c) 2020 Hemanth Savarla.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
package com.krish.kkmanc.sonicmusic.appshortcuts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.krish.kkmanc.sonicmusic.appshortcuts.shortcuttype.LastAddedShortcutType
import com.krish.kkmanc.sonicmusic.appshortcuts.shortcuttype.ShuffleAllShortcutType
import com.krish.kkmanc.sonicmusic.appshortcuts.shortcuttype.TopTracksShortcutType
import com.krish.kkmanc.sonicmusic.extensions.extraNotNull
import com.krish.kkmanc.sonicmusic.model.Playlist
import com.krish.kkmanc.sonicmusic.model.smartplaylist.LastAddedPlaylist
import com.krish.kkmanc.sonicmusic.model.smartplaylist.ShuffleAllPlaylist
import com.krish.kkmanc.sonicmusic.model.smartplaylist.TopTracksPlaylist
import com.krish.kkmanc.sonicmusic.service.MusicService
import com.krish.kkmanc.sonicmusic.service.MusicService.Companion.ACTION_PLAY_PLAYLIST
import com.krish.kkmanc.sonicmusic.service.MusicService.Companion.INTENT_EXTRA_PLAYLIST
import com.krish.kkmanc.sonicmusic.service.MusicService.Companion.INTENT_EXTRA_SHUFFLE_MODE
import com.krish.kkmanc.sonicmusic.service.MusicService.Companion.SHUFFLE_MODE_NONE
import com.krish.kkmanc.sonicmusic.service.MusicService.Companion.SHUFFLE_MODE_SHUFFLE

class AppShortcutLauncherActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (extraNotNull(KEY_SHORTCUT_TYPE, SHORTCUT_TYPE_NONE).value) {
            SHORTCUT_TYPE_SHUFFLE_ALL -> {
                startServiceWithPlaylist(
                    SHUFFLE_MODE_SHUFFLE, ShuffleAllPlaylist()
                )
                DynamicShortcutManager.reportShortcutUsed(this, ShuffleAllShortcutType.id)
            }
            SHORTCUT_TYPE_TOP_TRACKS -> {
                startServiceWithPlaylist(
                    SHUFFLE_MODE_NONE, TopTracksPlaylist()
                )
                DynamicShortcutManager.reportShortcutUsed(this, TopTracksShortcutType.id)
            }
            SHORTCUT_TYPE_LAST_ADDED -> {
                startServiceWithPlaylist(
                    SHUFFLE_MODE_NONE, LastAddedPlaylist()
                )
                DynamicShortcutManager.reportShortcutUsed(this, LastAddedShortcutType.id)
            }
        }
        finish()
    }

    private fun startServiceWithPlaylist(shuffleMode: Int, playlist: Playlist) {
        val intent = Intent(this, MusicService::class.java)
        intent.action = ACTION_PLAY_PLAYLIST

        val bundle = bundleOf(
            INTENT_EXTRA_PLAYLIST to playlist,
            INTENT_EXTRA_SHUFFLE_MODE to shuffleMode
        )

        intent.putExtras(bundle)

        startService(intent)
    }

    companion object {
        const val KEY_SHORTCUT_TYPE = "com.krish.kkmanc.sonicmusic.appshortcuts.ShortcutType"
        const val SHORTCUT_TYPE_SHUFFLE_ALL = 0L
        const val SHORTCUT_TYPE_TOP_TRACKS = 1L
        const val SHORTCUT_TYPE_LAST_ADDED = 2L
        const val SHORTCUT_TYPE_NONE = 4L
    }
}
