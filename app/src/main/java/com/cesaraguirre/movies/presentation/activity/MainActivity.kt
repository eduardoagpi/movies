package com.cesaraguirre.movies.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.cesaraguirre.movies.R
import com.cesaraguirre.movies.presentation.adapter.TabsPagerAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Categories"

        val pagerAdapter: androidx.viewpager.widget.PagerAdapter = TabsPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val findMenuItems = menuInflater
        findMenuItems.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_find_movie -> openSearchActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openSearchActivity() {
        startActivity(FindMovieActivity.getIntent(this))
    }
}
