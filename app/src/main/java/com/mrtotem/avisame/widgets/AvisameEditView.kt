package com.mrtotem.avisame.widgets

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import com.mrtotem.avisame.R

/**
 * Created by Octavio on 26/01/2018.
 */
class AvisameEditView(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    init {
        val typeface = ResourcesCompat.getFont(context, R.font.bowhouse_bold)
        setTypeface(typeface, Typeface.NORMAL)
    }
}