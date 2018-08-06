package ru.yahw.elbekd.financetracker.di

import android.app.Application
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
    fun provideTransactionRepo(application: Application) = TransactionRepo(application)

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient()

    @Singleton
    @Provides
    fun provideRemoteApi(okHttpClient: OkHttpClient) = RemoteApi(okHttpClient)

    @Singleton
    @Provides
    fun provideWorkManager() = WorkManager.getInstance()

    @Singleton
    @Provides
    fun provideWalletRepo(remoteApi: RemoteApi, application: Application) = WalletRepo(remoteApi, application)



}