package com.mrtotem.avisame.views.fragments


import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
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
import com.mrtotem.avisame.views.activities.MainActivity
import com.mrtotem.avisame.widgets.AvisameTextView

class RegisterFragment : Fragment() {

    lateinit var mTermsAndConditions: AvisameTextView
    lateinit var mRegister: Button

    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater!!.inflate(R.layout.fragment_register, container, false)

        mTermsAndConditions = v.findViewById<AvisameTextView>(R.id.terms_conditions) as AvisameTextView
        mRegister = v.findViewById<Button>(R.id.button_register) as Button

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        val sb = SpannableStringBuilder("Al registrarme acepto términos y condiciones")
        val fcs = ForegroundColorSpan(resources.getColor(R.color.colorToolbar))
        val bss = StyleSpan(Typeface.NORMAL)
        sb.setSpan(fcs, 22, mTermsAndConditions.text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        sb.setSpan(bss, 22, mTermsAndConditions.text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        mTermsAndConditions.text = sb

        mTermsAndConditions.setOnClickListener({
            startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        })
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): RegisterFragment {
            val fragment = RegisterFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}
