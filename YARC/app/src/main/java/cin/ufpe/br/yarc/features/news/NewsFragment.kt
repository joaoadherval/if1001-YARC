package cin.ufpe.br.yarc.features.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.commons.inflate
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment() {
    //testar o uso de extensões em android
    //private var newsList : RecyclerView? = null
    

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = container?.inflate(R.layout.news_fragment)

//        newsList = view?.findViewById(R.id.news_list) as RecyclerView?
//        newsList?.setHasFixedSize(true)
//        newsList?.layoutManager = LinearLayoutManager(context)

//        return view
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)
    }
}