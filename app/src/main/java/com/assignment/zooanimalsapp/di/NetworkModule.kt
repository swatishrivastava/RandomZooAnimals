package com.assignment.zooanimalsapp.di

import com.assignment.zooanimalsapp.network.AnimalApi
import com.assignment.zooanimalsapp.network.AnimalClient
import com.assignment.zooanimalsapp.network.BASE_URL
import com.assignment.zooanimalsapp.repository.AnimalRepo
import com.assignment.zooanimalsapp.repository.IRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideMosi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
     fun provideApiService(retrofit: Retrofit) = retrofit.create(AnimalApi::class.java)

}