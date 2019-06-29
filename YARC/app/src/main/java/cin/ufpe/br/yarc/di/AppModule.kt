package cin.ufpe.br.yarc.di

import android.app.Application
import android.content.Context
import cin.ufpe.br.yarc.YARCApp
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CommonPool
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule(val app: YARCApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    fun provideCoroutineContext() = Dispatchers.Default
}