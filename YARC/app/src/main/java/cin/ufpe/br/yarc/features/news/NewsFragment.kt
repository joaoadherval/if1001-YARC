package cin.ufpe.br.yarc.features.news

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cin.ufpe.br.yarc.App
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.commons.InfiniteScrollListener
import cin.ufpe.br.yarc.commons.RedditNews
import cin.ufpe.br.yarc.commons.RxBaseFragment
import cin.ufpe.br.yarc.commons.extensions.inflate
import cin.ufpe.br.yarc.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.schedulers.Schedulers
import javax.inject.Inject

class NewsFragment : RxBaseFragment() {

    companion object {
        private val KEY_PRODUCT_REDDIT = "redditNews"
    }

    @Inject
    lateinit var newsManager: NewsManager

    private var redditNews: RedditNews? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.newsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            news_list.setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            news_list.layoutManager = linearLayout
            news_list.clearOnScrollListeners()
            news_list.addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }
        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_PRODUCT_REDDIT)){
            redditNews = savedInstanceState.get(KEY_PRODUCT_REDDIT) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0)
            outState.putParcelable(KEY_PRODUCT_REDDIT, redditNews?.copy(news = news))
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
            .subscribeOn(Schedulers.io())
            .subscribe(
                { retrievedNews ->
                    redditNews = retrievedNews
                    (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                },
                { e -> Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show() }
            )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if(news_list.adapter == null)
            news_list.adapter = NewsAdapter()
    }
}