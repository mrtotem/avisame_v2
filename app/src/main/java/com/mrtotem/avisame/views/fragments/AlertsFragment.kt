package com.mrtotem.avisame.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrtotem.avisame.R
import com.mrtotem.avisame.views.fragments.base.TabFragment

class AlertsFragment : TabFragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    private lateinit var mArrivedButton: View
    private lateinit var mAlertButton: View
    private lateinit var mDangerButton: View
    private lateinit var mTrackingButton: View

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
        val v: View = inflater!!.inflate(R.layout.fragment_alerts_v2, container, false) as View

        mArrivedButton = v.findViewById<View>(R.id.arrived_button)
        mAlertButton = v.findViewById<View>(R.id.alert_button)
        mDangerButton = v.findViewById<View>(R.id.danger_button)
        mTrackingButton = v.findViewById<View>(R.id.tracking_button)

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTrackingButton.setOnClickListener {
            activity.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_popup_exit)
                    .addToBackStack("map_fragment")
                    .replace(R.id.main_content, AvisameMapFragment.newInstance("", ""), "map_fragment")
                    .commit()
        }
    }

    override fun onResume() {
        super.onResume()

        mHomeMvp?.setToolbarTitle(getString(R.string.title_alerts))
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): AlertsFragment {
            val fragment = AlertsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
