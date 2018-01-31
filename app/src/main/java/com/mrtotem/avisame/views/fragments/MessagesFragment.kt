package com.mrtotem.avisame.views.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mrtotem.avisame.R
import com.mrtotem.avisame.views.adapters.MessagesAdapter
import com.mrtotem.avisame.views.fragments.base.BaseFragment

class MessagesFragment : BaseFragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    private lateinit var mMessageList: RecyclerView
    private lateinit var mMessagesAdapter: MessagesAdapter

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
        val v: View = inflater!!.inflate(R.layout.fragment_messages, container, false) as View

        mMessageList = v.findViewById<RecyclerView>(R.id.messages_list) as RecyclerView

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()
    }

    override fun onResume() {
        super.onResume()
        mHomeMvp?.setToolbarTitle(getString(R.string.title_messages))

        refreshAdapter()
    }

    private fun configRecycler() {

        mMessageList.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
    }

    private fun refreshAdapter() {

        mMessagesAdapter = MessagesAdapter(activity)
        mHomeMvp?.getMessagePresenter()?.registerMessageListener(mMessagesAdapter)
        mMessageList.adapter = mMessagesAdapter

    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): MessagesFragment {
            val fragment = MessagesFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
