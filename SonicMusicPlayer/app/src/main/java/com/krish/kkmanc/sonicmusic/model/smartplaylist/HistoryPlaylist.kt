package com.krish.kkmanc.sonicmusic.model.smartplaylist

import com.krish.kkmanc.sonicmusic.App
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.model.Song
import kotlinx.parcelize.Parcelize
import org.koin.core.component.KoinComponent

@Parcelize
class HistoryPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.history),
    iconRes = R.drawable.ic_history
), KoinComponent {

    override fun songs(): List<Song> {
        return topPlayedRepository.recentlyPlayedTracks()
    }
}