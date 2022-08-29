package konkuk.samchuck.config;

import konkuk.samchuck.interceptors.BearerAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bearerAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/signup/**", "/api/authenticate/**");
    }
}
