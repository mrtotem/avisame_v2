package com.mrtotem.avisame.views.fragments


import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mrtotem.avisame.R
import com.mrtotem.avisame.widgets.AvisameEditView
import com.mrtotem.avisame.widgets.AvisameTextView

class RegisterFragment : Fragment() {

    lateinit var mTermsAndConditions: AvisameTextView
    lateinit var mRegister: Button
    lateinit var mUsername: AvisameEditView
    lateinit var mEmail: AvisameEditView
    lateinit var mPassword: AvisameEditView
    lateinit var mPasswordConfirm: AvisameEditView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater!!.inflate(R.layout.fragment_register, container, false)

        mTermsAndConditions = v.findViewById(R.id.terms_conditions) as AvisameTextView
        mUsername = v.findViewById(R.id.username) as AvisameEditView
        mEmail = v.findViewById(R.id.email) as AvisameEditView
        mPassword = v.findViewById(R.id.password) as AvisameEditView
        mPasswordConfirm = v.findViewById(R.id.repeat_password) as AvisameEditView
        mRegister = v.findViewById(R.id.button_register) as Button

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        val sb = SpannableStringBuilder(mTermsAndConditions.text)
        sb.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorToolbar)),
                22, mTermsAndConditions.text.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        sb.setSpan(StyleSpan(Typeface.NORMAL),
                0, 21,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        mTermsAndConditions.text = sb

        mTermsAndConditions.setOnClickListener {
            this.view?.let { view ->
                Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
                        .show()
            }
        }
    }
}
