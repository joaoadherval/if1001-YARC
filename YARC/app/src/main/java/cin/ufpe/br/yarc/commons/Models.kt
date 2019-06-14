package cin.ufpe.br.yarc.commons

import cin.ufpe.br.yarc.commons.adapter.AdapterConstants
import cin.ufpe.br.yarc.commons.adapter.ViewType

data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>
)

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}