package br.com.poemeunome.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.poemeunome.exception.ValidationException;
import br.com.poemeunome.model.Role;
import br.com.poemeunome.model.User;
import br.com.poemeunome.repository.UserRepository;
import br.com.poemeunome.repository.UserRepositoryUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final String USUARIO = "Usuário";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> usuario = this.userRepository.findByEmail(email);

		if (!usuario.isPresent())
			throw new UsernameNotFoundException(
					this.messageSource.getMessage("message.common.notfound", new Object[] { USUARIO }, null));
		else if (!usuario.get().isEnable())
			throw new ValidationException(
					this.messageSource.getMessage("message.common.notenable", new Object[] { USUARIO }, null));

		return new UserRepositoryUserDetails(usuario.get());
	}

	private final List<GrantedAuthority> getGrantedAuthorities(final Collection<Role> roles) {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		roles.stream().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getNome())));
		return authorities;
	}

	public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		return getGrantedAuthorities(roles);
	}

}
