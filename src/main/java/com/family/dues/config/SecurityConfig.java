package com.family.dues.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final PasswordEncoder enc;
  @Value("${admin.username}") String user;
  @Value("${admin.password}") String pw;

  public SecurityConfig(PasswordEncoder enc) { this.enc = enc; }

  @Override
  protected void configure(AuthenticationManagerBuilder a) throws Exception {
    a.inMemoryAuthentication().passwordEncoder(enc)
        .withUser(user).password(enc.encode(pw)).roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity h) throws Exception {
    h.authorizeRequests()
        .antMatchers("/members/**", "/settings/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/dues/**").hasRole("ADMIN")
        .anyRequest().permitAll()
      .and().formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/dues", true)
        .failureUrl("/login?error")
        .permitAll()
      .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/?logout")
        .permitAll()
      .and().exceptionHandling()
        .accessDeniedHandler((req, res, ex) -> {
          boolean csrf = ex instanceof InvalidCsrfTokenException || ex instanceof MissingCsrfTokenException;
          res.sendRedirect(csrf ? "/login?expired=1" : "/login?denied=1");
        });
  }
}
