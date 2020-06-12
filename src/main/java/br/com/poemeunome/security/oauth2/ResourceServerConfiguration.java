package br.com.poemeunome.security.oauth2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "restservice";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.logout().logoutSuccessUrl("/").permitAll().invalidateHttpSession(true).clearAuthentication(true).and()
				.authorizeRequests().antMatchers("/api/user/**").hasRole("ADMIN").anyRequest().denyAll().and()
				.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}
