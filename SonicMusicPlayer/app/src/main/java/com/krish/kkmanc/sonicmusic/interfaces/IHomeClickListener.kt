package com.krish.kkmanc.sonicmusic.interfaces

import com.krish.kkmanc.sonicmusic.model.Album
import com.krish.kkmanc.sonicmusic.model.Artist
import com.krish.kkmanc.sonicmusic.model.Genre

interface IHomeClickListener {
    fun onAlbumClick(album: Album)

    fun onArtistClick(artist: Artist)

    fun onGenreClick(genre: Genre)
}