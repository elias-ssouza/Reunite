package com.example.reunite

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.time.Month


class ProductDetailActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var calendarView: CalendarView
    lateinit var textTitle: TextView
    lateinit var mShortMonth: Month
    lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.my_schedule_productdetail)

        floatingActionButton = findViewById (R.id.add_floating_button)
       //floatingActionButton.setOnClickListener(new View.OnClickListener();
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}