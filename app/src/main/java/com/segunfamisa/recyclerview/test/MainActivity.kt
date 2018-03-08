package com.segunfamisa.recyclerview.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private val itemCount = 140

    private lateinit var itemAdapter: ItemListAdapter

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.refresh, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                reloadItems()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView() {
        itemAdapter = ItemListAdapter(generateItems(itemCount), { item ->
            itemAdapter.removeItem(item)
        })

        with(recyclerView) {
            adapter = itemAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.HORIZONTAL)
        }
    }

    private fun generateItems(count: Int): List<ListItem> {
        val items = mutableListOf<ListItem>()
        repeat(count, {
            items.add(ListItem(it))
        })
        return items
    }

    private fun reloadItems() {
        itemAdapter.addItems(generateItems(itemCount))
    }
}
