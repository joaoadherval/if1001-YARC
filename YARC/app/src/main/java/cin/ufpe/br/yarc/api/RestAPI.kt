package cin.ufpe.br.yarc.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI() {

    private val redditAPI: RedditAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://www.reddit.com").addConverterFactory(MoshiConverterFactory.create()).build()
        redditAPI = retrofit.create(RedditAPI::class.java)
    }

    fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditAPI.getTop(after, limit)
    }
}