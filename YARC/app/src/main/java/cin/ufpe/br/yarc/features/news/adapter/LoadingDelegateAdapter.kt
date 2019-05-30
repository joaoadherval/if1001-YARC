package cin.ufpe.br.yarc.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.commons.adapter.ViewType
import cin.ufpe.br.yarc.commons.adapter.ViewTypeDelegateAdapter
import cin.ufpe.br.yarc.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item_loading))
}