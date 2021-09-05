package bulgakov.locality.configuration;

import bulgakov.locality.filter.CharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/registration", "/").not().fullyAuthenticated()
                .antMatchers("/edit/user", "/delete/user", "/users").hasAuthority("ADMIN")
                .antMatchers("/create/infrastructure", "/create/locality",
                        "/delete/infrastructure", "/delete/locality", "/edit/infrastructure",
                        "/edit/locality").hasAuthority("CHAIRMEN")
                .anyRequest().authenticated().and()

                .formLogin().loginPage("/login")
                .usernameParameter("check_username").passwordParameter("check_password").permitAll()
                .defaultSuccessUrl("/home").permitAll()
                .and().exceptionHandling().accessDeniedPage("/error")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).clearAuthentication(true).permitAll();

        httpSecurity.addFilterAfter(new CharsetFilter(), BasicAuthenticationFilter.class);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
