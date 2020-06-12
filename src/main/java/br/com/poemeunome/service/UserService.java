package br.com.poemeunome.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.poemeunome.exception.ObjectNotFoundException;
import br.com.poemeunome.exception.SQLOperationsException;
import br.com.poemeunome.model.Role;
import br.com.poemeunome.model.User;
import br.com.poemeunome.model.dto.UserDTO;
import br.com.poemeunome.repository.UserRepository;

@Service
public class UserService {

	private static final String USUARIO = "Usuário";
	private static final String MESSAGE_NOT_FOUND = "message.common.notfound";
	private static final String MESSAGE_NOT_AUTHORIZED = "message.common.notauthorized";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserDTO> findAll() {
		List<User> usuarios = this.userRepository.findAll();
		List<UserDTO> usuariosDTO = usuarios.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return usuariosDTO;
	}

	public UserDTO findById(Long id) {
		Optional<User> usuario = this.userRepository.findById(id);
		return new UserDTO(usuario.orElseThrow(() -> new ObjectNotFoundException(
				messageSource.getMessage(MESSAGE_NOT_FOUND, new Object[] { USUARIO }, null))));
	}

	public UserDTO createOrUpdate(UserDTO dto) {
		dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
		return new UserDTO(this.userRepository.save(new User(dto)));
	}

	public void delete(Long id) {
		Optional<User> usuario = this.userRepository.findById(id);
		try {
			this.userRepository.delete(usuario.orElseThrow(() -> new ObjectNotFoundException(
					messageSource.getMessage(MESSAGE_NOT_FOUND, new Object[] { USUARIO }, null))));
		} catch (DataIntegrityViolationException e) {
			throw new SQLOperationsException(messageSource.getMessage(MESSAGE_NOT_AUTHORIZED, null, null));
		}
	}

	public List<Role> findRoles(Long id) {
		return this.findById(id).getPermissoes();
	}

}
