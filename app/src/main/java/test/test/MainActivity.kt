package test.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var activeMenuItem: String? = null
    private lateinit var menuAir: View
    private lateinit var menuHotel: View
    private lateinit var menuLocation: View
    private lateinit var menuBell: View
    private lateinit var menuPerson: View

    private lateinit var menuAirText: TextView
    private lateinit var menuHotelText: TextView
    private lateinit var menuLocationText: TextView
    private lateinit var menuBellText: TextView
    private lateinit var menuPersonText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuView = findViewById<View>(R.id.menu)
        menuAir = menuView.findViewById(R.id.menu_air)
        menuHotel = menuView.findViewById(R.id.menu_hotel)
        menuLocation = menuView.findViewById(R.id.menu_location)
        menuBell = menuView.findViewById(R.id.menu_bell)
        menuPerson = menuView.findViewById(R.id.menu_person)

        menuAirText = menuView.findViewById(R.id.text_air)
        menuHotelText = menuView.findViewById(R.id.text_hotel)
        menuLocationText = menuView.findViewById(R.id.text_location)
        menuBellText = menuView.findViewById(R.id.text_bell)
        menuPersonText = menuView.findViewById(R.id.text_person)

        menuAir.setOnClickListener {
            loadActivity(AirActivity::class.java)
            setActiveMenuItem("air")
        }
        menuHotel.setOnClickListener {
            loadActivity(HotelActivity::class.java)
            setActiveMenuItem("hotel")
        }
        menuLocation.setOnClickListener {
            loadActivity(LocationActivity::class.java)
            setActiveMenuItem("location")
        }
        menuBell.setOnClickListener {
            loadActivity(BellActivity::class.java)
            setActiveMenuItem("bell")
        }
        menuPerson.setOnClickListener {
            loadActivity(PersonActivity::class.java)
            setActiveMenuItem("person")
        }




        //
        setActiveMenuItem("air")
        loadActivity(AirActivity::class.java)
    }

    private fun loadActivity(targetClass: Class<out ContentFragment>) {
        val fragment = targetClass.newInstance().newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }

    @SuppressLint("CutPasteId")
    private fun setActiveMenuItem(menuItemTag: String) {

        when (activeMenuItem) {
            "air" -> {
                menuAirText.setTextColor(resources.getColor(R.color.Grey_6, theme))
            }
            "hotel" -> {
                menuHotelText.setTextColor(resources.getColor(R.color.Grey_6, theme))
            }
            "location" -> {
                menuLocationText.setTextColor(resources.getColor(R.color.Grey_6, theme))
            }
            "bell" -> {
                menuBellText.setTextColor(resources.getColor(R.color.Grey_6, theme))
            }
            "person" -> {
                menuPersonText.setTextColor(resources.getColor(R.color.Grey_6, theme))
            }
        }

        activeMenuItem = menuItemTag
        when (activeMenuItem) {
            "air" -> {
                menuAirText.setTextColor(resources.getColor(R.color.Blue, theme))
            }
            "hotel" -> {
                menuHotelText.setTextColor(resources.getColor(R.color.Blue, theme))
            }
            "location" -> {
                menuLocationText.setTextColor(resources.getColor(R.color.Blue, theme))
            }
            "bell" -> {
                menuBellText.setTextColor(resources.getColor(R.color.Blue, theme))
            }
            "person" -> {
                menuPersonText.setTextColor(resources.getColor(R.color.Blue, theme))
            }
        }
    }



}

