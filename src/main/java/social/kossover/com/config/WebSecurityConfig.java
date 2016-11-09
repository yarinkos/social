package social.kossover.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import social.kossover.com.security.providers.CustomAuthenticationProvider;
import social.kossover.com.security.providers.MongoDBUserDetailsService;

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
                .antMatchers("/").permitAll()
                //.anyRequest().authenticated()
                .and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/", "/home","/sighup","/greeting6","/saveCountry","/saveCountry1").permitAll()
                //.anyRequest().authenticated()
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

    /*@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }*/

    /*@Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(mongoDBUserDetailsService);
        //authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }*/
}
