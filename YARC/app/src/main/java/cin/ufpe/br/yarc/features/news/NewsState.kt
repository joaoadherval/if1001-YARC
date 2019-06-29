package cin.ufpe.br.yarc.features.news

import cin.ufpe.br.yarc.commons.RedditNews

sealed class NewsState {
    class Success(val redditNews: RedditNews) : NewsState()
    class Error(val message: String?) : NewsState()
}