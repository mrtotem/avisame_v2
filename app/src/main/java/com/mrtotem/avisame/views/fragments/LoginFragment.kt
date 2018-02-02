package com.mrtotem.avisame.views.fragments


import android.content.Intent
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

class LoginFragment : Fragment() {

    lateinit var mRegister: AvisameTextView
    lateinit var mLogin: Button

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
        val v: View = inflater!!.inflate(R.layout.fragment_login, container, false)

        mRegister = v.findViewById<AvisameTextView>(R.id.button_register) as AvisameTextView
        mLogin = v.findViewById<Button>(R.id.button_login) as Button

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    fun setupView() {

        val sb = SpannableStringBuilder("No tenés una cuenta? Registrate acá")
        val fcs = ForegroundColorSpan(resources.getColor(R.color.colorToolbar))
        val bss = StyleSpan(android.graphics.Typeface.NORMAL)
        sb.setSpan(fcs, 21, mRegister.text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(bss, 21, mRegister.text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mRegister.text = sb

        mLogin.setOnClickListener({
            startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        })
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
