package chinanko.chinanko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import chinanko.chinanko.repository.UserRepository;

@Configuration
public class ApplicationConfiguration {
    private UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.getUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

  @Bean
    public AuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        
        // 1. Usar el constructor vacío (sin argumentos)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        
        // 2. Asignar el UserDetailsService mediante el setter
        provider.setUserDetailsService(userDetailsService);
        
        // 3. Asignar el PasswordEncoder mediante el setter
        provider.setPasswordEncoder(passwordEncoder);
        
        return provider;
    }
    /*
     * @Bean
     * AuthenticationProvider authenticationProvider() {
     * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
     * 
     * authProvider.setUserDetailsService(userDetailsService());
     * authProvider.setPasswordEncoder(passwordEncoder());
     * 
     * return authProvider;
     * }
     */

}
