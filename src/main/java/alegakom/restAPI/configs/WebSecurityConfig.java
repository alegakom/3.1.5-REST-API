package alegakom.restAPI.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private UserDetailsService userDetailsService;

    final
    PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/index/**").authenticated()
                .antMatchers("/api/**").hasAnyRole("ADMIN")
                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/api/index")
                .permitAll();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }


//     ???????????????????????????? inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails admin =
//                User.withDefaultPasswordEncoder
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN", "USER")
//                        .build();
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    //JDBC Authentication
//    @Bean
//    public UserDetailsService userDetailsService (DataSource datasource) {
//        UserDetails admin =
//                User.withDefaultPasswordEncoder
//                        .username("alegakom")
//                        .password("admin")
//                        .roles("ADMIN", "USER")
//                        .build();
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
//        if(jdbcUserDetailsManager.userExists(user.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if(jdbcUserDetailsManager.userExists(admin.getUsername())) {
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(user);
//        return jdbcUserDetailsManager;
//    }
}