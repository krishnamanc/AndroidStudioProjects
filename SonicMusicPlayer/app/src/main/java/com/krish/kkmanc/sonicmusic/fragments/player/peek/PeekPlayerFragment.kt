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
package com.krish.kkmanc.sonicmusic.fragments.player.peek

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.krish.kkmanc.appthemehelper.util.ToolbarContentTintHelper
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.databinding.FragmentPeekPlayerBinding
import com.krish.kkmanc.sonicmusic.extensions.*
import com.krish.kkmanc.sonicmusic.fragments.base.AbsPlayerFragment
import com.krish.kkmanc.sonicmusic.fragments.base.goToAlbum
import com.krish.kkmanc.sonicmusic.fragments.base.goToArtist
import com.krish.kkmanc.sonicmusic.fragments.player.PlayerAlbumCoverFragment
import com.krish.kkmanc.sonicmusic.helper.MusicPlayerRemote
import com.krish.kkmanc.sonicmusic.util.PreferenceUtil
import com.krish.kkmanc.sonicmusic.util.color.MediaNotificationProcessor

/**
 * Created by hemanths on 2019-10-03.
 */

class PeekPlayerFragment : AbsPlayerFragment(R.layout.fragment_peek_player) {

    private lateinit var controlsFragment: PeekPlayerControlFragment
    private var lastColor: Int = 0
    private var _binding: FragmentPeekPlayerBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPeekPlayerBinding.bind(view)
        setUpPlayerToolbar()
        setUpSubFragments()
        binding.title.isSelected = true
        binding.title.setOnClickListener {
            goToAlbum(requireActivity())
        }
        binding.text.setOnClickListener {
            goToArtist(requireActivity())
        }
        binding.root.drawAboveSystemBarsWithPadding()
    }

    private fun setUpSubFragments() {
        controlsFragment =
            whichFragment(R.id.playbackControlsFragment) as PeekPlayerControlFragment

        val coverFragment =
            whichFragment(R.id.playerAlbumCoverFragment) as PlayerAlbumCoverFragment
        coverFragment.setCallbacks(this)
    }

    private fun setUpPlayerToolbar() {
        binding.playerToolbar.apply {
            inflateMenu(R.menu.menu_player)
            setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            setOnMenuItemClickListener(this@PeekPlayerFragment)
            ToolbarContentTintHelper.colorizeToolbar(
                this,
                colorControlNormal(),
                requireActivity()
            )
        }
    }

    override fun playerToolbar(): Toolbar {
        return binding.playerToolbar
    }

    override fun onShow() {
    }

    override fun onHide() {
    }

    override fun toolbarIconColor() = colorControlNormal()

    override val paletteColor: Int
        get() = lastColor

    override fun onColorChanged(color: MediaNotificationProcessor) {
        lastColor = color.primaryTextColor
        libraryViewModel.updateColor(color.primaryTextColor)
        controlsFragment.setColor(color)
    }

    override fun onFavoriteToggled() {
    }

    private fun updateSong() {
        val song = MusicPlayerRemote.currentSong
        binding.title.text = song.title
        binding.text.text = song.artistName

        if (PreferenceUtil.isSongInfo) {
            binding.songInfo.text = getSongInfo(song)
            binding.songInfo.show()
        } else {
            binding.songInfo.hide()
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        updateSong()
    }

    override fun onPlayingMetaChanged() {
        super.onPlayingMetaChanged()
        updateSong()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
