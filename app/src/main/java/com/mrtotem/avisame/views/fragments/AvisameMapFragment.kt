package com.mrtotem.avisame.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mrtotem.avisame.R
import com.mrtotem.avisame.enums.UITypes
import com.mrtotem.avisame.views.fragments.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 */
class AvisameMapFragment : OnMapReadyCallback, BaseFragment() {

    override fun onMapReady(p0: GoogleMap?) {
        showMap(p0)
    }

    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

    private var mParam1: String? = null
    private var mParam2: String? = null
    private var map: MapView? = null

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

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        map?.onCreate(mapViewBundle)

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map?.getMapAsync(this)
    }

    override fun onResume() {
        map?.onResume()
        super.onResume()

        mHomeMvp!!.configToolbar(UITypes.WITH_BACK_BUTTON)
        mHomeMvp!!.setToolbarTitle(getString(R.string.title_map_following))
    }

    override fun onStart() {
        super.onStart()
        map?.onStart()
    }

    override fun onStop() {
        super.onStop()
        map?.onStop()
    }

    override fun onPause() {
        map?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        map?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map?.onLowMemory()
    }

    private fun showMap(map: GoogleMap?) {
        val sydney = LatLng(-32.9614853, -60.6316771)
        map?.addMarker(MarkerOptions().position(sydney)
                .title("Tracking")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)))
        map?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        map?.animateCamera(CameraUpdateFactory.zoomTo(15F), 2000, null)
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): AvisameMapFragment {
            val fragment = AvisameMapFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
