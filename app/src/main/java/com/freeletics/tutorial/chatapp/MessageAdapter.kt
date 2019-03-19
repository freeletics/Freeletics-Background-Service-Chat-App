package com.freeletics.tutorial.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_message.*

class ChatAdapter(private val inflater: LayoutInflater, private val glide: Glide) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_MESSAGE = R.layout.item_chat_message
    private val VIEW_TYPE_IMAGE = R.layout.item_chat_image

    lateinit var messages: List<Message>

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
                .load("")
        }
    }
}