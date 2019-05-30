package cin.ufpe.br.yarc.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cin.ufpe.br.yarc.R
import cin.ufpe.br.yarc.commons.NewsItem
import cin.ufpe.br.yarc.commons.adapter.ViewType
import cin.ufpe.br.yarc.commons.adapter.ViewTypeDelegateAdapter
import cin.ufpe.br.yarc.commons.extensions.getTime
import cin.ufpe.br.yarc.commons.extensions.inflate
import cin.ufpe.br.yarc.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as NewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item)) {

        fun bind(item: NewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getTime()
        }
    }
}
