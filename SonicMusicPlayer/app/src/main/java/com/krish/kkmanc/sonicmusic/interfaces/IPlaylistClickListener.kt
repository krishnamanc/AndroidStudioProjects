package com.krish.kkmanc.sonicmusic.interfaces

import android.view.View
import com.krish.kkmanc.sonicmusic.db.PlaylistWithSongs

interface IPlaylistClickListener {
    fun onPlaylistClick(playlistWithSongs: PlaylistWithSongs, view: View)
}