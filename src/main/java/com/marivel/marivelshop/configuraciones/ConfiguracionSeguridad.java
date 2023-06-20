package com.marivel.marivelshop.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marivel.marivelshop.entidades.login.Rol;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    // String[] recursos = new String[] {
    // "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
    // };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Configurar urls
                .authorizeRequests()
                // .antMatchers("/producto").hasRole(Rol.ADMIN.name())
                .antMatchers("/producto/**").hasRole(Rol.ADMIN.name())
                .antMatchers("/reportes/**").hasRole(Rol.ADMIN.name())
                .antMatchers("/carrito/comprar").hasRole(Rol.CLIENTE.name())
                .antMatchers("/venta/**").hasRole(Rol.CLIENTE.name())
                // .antMatchers("/menu").authenticated()
                // .antMatchers("/admin").hasRole(Rol.ADMIN.name())
                // .antMatchers("/cliente").hasRole(Rol.CLIENTE.name())
                .anyRequest().permitAll()
                .and()
                // Configurar formulario
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
                .usernameParameter("user")
                .passwordParameter("password")
                .and()
                // Configurar logout
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
