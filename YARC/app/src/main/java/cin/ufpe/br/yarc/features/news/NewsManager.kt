package cin.ufpe.br.yarc.features.news

import cin.ufpe.br.yarc.api.RestAPI
import cin.ufpe.br.yarc.commons.NewsItem
import rx.Observable

class NewsManager(private val api: RestAPI = RestAPI()) {
    fun getNews(limit: String = "10"): Observable<List<NewsItem>> {
        return Observable.create {
                subscriber ->
                val callResponse = api.getNews("", limit)
                val response = callResponse.execute()

                if(response.isSuccessful) {
                    val news = response.body().data.children.map {
                        val item = it.data
                        NewsItem(item.author, item.title, item.num_comments, item.created, item.thumbnail, item.url)
                    }
                    subscriber.onNext(news)
                    subscriber.onCompleted()
                } else {
                    subscriber.onError(Throwable(response.message()))
                }
        }
    }
}