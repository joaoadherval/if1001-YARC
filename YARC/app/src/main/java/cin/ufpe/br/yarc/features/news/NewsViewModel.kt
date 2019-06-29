package cin.ufpe.br.yarc.features.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import cin.ufpe.br.yarc.api.NewsAPI
import cin.ufpe.br.yarc.api.RedditNewsResponse
import cin.ufpe.br.yarc.commons.RedditNews
import cin.ufpe.br.yarc.commons.RedditNewsItem
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NewsViewModel @Inject constructor(
    private val api: NewsAPI) : ViewModel() {

    val newsState: MutableLiveData<NewsState> = MutableLiveData()

    fun fetchNews(after: String, limit: String = "10") = launch(AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()) {
        try {
            val result = api.getNews(after, limit).await()
            val news = process(result)
            newsState.postValue(NewsState.Success(news))
        } catch (e: Throwable) {
            newsState.postValue(NewsState.Error(e.message))
        }
    }

    private fun process(response: RedditNewsResponse): RedditNews {
        val dataResponse = response.data
        val news = dataResponse.children.map {
            val item = it.data
            RedditNewsItem(item.author, item.title, item.num_comments,
                item.created, item.thumbnail, item.url)
        }
        return RedditNews(
            dataResponse.after.orEmpty(),
            dataResponse.before.orEmpty(),
            news)
    }
}