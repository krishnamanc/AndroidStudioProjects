package com.krish.kkmanc.sonicmusic.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.krish.kkmanc.sonicmusic.R
import com.krish.kkmanc.sonicmusic.databinding.DialogPlaybackSpeedBinding
import com.krish.kkmanc.sonicmusic.extensions.accent
import com.krish.kkmanc.sonicmusic.extensions.colorButtons
import com.krish.kkmanc.sonicmusic.extensions.materialDialog
import com.krish.kkmanc.sonicmusic.util.PreferenceUtil
import com.google.android.material.slider.Slider

class PlaybackSpeedDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogPlaybackSpeedBinding.inflate(layoutInflater)
        binding.playbackSpeedSlider.accent()
        binding.playbackPitchSlider.accent()
        binding.playbackSpeedSlider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            binding.speedValue.text = "$value"
        })
        binding.playbackPitchSlider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            binding.pitchValue.text = "$value"
        })
        binding.playbackSpeedSlider.value = PreferenceUtil.playbackSpeed
        binding.playbackPitchSlider.value = PreferenceUtil.playbackPitch

        return materialDialog(R.string.playback_settings)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(R.string.save) { _, _ ->
                updatePlaybackAndPitch(
                    binding.playbackSpeedSlider.value,
                    binding.playbackPitchSlider.value
                )
            }
            .setNeutralButton(R.string.reset_action) {_, _ ->
                updatePlaybackAndPitch(
                    1F,
                    1F
                )
            }
            .setView(binding.root)
            .create()
            .colorButtons()
    }

    private fun updatePlaybackAndPitch(speed: Float, pitch: Float) {
        PreferenceUtil.playbackSpeed = speed
        PreferenceUtil.playbackPitch = pitch
    }

    companion object {
        fun newInstance(): PlaybackSpeedDialog {
            return PlaybackSpeedDialog()
        }
    }
}