package widgets

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.mrtotem.avisame.R

/**
 * Created by Octavio on 26/01/2018.
 */
class AvisameTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    init {
        val typeface = ResourcesCompat.getFont(context, R.font.neoteric)
        setTypeface(typeface, Typeface.BOLD)
    }
}