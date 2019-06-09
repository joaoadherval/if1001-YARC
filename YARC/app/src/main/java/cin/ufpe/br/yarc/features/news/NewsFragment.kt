package cin.ufpe.br.yarc.features.news

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.commons.BaseFragment
import cin.ufpe.br.yarc.commons.NewsItem
import cin.ufpe.br.yarc.commons.extensions.inflate
import cin.ufpe.br.yarc.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NewsFragment : BaseFragment() {

    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if (savedInstanceState == null) {
            requestNews()
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { retrivedNews -> (news_list.adapter as NewsAdapter).addNews(retrivedNews) },
            { e -> Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show() }
        )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if(news_list.adapter == null)
            news_list.adapter = NewsAdapter()
    }
}