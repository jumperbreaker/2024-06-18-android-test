package test.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class PersonActivity : Fragment(), ContentFragment {
    override fun newInstance(): Fragment {
        return PersonActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_person, container, false)
    }

    // Настройка элементов HotelActivity
}
