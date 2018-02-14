package com.mrtotem.avisame.views.fragments

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrtotem.avisame.R
import com.mrtotem.avisame.views.adapters.FriendsAdapter
import com.mrtotem.avisame.views.fragments.base.TabFragment

class FriendsFragment : TabFragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    private lateinit var mFriendsList: RecyclerView

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
        val v: View = inflater!!.inflate(R.layout.fragment_friends, container, false) as View

        mFriendsList = v.findViewById<RecyclerView>(R.id.friends_list) as RecyclerView

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()
    }

    override fun onResume() {
        super.onResume()
        mHomeMvp?.setToolbarTitle(getString(R.string.title_friends))

        refreshAdapter()
    }

    private fun configRecycler() {
        mFriendsList.layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
    }

    private fun refreshAdapter() {

        val messages: ArrayList<String>? = null
        var adapter = FriendsAdapter(activity, messages)
        mFriendsList.adapter = adapter
        adapter.configRows()
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): FriendsFragment {
            val fragment = FriendsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
