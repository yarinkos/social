package social.kossover.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import social.kossover.com.security.CustomAuthenticationProvider;
import social.kossover.com.security.MongoDBUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true) //todo what is it ?
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MongoDBUserDetailsService mongoDBUserDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http    //todo remove
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();*/

        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()//.defaultSuccessUrl("/resource")
//                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mongoDBUserDetailsService);
         auth.authenticationProvider(customAuthenticationProvider);
        /*auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");*/
    }
}
