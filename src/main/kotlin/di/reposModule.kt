package di

import com.moashraf.data.repo.BlogRepositoryImpl
import com.moashraf.data.repo.UserRepositoryImpl
import com.moashraf.domain.repo.BlogRepository
import com.moashraf.domain.repo.UserRepository
import org.koin.dsl.module

val reposModule = module {
    single<UserRepository> {
        UserRepositoryImpl()
    }
    single<BlogRepository> {
        BlogRepositoryImpl()
    }
}

