package cin.ufpe.br.yarc.features.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.YARCApp
import cin.ufpe.br.yarc.commons.InfiniteScrollListener
import cin.ufpe.br.yarc.commons.RedditNews
import cin.ufpe.br.yarc.commons.ViewModelFactory
import cin.ufpe.br.yarc.commons.extensions.androidLazy
import cin.ufpe.br.yarc.commons.extensions.getViewModel
import cin.ufpe.br.yarc.commons.extensions.inflate
import cin.ufpe.br.yarc.features.news.adapter.NewsAdapter
import cin.ufpe.br.yarc.features.news.adapter.NewsDelegateAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

class NewsFragment : Fragment(), NewsDelegateAdapter.onViewSelectedListener {

    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            Snackbar.make(news_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    companion object {
        private val KEY_PRODUCT_REDDIT = "redditNews"
    }

    private var redditNews: RedditNews? = null
    private val newsAdapter by androidLazy { NewsAdapter(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsViewModel>
    private val newsViewModel by androidLazy {
        getViewModel<NewsViewModel>(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        YARCApp.newsComponent.inject(this)
        super.onCreate(savedInstanceState)
        newsViewModel.newsState.observe(this, Observer<NewsState> {
            manageState(it)
        })
    }

    private fun manageState(kedditState: NewsState?) {
        val state = kedditState ?: return
        when (state) {
            is NewsState.Success -> {
                redditNews = state.redditNews
                newsAdapter.addNews(state.redditNews.news)
            }
            is NewsState.Error -> {
                Snackbar.make(news_list, state.message.orEmpty(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY") { requestNews() }
                    .show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        news_list.adapter = newsAdapter

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_PRODUCT_REDDIT)){
            redditNews = savedInstanceState.get(KEY_PRODUCT_REDDIT) as RedditNews
            newsAdapter.clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = newsAdapter.getNews()
        if (redditNews != null && news.size > 0)
            outState.putParcelable(KEY_PRODUCT_REDDIT, redditNews?.copy(news = news))
    }

    private fun requestNews() {
        newsViewModel.fetchNews(redditNews?.after.orEmpty())
    }
}