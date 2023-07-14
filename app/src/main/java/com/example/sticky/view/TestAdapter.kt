package com.example.sticky.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sticky.view.model.Content
import com.example.sticky.view.model.Footer
import com.example.sticky.view.model.Header
import com.example.sticky.view.model.Item

class TestAdapter(
    private val items: List<Item>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            HeaderViewHolder.LAYOUT -> HeaderViewHolder(view)
            ContentViewHolder.LAYOUT -> ContentViewHolder(view)
            FooterViewHolder.LAYOUT -> FooterViewHolder(view)
            else -> throw RuntimeException("Unsupported viewType!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = items[position]) {
            is Header -> {
                (holder as HeaderViewHolder).bind(item)
            }
            is Content -> {
                (holder as ContentViewHolder).bind(item)
            }
            is Footer -> {
                (holder as FooterViewHolder).bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is Header -> HeaderViewHolder.LAYOUT
            is Content -> ContentViewHolder.LAYOUT
            is Footer -> FooterViewHolder.LAYOUT
        }
    }
}