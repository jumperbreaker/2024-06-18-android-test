package test.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class AirActivity : Fragment(), ContentFragment {

    private lateinit var editTextFrom: EditText
    private lateinit var editTextTo: EditText
    private lateinit var recycler_view: RecyclerView
    private val PREF_KEY = "edit_text_from_value"
    private val offerRepository = OfferRepository()

    override fun newInstance(): Fragment {
        return AirActivity()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_air, container, false)

        recycler_view = view.findViewById(R.id.recycler_view)
        editTextFrom = view.findViewById(R.id.edit_text_from)
        editTextTo = view.findViewById(R.id.edit_text_to)

        // Создаем фильтр для ввода только кириллицы и цифр
        val cyrillicFilter = InputFilter { source, _, _, _, _, _ ->
            if (source.toString().matches(Regex("[а-яА-ЯёЁ\\d]+"))) {
                source
            } else {
                ""
            }
        }
        editTextFrom.filters = arrayOf(cyrillicFilter)
        editTextTo.filters = arrayOf(cyrillicFilter)

        val images = listOf(R.drawable.im_offers_1, R.drawable.im_offers_2, R.drawable.im_offers_3)

        // Получаем список предложений (offer) из репозитория
        val offers = mutableListOf<Offer>()
        lifecycleScope.launch {
            try {
                offerRepository.getOffers().collect { offers.addAll(it) }
                if (offers.isNotEmpty()) {
                    val adapter = ImageAdapter(images, offers)
                    recycler_view.adapter = adapter
                }
            } catch (e: HttpException) {
                // Обрабатываем исключение, когда сервер возвращает ошибку
                if (e.code() == 404) {
                    // Обрабатываем ошибку 404 Not Found
                    Toast.makeText(requireContext(), "Ресурс не найден", Toast.LENGTH_SHORT).show()
                } else {
                    // Обрабатываем другие HTTP-ошибки
                    Toast.makeText(requireContext(), "Ошибка: ${e.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                // Обрабатываем исключение, когда нет соединения с сервером
                Toast.makeText(requireContext(), "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show()
            }
        }

        val adapter = ImageAdapter(images, offers)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreEditTextFromValue()
        editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                saveEditTextFromValue(s.toString())
            }
        })
    }

    private fun restoreEditTextFromValue() {
        val sharedPreferences = requireContext().getSharedPreferences("app_preferences", AppCompatActivity.MODE_PRIVATE)
        val value = sharedPreferences.getString(PREF_KEY, "")
        editTextFrom.setText(value)
    }

    private fun saveEditTextFromValue(value: String) {
        val sharedPreferences = requireContext().getSharedPreferences("app_preferences", AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().putString(PREF_KEY, value).apply()
    }

}

class ImageAdapter(
    private val images: List<Int>,
    private val offers: List<Offer>
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (offers.isNotEmpty() && images.isNotEmpty()) {
            holder.imageView.setImageResource(images[position])
            holder.titleTextView.text = offers[position].title
            holder.townTextView.text = offers[position].town
            holder.priceTextView.text = "от: ${offers[position].price.value}"
            holder.priceIcon.setImageResource(R.drawable.ic_air)
        } else {
            holder.titleTextView.text = ""
            holder.townTextView.text = ""
            holder.priceTextView.text = ""
            holder.priceIcon.setImageResource(0)
        }
    }

    override fun getItemCount() = images.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val titleTextView: TextView = itemView.findViewById(R.id.text_view_title)
        val townTextView: TextView = itemView.findViewById(R.id.text_view_town)
        val priceTextView: TextView = itemView.findViewById(R.id.text_view_price)
        val priceIcon: ImageView = itemView.findViewById(R.id.price_icon)
    }
}