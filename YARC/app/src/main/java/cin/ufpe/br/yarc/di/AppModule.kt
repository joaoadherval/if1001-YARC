package cin.ufpe.br.yarc.di

import android.content.Context
import cin.ufpe.br.yarc.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): App = app
}