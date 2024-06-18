package test.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class LocationActivity : Fragment(), ContentFragment {
    override fun newInstance(): Fragment {
        return LocationActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_location, container, false)
    }

    // Настройка элементов HotelActivity
}
