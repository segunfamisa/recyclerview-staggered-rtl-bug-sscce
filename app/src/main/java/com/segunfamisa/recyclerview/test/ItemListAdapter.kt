package com.segunfamisa.recyclerview.test

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemListAdapter(items: List<ListItem>,
                      private val itemClickListener: (ListItem) -> Unit) :
        RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    private val items: MutableList<ListItem> = mutableListOf()

    init {
        this.items.addAll(items)
    }

    fun removeItem(item: ListItem) {
        val oldItems = ArrayList(items)
        items.remove(item)
        val callbackResult = DiffUtil.calculateDiff(DiffUtilCallback(oldItems, items))
        callbackResult.dispatchUpdatesTo(this)
    }

    fun addItem(item: ListItem) {
        val oldItems = ArrayList(items)
        items.add(item)
        val callbackResult = DiffUtil.calculateDiff(DiffUtilCallback(oldItems, items))
        callbackResult.dispatchUpdatesTo(this)
    }

    fun addItems(items: List<ListItem>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    /**
     * ViewHolder
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView by lazy { itemView.findViewById<TextView>(R.id.text) }

        fun bind(item: ListItem) {
            textView.text = "Pos ${item.id}"
            itemView.isClickable = true
            itemView.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }

    }

    /**
     * DiffUtil Callback
     */
    inner class DiffUtilCallback(private val oldList: List<ListItem>,
                                 private val newList: List<ListItem>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                oldList[oldItemPosition] == newList[newItemPosition]

    }
}