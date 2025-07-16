package di

import com.moashraf.data.service.BlogServiceImpl
import com.moashraf.data.service.ServiceImpl
import com.moashraf.domain.service.BlogService
import com.moashraf.domain.service.UserService
import org.koin.dsl.module

val servicesModule = module {
    single<UserService> {
        ServiceImpl(get())
    }

    single<BlogService> {
        BlogServiceImpl(get())
    }
}