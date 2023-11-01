package sallange.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sallange.server.config.argumentresolver.UserAuthArgumentResolver;
import sallange.server.config.interceptor.UserAuthInterceptor;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserAuthInterceptor userAuthInterceptor;
    private final UserAuthArgumentResolver userAuthArgumentResolver;

//    @Override
//    public void addInterceptors(final InterceptorRegistry registry) {
//        registry.addInterceptor(userAuthInterceptor)
//                .excludePathPatterns("/admin/**", "/test/**", "/api/login/**");
//    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userAuthArgumentResolver);
    }
}
