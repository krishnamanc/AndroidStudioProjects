package com.krish.kkmanc.sonicmusic.model.smartplaylist

import com.krish.kkmanc.sonicmusic.App
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
class NotPlayedPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.not_recently_played),
    iconRes = R.drawable.ic_audiotrack
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.notRecentlyPlayedTracks()
    }
}