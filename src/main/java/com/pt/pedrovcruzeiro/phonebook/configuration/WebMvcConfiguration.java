package com.pt.pedrovcruzeiro.phonebook.configuration;

import com.pt.pedrovcruzeiro.phonebook.util.interceptor.MdcContextProviderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration {

  @Bean
  public WebMvcConfigurer initializerWebMvcConfigurer(
      MdcContextProviderInterceptor mdcContextProviderInterceptor) {
    return new WebMvcConfigurer() {
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mdcContextProviderInterceptor);
      }
    };
  }
}
