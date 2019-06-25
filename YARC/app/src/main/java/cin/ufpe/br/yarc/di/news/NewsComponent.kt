package cin.ufpe.br.yarc.di.news

import cin.ufpe.br.yarc.di.AppModule
import cin.ufpe.br.yarc.di.NetworkModule
import cin.ufpe.br.yarc.features.news.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    NewsModule::class,
    NetworkModule::class)
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}