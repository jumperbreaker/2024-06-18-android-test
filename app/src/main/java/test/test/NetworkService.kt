package test.test

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: OfferService = retrofit.create(OfferService::class.java)
}
