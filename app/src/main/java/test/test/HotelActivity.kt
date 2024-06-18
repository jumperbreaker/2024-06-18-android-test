package test.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HotelActivity : Fragment(), ContentFragment {
    override fun newInstance(): Fragment {
        return HotelActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_hotel, container, false)
    }

    // Настройка элементов HotelActivity
}
