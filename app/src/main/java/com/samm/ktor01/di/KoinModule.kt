package com.samm.ktor01.di

import com.samm.ktor01.data.database.MyDatabase
import com.samm.ktor01.data.repository.RepositoryImpl
import com.samm.ktor01.domain.repository.Repository
import com.samm.ktor01.presentation.viewmodels.AstroViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }
    single { get<MyDatabase>().myDao() }
    single { MyDatabase.getDatabase(androidContext()) }
}

val viewModelModule = module {
    viewModel { AstroViewModel(get()) }
}