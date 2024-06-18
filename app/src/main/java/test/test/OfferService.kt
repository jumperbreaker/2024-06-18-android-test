package test.test

import retrofit2.http.GET
import retrofit2.http.Path

interface OfferService {
    @GET("{offerUrl}")
    suspend fun getOffers(@Path("offerUrl") offerUrl: String): OfferResponse
}
