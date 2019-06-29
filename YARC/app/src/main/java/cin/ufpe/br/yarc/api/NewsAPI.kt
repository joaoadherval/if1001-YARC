package cin.ufpe.br.yarc.api

import kotlinx.coroutines.Deferred
import retrofit2.Call

interface NewsAPI {
    fun oldGetNews(after: String, limit: String): Call<RedditNewsResponse>
    suspend fun getNews(after: String, limit: String): Deferred<RedditNewsResponse>
}