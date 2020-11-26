package com.ydh.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ydh.todoapp.R
import com.ydh.todoapp.databinding.ActivityMainBinding
import com.ydh.todoapp.model.Page
import com.ydh.todoapp.view.fragment.FavoritesFragment
import com.ydh.todoapp.view.fragment.ProfileFragment
import com.ydh.todoapp.view.fragment.TodoFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    lateinit var binding: ActivityMainBinding
    lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        navigationController = this.findNavController(R.id.navHostFragment)

        setupViewPager()
        binding.navTodo.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuTodo -> binding.vpTodo.currentItem = 0
                R.id.menuFavorite -> binding.vpTodo.currentItem = 1
                R.id.menuProfile -> binding.vpTodo.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener false
        }
        setContentView(binding.root)
    }

    private fun setupViewPager() {
        binding.run {
            val pages = listOf(
                Page("Todo", TodoFragment()),
                Page("Favorite", FavoritesFragment()),
                Page("Profile", ProfileFragment()))
            val adapter = ViewPagerAdapter(pages, supportFragmentManager, lifecycle)
            vpTodo.adapter = adapter

            vpTodo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    navTodo.menu.getItem(0).isChecked = false
                    navTodo.menu.getItem(position).isChecked = true
                    menuItem = navTodo.menu.getItem(position)
                }
            })
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp()
//    }
}