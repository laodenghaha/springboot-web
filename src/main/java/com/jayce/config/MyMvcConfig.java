package com.jayce.config;


import com.jayce.compoment.LoginHandlerInterceptor;
import com.jayce.compoment.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 老邓 on 2018/6/26.
 * 配置类
 */
@Configuration
//@EnableWebMvc
public class MyMvcConfig  implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        // super.addViewController(registry)
        // 浏览器发送 /haha请求来到 success
        registry.addViewController("/haha").setViewName("success");
    }
    // 所有的WebMvcConfigurer 会一起起作用
    @Bean  // 需要注入到容器中
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("/index");
                registry.addViewController("/index.html").setViewName("/index");
                registry.addViewController("/main.html").setViewName("/dashboard");
            }

            //注册拦截器
            // SpringBoot已经做好了静态资源映射，所以拦截器不用处理静态资源
               public void addInterceptors(InterceptorRegistry registry) {
                    registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                            // 排除要拦截的请求
                            .excludePathPatterns("/index.html","/","/login");
                }
        };


        return webMvcConfigurer;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
