package com.islam.tasks.floawrd.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.islam.tasks.core.navigation.DeeplinkProcessor
import com.islam.tasks.article_details.presentation.navigation.ArticlesDetailsDeeplinkProcessor
import com.islam.tasks.articles_list.presentation.navigation.ArticlesListDeeplinkProcessor
import com.islam.tasks.core.ArticlesKeys
import com.islam.tasks.core.di.IODispatcher
import com.islam.tasks.core.di.MainDispatcher
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        @Provides
        @Singleton
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        @Provides
        @Singleton
        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder().baseUrl(ArticlesKeys.geApiUrl()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient).build()

        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.userDataStore
        }

        @Provides
        @Singleton
        fun provideUserGson() = Gson()

        @Provides
        @Singleton
        fun providesContext(@ApplicationContext context: Context): Context = context

        @Provides
        @Singleton
        @MainDispatcher
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @Singleton
        @IODispatcher
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    }

    @Binds
    @IntoSet
    abstract fun bindPostsProcessors(
        processor: ArticlesDetailsDeeplinkProcessor
    ): DeeplinkProcessor

    @Binds
    @IntoSet
    abstract fun bindUsersProcessors(
        processor: ArticlesListDeeplinkProcessor
    ): DeeplinkProcessor
}