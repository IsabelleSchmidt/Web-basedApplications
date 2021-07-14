// package de.hsrm.mi.web.projekt.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//     @Autowired UserDetailService userDetailService;

//     @Bean PasswordEncoder passwordEncoder() {
//         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder authmanagerbuilder) throws Exception {

//         authmanagerbuilder
//             .userDetailsService(userDetailService)
//             .passwordEncoder(passwordEncoder());
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception{
        
//         http.authorizeRequests()
//             .antMatchers("/css/**").permitAll()
//             .antMatchers("/api/*", "/messagebroker").permitAll()
//             .antMatchers("/foto/*").permitAll()
//             // .antMatchers(HttpMethod.GET, "/foto/*").permitAll()
//             // .antMatchers(HttpMethod.GET, "/foto/*/del").hasRole("PHOTOGRAPH")
//             .antMatchers(HttpMethod.PUT).hasRole("PHOTOGRAPH")
//             .antMatchers(HttpMethod.DELETE, "/foto/*/del").hasRole("PHOTOGRAPH")
//             .antMatchers("/h2-console/**").permitAll()
//             .anyRequest().authenticated()
//         .and()
//             .csrf()
//             .ignoringAntMatchers("/api/foto/*")
//         .and()
//             .formLogin()
//             // .loginPage("/fotologin")
//             .defaultSuccessUrl("/foto")
//             .permitAll()
//         .and()
//             .logout()
//             .permitAll()
//         .and()
//             .csrf()
//             .ignoringAntMatchers("/h2-console/**")
//             .disable();
//         http.headers().frameOptions().disable();
//     }

//     // Eine Loesung die ich im Internet gefunden hab die auch funktioniert hat
//     // Ich war mir nur nicht sicher ob wir das auch so machen dürften
//     // Ich habe es aber trotzdem dringelassen für mich weil ich es übersichtlicher finde :)

//     // @Override
//     // public void configure(WebSecurity web) throws Exception {
//     //     web
//     //         .ignoring()
//     //         .antMatchers("/h2-console/**");
//     // }
// }
