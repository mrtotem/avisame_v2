package com.mrtotem.avisame.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.mrtotem.avisame.R


/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null
    private lateinit var map: MapView
    private var googleMap: GoogleMap? = null

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
        val v: View = inflater!!.inflate(R.layout.fragment_map, container, false) as View

        map = v.findViewById<MapView>(R.id.map) as MapView

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map.getMapAsync { p0 -> showMap(p0) }
    }

    private fun showMap(map: GoogleMap){

        map.
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): MapFragment {
            val fragment = MapFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
