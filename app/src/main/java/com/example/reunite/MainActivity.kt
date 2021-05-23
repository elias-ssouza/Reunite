package com.example.reunite

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import com.google.android.material.navigation.NavigationView
import android.widget.CalendarView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var imageProfile: ImageView
    lateinit var calendarView: CalendarView
    lateinit var textTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "The selected date is $p3.${p2 + 1}.$p1",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })*/

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.activity_main_title)


        drawerLayout = findViewById(R.id.nav_drawer_layout)

        val toogle: ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.toogle_open, R.string.toogle_close)
        drawerLayout.addDrawerListener(toogle)

        toogle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        imageProfile = navigationView.getHeaderView(0).findViewById(R.id.header_profile_image)
    }



    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_account -> {
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_schedule ->
            {
                val intent = Intent(this, ProductDetailActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings ->
            {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout -> {
                val intent = Intent(this, UserLoginActivity::class.java)
                startActivity(intent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onResume() {
        super.onResume()
        val profileImage = PreferenceManager.getDefaultSharedPreferences(this).getString(MediaStore.EXTRA_OUTPUT, null)
        if (profileImage != null){
            imageProfile.setImageURI(Uri.parse(profileImage))
        }
        else{
            imageProfile.setImageResource(R.drawable.profile_image)
        }
    }

    fun addCalendarEvent(view: View) {
        val calendarEvent: Calendar = Calendar.getInstance()
        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra("beginTime", calendarEvent.timeInMillis)
        intent.putExtra("allDay", true)
        intent.putExtra("rule", "FREQ=YEARLY")
        intent.putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
        intent.putExtra("title", "Calendar Event")
        startActivity(intent)
    }
}