package com.family.dues.config;
import org.springframework.context.annotation.Configuration;import org.springframework.web.servlet.config.annotation.*;
@Configuration public class WebMvcConfig implements WebMvcConfigurer {
  @Override public void addViewControllers(ViewControllerRegistry r){ r.addViewController("/login").setViewName("login"); } }
