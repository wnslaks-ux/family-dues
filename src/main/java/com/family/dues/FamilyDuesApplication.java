package com.family.dues;
import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication public class FamilyDuesApplication extends SpringBootServletInitializer {
  @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder b){return b.sources(FamilyDuesApplication.class);}
  public static void main(String[] a){SpringApplication.run(FamilyDuesApplication.class,a);} }
