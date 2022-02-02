package com.assignment.zooanimalsapp.di
import com.assignment.zooanimalsapp.network.AnimalApi
import com.assignment.zooanimalsapp.repository.AnimalRepo
import com.assignment.zooanimalsapp.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataModule {
    @Provides
    @Singleton
    fun provideRepo(api: AnimalApi): IRepository = AnimalRepo(api)
}