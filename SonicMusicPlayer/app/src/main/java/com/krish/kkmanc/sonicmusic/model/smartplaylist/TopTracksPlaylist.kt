package com.krish.kkmanc.sonicmusic.model.smartplaylist

import com.krish.kkmanc.sonicmusic.App
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
class TopTracksPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.my_top_tracks),
    iconRes = R.drawable.ic_trending_up
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.topTracks()
    }
}