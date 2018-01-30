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
import org.joda.time.DateTime
import com.mrtotem.avisame.widgets.AvisameTextView
import java.util.*

/**
 * Created by Octavio on 29/01/2018.
 */
class MessagesAdapter(val context: Context, val userList: ArrayList<String>?) : RecyclerView.Adapter<MessagesAdapter.MessageVH>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MessageVH {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_message, parent, false)
        return MessageVH(v)
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(holder: MessageVH?, position: Int) {

        holder!!.setupMessageVH("llegue bieen! el taxista me acepto la promo!")
    }

    class MessageVH(v: View) : RecyclerView.ViewHolder(v) {

        var messageTitle: AvisameTextView = v.findViewById<AvisameTextView>(R.id.message_title) as AvisameTextView
        var message: AvisameTextView = v.findViewById<AvisameTextView>(R.id.message_description) as AvisameTextView
        var messageDate: AvisameTextView = v.findViewById<AvisameTextView>(R.id.message_date) as AvisameTextView
        var avatar: SimpleDraweeView = v.findViewById<SimpleDraweeView>(R.id.user_avatar) as SimpleDraweeView
        var delete: ImageView = v.findViewById<ImageView>(R.id.delete_button) as ImageView

        fun setupMessageVH(newMessage: String) {

            delete.setOnClickListener {

            }

            messageTitle.text = "Mensaje de llegada"
            message.text = String.format(Locale.US, "Mensaje: %s", newMessage)
            messageDate.text = String.format(Locale.US, "Fecha: %s", DateTime().toString("dd-mm-yyyy hh:mm aaa"))
            avatar.controller = ImageUtils().processImage(null, 128, 128)
        }
    }
}