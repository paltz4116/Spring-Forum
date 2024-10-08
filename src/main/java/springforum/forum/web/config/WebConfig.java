package springforum.forum.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springforum.forum.web.interceptor.LoggedInUserCheckInterceptor;
import springforum.forum.web.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/signup",
                        "/page/*", "/post/*");

        registry.addInterceptor(new LoggedInUserCheckInterceptor())
                .order(2)
                .addPathPatterns("/login", "/signup");
    }
}
