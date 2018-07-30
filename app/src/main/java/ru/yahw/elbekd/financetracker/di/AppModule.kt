package ru.yahw.elbekd.financetracker.di

import dagger.Module
import dagger.Provides
import ru.yahw.elbekd.financetracker.data.remote.RemoteApi
import ru.yahw.elbekd.financetracker.domain.repository.CategoryRepo
import ru.yahw.elbekd.financetracker.domain.repository.TransactionRepo
import ru.yahw.elbekd.financetracker.domain.repository.WalletRepo
import javax.inject.Singleton

/**
 * Created by Elbek D. on 24.07.2018.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideCategoryRepo() = CategoryRepo()

    @Singleton
    @Provides
    fun provideTransactionRepo() = TransactionRepo()

    @Singleton
    @Provides
    fun provideRemoteApi() = RemoteApi()

    @Singleton
    @Provides
    fun provideWalletRepo(remoteApi: RemoteApi) = WalletRepo(remoteApi)
}