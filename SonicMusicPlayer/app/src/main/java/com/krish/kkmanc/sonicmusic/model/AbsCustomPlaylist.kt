package com.krish.kkmanc.sonicmusic.model

import com.krish.kkmanc.sonicmusic.repository.LastAddedRepository
import com.krish.kkmanc.sonicmusic.repository.SongRepository
import com.krish.kkmanc.sonicmusic.repository.TopPlayedRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class AbsCustomPlaylist(
    id: Long,
    name: String
) : Playlist(id, name), KoinComponent {

    abstract fun songs(): List<Song>

    protected val songRepository by inject<SongRepository>()

    protected val topPlayedRepository by inject<TopPlayedRepository>()

    protected val lastAddedRepository by inject<LastAddedRepository>()
}