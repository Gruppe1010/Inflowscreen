package gruppe10.inflowscreen.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // TODO "/webjars/**" MÅSKE sæt dette ind i permitall i configure

    // TODO find ud af at lave 1 til ${klinikId}

    @Override
    protected void configure(HttpSecurity http) throws Exception { // ! kommenter l.. 34-5 ind og slet 33
        http.
                csrf().disable()
                .authorizeRequests()
                //.antMatchers("/resources/**", "/static/**", "/css/slideshow.css", "/images/**", "/js/slideshow.js", "/templates/slideshow.html").permitAll()
                //.antMatchers("/**").permitAll()
                .antMatchers("/slideshow").permitAll()
                .antMatchers("/createSlide", "/").hasAnyAuthority("USER", "ADMIN") // hvis der kommer flere brugertyper
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/", true)
                .and()
                .httpBasic(); // Test
    }

}
