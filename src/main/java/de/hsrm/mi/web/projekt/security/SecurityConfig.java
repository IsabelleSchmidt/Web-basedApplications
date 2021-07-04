package de.hsrm.mi.web.projekt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authmanagerbuilder) throws Exception {
        PasswordEncoder pwenc = passwordEncoder();

        authmanagerbuilder.inMemoryAuthentication()
            .withUser("friedfert")
            .password(pwenc.encode("dingdong"))
            .roles("GUCKER")
        .and()
            .withUser("joghurta")
            .password(pwenc.encode("geheim123"))
            .roles("PHOTOGRAPH");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/css/*").permitAll()
            .antMatchers("/api/*", "/api*").authenticated()
            .antMatchers("/messagebroker").permitAll()
            // .antMatchers("/register", "logout").permitAll()
            // .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
            // .antMatchers(HttpMethod.PUT, "/bag").hasRole("ADMIN")
            .anyRequest().hasAnyRole("ADMIN","PHOTOGRAPH", "GUCKER")
        .and()
            .formLogin()
            .defaultSuccessUrl("/foto")
            .permitAll();
    }
}
