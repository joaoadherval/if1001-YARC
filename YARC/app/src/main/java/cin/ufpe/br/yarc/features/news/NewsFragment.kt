package cin.ufpe.br.yarc.features.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.commons.NewsItem
import cin.ufpe.br.yarc.commons.extensions.inflate
import cin.ufpe.br.yarc.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if (savedInstanceState == null) {
            val news = mutableListOf<NewsItem>()
            for (i in 1..10) {
                news.add(NewsItem("author$i", "Title $i", i, 1457207701L - i * 200, "http://lorempixel.com/200/200/technics/$i", "url"))
            }
            (news_list.adapter as NewsAdapter).addNews(news)
        }
    }

    private fun initAdapter() {
        if(news_list.adapter == null)
            news_list.adapter = NewsAdapter()
    }
}