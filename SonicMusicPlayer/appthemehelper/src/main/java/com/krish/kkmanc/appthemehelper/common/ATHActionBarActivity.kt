package com.krish.kkmanc.appthemehelper.common

import androidx.appcompat.widget.Toolbar

import com.krish.kkmanc.appthemehelper.util.ToolbarContentTintHelper

class ATHActionBarActivity : ATHToolbarActivity() {

    override fun getATHToolbar(): Toolbar? {
        return ToolbarContentTintHelper.getSupportActionBarView(supportActionBar)
    }
}
