package com.rymon.aparatdemo.di

import com.rymon.aparatdemo.api.AparatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit( client : OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(AparatApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideAparatApi(retrofit: Retrofit): AparatApi =
        retrofit.create(AparatApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
     OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)).build()
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor =
        HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)

}