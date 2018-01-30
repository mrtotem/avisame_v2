package com.mrtotem.avisame.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.mrtotem.avisame.R
import com.mrtotem.avisame.enums.NavOptions
import com.mrtotem.avisame.utils.ImageUtils
import com.mrtotem.avisame.widgets.AvisameTextView

/**
 * Created by Octavio on 29/01/2018.
 */
class NavigationViewAdapter(val context: Context, val listener: OnNavigationClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val HEADER: Int = 0
    val CONTENT: Int = 1
    val FOOTER: Int = 2

    lateinit var rows: ArrayList<Any>

    fun configNavView() {

        rows = arrayListOf()
        rows.add(NavHeader("Octi Danieli", ""))
        rows.add(NavItem("Perfil", R.mipmap.profile_icon, NavOptions.PROFILE))
        rows.add(NavItem("Logout", R.mipmap.logout_icon, NavOptions.LOGOUT))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            HEADER -> NavHeaderHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row_nav_header, parent, false))
            FOOTER -> NavFooterHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row_nav_footer, parent, false))
            else -> NavItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row_nav_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    override fun getItemViewType(position: Int): Int {

        if (rows[position] is NavHeader) {
            return HEADER
        }

        if (rows[position] is NavFooter) {
            return FOOTER
        }

        return CONTENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when (getItemViewType(position)) {
            HEADER -> {
                val data = rows[position] as NavHeader
                ((holder as NavHeaderHolder).setupHeader(data.mUsername, data.mAvatarRes))
            }

            FOOTER -> {

            }

            CONTENT -> {
                val data = rows[position] as NavItem
                ((holder as NavItemHolder).setupHeader(context, data.title, data.iconRes, data.option, listener))
            }
        }
    }

    class NavHeader(username: String, avatarRes: String) {

        var mUsername = username
        var mAvatarRes = avatarRes
    }

    class NavFooter {}

    class NavItem(title: String, iconRes: Int, option: NavOptions) {

        var title = title
        var iconRes = iconRes
        var option = option
    }

    class NavHeaderHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mAvatar: SimpleDraweeView = view.findViewById<SimpleDraweeView>(R.id.avatar) as SimpleDraweeView
        var mUsername: AvisameTextView = view.findViewById<AvisameTextView>(R.id.user_name) as AvisameTextView

        fun setupHeader(username: String, avatarRes: String) {

            mUsername.text = username
            mAvatar.controller = ImageUtils().processImage(null, 128, 128)
        }
    }

    class NavFooterHolder(view: View) : RecyclerView.ViewHolder(view) {}

    class NavItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mClickeable: View = view.findViewById(R.id.clickeable)
        var mItemIcon: ImageView = view.findViewById<ImageView>(R.id.nav_icon) as ImageView
        var mItemLabel: AvisameTextView = view.findViewById<AvisameTextView>(R.id.nav_label) as AvisameTextView

        fun setupHeader(context: Context, label: String, avatarRes: Int, option: NavOptions, listener: OnNavigationClickListener) {

            mItemIcon.setImageDrawable(context.getDrawable(avatarRes))
            mItemLabel.text = label
            mClickeable.setOnClickListener {
                listener.onClickListener(option)
            }
        }
    }

    interface OnNavigationClickListener {

        fun onClickListener(option: NavOptions)
    }
}