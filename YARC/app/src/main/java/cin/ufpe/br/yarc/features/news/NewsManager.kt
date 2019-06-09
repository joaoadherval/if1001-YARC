package cin.ufpe.br.yarc.features.news

import cin.ufpe.br.yarc.commons.NewsItem
import rx.Observable

class NewsManager {
    fun getNews(): Observable<List<NewsItem>> {
        return Observable.create {
                subscriber ->

            val news = mutableListOf<NewsItem>()
            for (i in 1..10) {
                news.add(NewsItem("author$i","Title $i", i, 1457207701L - i * 200, "http://lorempixel.com/200/200/technics/$i", "url"))
            }
            subscriber.onNext(news)
        }
    }
}