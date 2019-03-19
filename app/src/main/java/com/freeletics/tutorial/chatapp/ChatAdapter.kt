package com.freeletics.tutorial.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_image.*
import kotlinx.android.synthetic.main.item_chat_message.*
import java.lang.RuntimeException

class ChatAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_MESSAGE = 0
    private val VIEW_TYPE_IMAGE = 1

    var messages: MutableList<Message> = ArrayList()

    override fun getItemViewType(position: Int): Int = when (messages[position]) {
        is Message.Text -> VIEW_TYPE_MESSAGE
        is Message.Image -> VIEW_TYPE_IMAGE
    }

    override fun getItemCount(): Int = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_MESSAGE -> MessageViewHolder(inflater.inflate(R.layout.item_chat_message, parent, false))
            VIEW_TYPE_IMAGE -> MessageViewHolder(
                inflater.inflate(R.layout.item_chat_image, parent, false)
            )
            else -> throw RuntimeException("Try to create ViewHolder for unknown type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: Message = messages[position]
        when (item) {
            is Message.Text -> (holder as MessageViewHolder).bind(item)
            is Message.Image -> (holder as ImageViewHolder).bind(item)
        }
    }

    private inner class MessageViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(msg: Message.Text) {
            text.text = msg.text
        }
    }

    private inner class ImageViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(msg: Message.Image) {
            Glide.with(containerView.context)
                .load(UploadClient.url + "/" + msg.imageUrl)
                .placeholder(R.color.material_grey_300)
                .error(R.color.material_grey_300)
                .into(image)
        }
    }
}