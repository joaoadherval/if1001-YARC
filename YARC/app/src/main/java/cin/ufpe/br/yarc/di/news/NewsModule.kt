package cin.ufpe.br.yarc.di.news

import cin.ufpe.br.yarc.api.NewsAPI
import cin.ufpe.br.yarc.api.NewsRestAPI
import cin.ufpe.br.yarc.api.RedditAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsAPI(redditApi: RedditAPI): NewsAPI {
        return NewsRestAPI(redditApi)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditAPI {
        return retrofit.create(RedditAPI::class.java)
    }
}