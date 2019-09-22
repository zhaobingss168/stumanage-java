package com.bing.stumanage;

import com.bing.stumanage.interceptor.LoginInterception;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Description 配置拦截器拦截规则
 * @Author zhaobing
 * @Date 2019/9/16 20:38
 **/
@Configuration
public class StuManageWebMvcConfigurer implements WebMvcConfigurer {
    @Resource
    private LoginInterception loginInterception;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(loginInterception).
                addPathPatterns("/**").//所有路径都拦截
                excludePathPatterns("/login/4a/first").//管理端登陆第一次交互
                excludePathPatterns("/login/4a/second").//管理端登陆第二次交互
                excludePathPatterns("/base/getVerify").//图形验证码
                excludePathPatterns("/swagger-ui.html").//如下四个是放开对swagger的限制
                excludePathPatterns("/webjars/**").
                excludePathPatterns("/v2/**").
                excludePathPatterns("/swagger-resources/**")
        ;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //自定义静态资源映射，只需重写addResourceHandlers方法即可。
        //addResourceHandler添加映射路径,addResourceLocations来指定路径
        //配置静态资源的映射路径,不配置默认的是/**,新增的图片也会保存在static文件夹下面
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
