package cn.blog.config;

import cn.blog.interceptor.TokenInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    //拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/admin/**");
    }

    //转换date数据
    @Bean
    public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        ValueFilter valueFilter = new ValueFilter() {
            public Object process(Object o, String s, Object o1) {
                if (null == o1) {
                    o1 = "";
                }
                return o1;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }

    //图片流
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxInMemorySize(10240);     //最大内存大小 (10240)
        resolver.setMaxUploadSize(50*1024*1024);    //上传文件大小(单位为字节) 50M     50*1024*1024
        return resolver;
    }

}
