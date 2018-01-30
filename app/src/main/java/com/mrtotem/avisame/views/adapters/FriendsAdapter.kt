package com.mrtotem.avisame.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.mrtotem.avisame.R
import com.mrtotem.avisame.utils.ImageUtils
import com.mrtotem.avisame.widgets.AvisameTextView

/**
 * Created by Octavio on 29/01/2018.
 */
class FriendsAdapter(val context: Context, val userList: ArrayList<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ADD_FRIEND: Int = 0
    val FRIEND: Int = 1

    var rows: ArrayList<Any>? = null

    fun configRows() {

        rows = arrayListOf()
        rows?.add(AddFriendView())
        rows?.add(FriendView("Octi Danieli", false, ""))
        rows?.add(FriendView("Pau Bornacin", true, ""))
        rows?.add(FriendView("Homero Simpson", false, ""))
        rows?.add(FriendView("Pedro", false, ""))
        rows?.add(FriendView("Christian Bale", false, ""))
        rows?.add(FriendView("Obi Wan Kenobi", false, ""))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ADD_FRIEND -> AddFriendVH(LayoutInflater.from(parent?.context).inflate(R.layout.row_add_friend, parent, false))
            else -> FriendVH(LayoutInflater.from(parent?.context).inflate(R.layout.row_friend, parent, false), this)
        }
    }

    override fun getItemCount(): Int {
        return rows?.size!!
    }

    override fun getItemViewType(position: Int): Int {

        if (rows?.get(position) is AddFriendView) {
            return ADD_FRIEND
        }
        return FRIEND
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when (getItemViewType(position)) {
            ADD_FRIEND ->
                (holder as AddFriendVH).setupAddFriendVH()
            FRIEND ->
                (holder as FriendVH).setupFriendVH(
                        context,
                        (rows?.get(position) as FriendView).name,
                        (rows?.get(position) as FriendView).isSelected,
                        position)
        }
    }

    class FriendVH(v: View, private val adapter: FriendsAdapter) : RecyclerView.ViewHolder(v) {

        var friendName: AvisameTextView = v.findViewById<AvisameTextView>(R.id.friend_name) as AvisameTextView
        var avatar: SimpleDraweeView = v.findViewById<SimpleDraweeView>(R.id.friend_avatar) as SimpleDraweeView
        var selected: ImageView = v.findViewById(R.id.selected)

        fun setupFriendVH(context: Context, name: String?, isSelected: Boolean, position: Int) {

            avatar.setOnClickListener({
                adapter.notifiyNewSelection(position)
            })

            friendName.text = name

            if (isSelected) {
                friendName.setTextColor(context.resources.getColor(R.color.colorTabElementSelected))
                selected.visibility = View.VISIBLE
            } else {
                friendName.setTextColor(context.resources.getColor(R.color.colorTextDark))
                selected.visibility = View.GONE
            }
            avatar.controller = ImageUtils().processImage(null, 128, 128)
        }
    }

    class AddFriendVH(v: View) : RecyclerView.ViewHolder(v) {

        fun setupAddFriendVH() {

        }
    }

    class FriendView(friendName: String?, var isFriendSelected: Boolean, friendAvatar: String?) {

        var name: String? = friendName
        var isSelected: Boolean = isFriendSelected
        var avatar: String? = friendAvatar
    }

    class AddFriendView {}

    fun notifiyNewSelection(position: Int) {

        rows!!
                .filterIsInstance<FriendView>()
                .filter { rows!!.indexOf(it) != position }
                .forEach { it.isSelected = false }

        if (rows!![position] is FriendView) {
            (rows!![position] as FriendView).isSelected = true
        }

        notifyDataSetChanged()
    }
}