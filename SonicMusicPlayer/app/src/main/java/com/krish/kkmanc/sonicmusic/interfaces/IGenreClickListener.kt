package com.krish.kkmanc.sonicmusic.interfaces

import android.view.View
import com.krish.kkmanc.sonicmusic.model.Genre

interface IGenreClickListener {
    fun onClickGenre(genre: Genre, view: View)
}