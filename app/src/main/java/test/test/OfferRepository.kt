package test.test

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class OfferRepository {
    private val apiService = NetworkService.apiService

    fun getOffers(): Flow<List<Offer>> = flow {
        try {
            val response = apiService.getOffers("214a1713-bac0-4853-907c-a1dfc3cd05fd")
            emit(response.offers)
        } catch (e: IOException) {
            // Обрабатываем исключение, когда нет соединения с сервером
            // Например, можно вывести сообщение об ошибке или выполнить другие действия
            e.printStackTrace()
        }
    }
}
