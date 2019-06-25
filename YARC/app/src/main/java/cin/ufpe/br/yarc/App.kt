package cin.ufpe.br.yarc

import android.app.Application
import cin.ufpe.br.yarc.di.AppModule
import cin.ufpe.br.yarc.di.news.DaggerNewsComponent
import cin.ufpe.br.yarc.di.news.NewsComponent

class App : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}