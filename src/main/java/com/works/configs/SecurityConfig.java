package com.works.configs;

import com.works.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final JwtFilter jwtFilter;
    final UserService userService;

    public SecurityConfig(JwtFilter jwtFilter, UserService userService) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder( userService.encoder() );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .headers()
                .frameOptions()
                .deny()
                .and()
                .authorizeRequests()
                .antMatchers(getPermitAll()).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(getCustomerRole()).hasAnyRole("customer","admin")
                .antMatchers(getAdminRole()).hasRole("admin")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private static final String[] AUTH_WHITELIST = {
            "/auth",
            "/register",
            "/product/list",
            "/image/list",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private String[] getCustomerRole(){
        String[] customerRole={
                "/product/list","/product/getImagesByProduct","/product/findImageWithProductId/{productId}",
                "/product/findProductWithCategory/{categoryId}","/product/searchProduct","/product/findProductById/{productId}",
                "/category/list",
                "/image/list/{productId}",
                "/order/save","/order/delete/{orderId}","/order/update","/order/listOrders","/order/getOrderById/{userId}"
        };
        return customerRole;
    }

    private String[] getAdminRole(){
        String[] adminRole={
                "/user/list","/user/listByUserId/{userId}",
                "/category/**",
                "/product/**",
                "/image/**",
                "/order/**",
        };
        return adminRole;
    }
    private String[] getPermitAll(){
        String[] permitAll={"/user/register","/user/auth"};
        return permitAll;
    }

}
