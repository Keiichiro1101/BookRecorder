package hu.ait.bookrecorder3

import hu.ait.bookrecorder3.googlebookapi.BookResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("volumes")
    suspend fun getBooks(@Query("q") q: String): Response<BookResult>
}