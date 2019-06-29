package cin.ufpe.br.yarc.api

import cin.ufpe.br.yarc.commons.Logger
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class NewsRestAPI @Inject constructor(private val redditAPI: RedditAPI) : NewsAPI {

    override suspend fun getNews(after: String, limit: String): Deferred<RedditNewsResponse> {
        Logger.dt("calling Rest API")
        return redditAPI.getDeferredTop(after, limit)
    }

    override fun oldGetNews(after: String, limit: String) : Call<RedditNewsResponse> {
        return redditAPI.getTop(after, limit)
    }
}