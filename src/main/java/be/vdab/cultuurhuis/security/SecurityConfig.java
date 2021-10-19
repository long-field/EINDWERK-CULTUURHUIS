package be.vdab.cultuurhuis.security;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery( "select gebruikersnaam as username, paswoord as password, true as enabled from klanten where gebruikersnaam = ?")
                .authoritiesByUsernameQuery( "select klanten.gebruikersnaam as username, 'klant' as authorities from klanten where klanten.gebruikersnaam = ?" );
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login"));
        http.formLogin().loginPage("/login").defaultSuccessUrl("/");
        http.logout(logout -> logout.logoutSuccessUrl("/login"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/", "/login").permitAll());
    }

}
