package com.example.d2android100.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.d2android100.R
import com.example.d2android100.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopListAdapter.VH>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val count = view.findViewById<TextView>(R.id.id1)
        val name = view.findViewById<TextView>(R.id.name)

        fun bind(item: ShopItem, position: Int) {
            name.text = "${item.name}"
            count.text = item.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        var layout = when (viewType) {
            ENABLED_VIEW -> R.layout.item_view_enabled
            DISABLED_VIEW -> R.layout.item_view_disabled
            else -> throw RuntimeException("Unkown viewType $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position), position)

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(getItem(position))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getItemType(position)
    }

    private fun getItemType(position: Int):Int {
        return  if (getItem(position).enabled) {
            ENABLED_VIEW
        } else {
            DISABLED_VIEW
        }
    }


    companion object {
        const val ENABLED_VIEW = 0
        const val DISABLED_VIEW = 1
        const val POOL_SIZE = 15
    }
}
