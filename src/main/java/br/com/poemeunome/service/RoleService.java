package br.com.poemeunome.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.poemeunome.exception.ObjectNotFoundException;
import br.com.poemeunome.exception.SQLOperationsException;
import br.com.poemeunome.model.Role;
import br.com.poemeunome.model.dto.RoleDTO;
import br.com.poemeunome.repository.RoleRepository;

@Service
public class RoleService {

	private static final String ROLE = "Permissão";
	private static final String MESSAGE_NOT_FOUND = "message.common.notfound";
	private static final String MESSAGE_NOT_AUTHORIZED = "message.common.notauthorized";

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MessageSource messageSource;

	public List<RoleDTO> findAll() {
		List<Role> permissoes = this.roleRepository.findAll();
		List<RoleDTO> permissoesDTO = permissoes.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
		return permissoesDTO;
	}

	public RoleDTO findById(Long id) {
		Optional<Role> role = this.roleRepository.findById(id);
		return new RoleDTO(role.orElseThrow(() -> new ObjectNotFoundException(
				messageSource.getMessage(MESSAGE_NOT_FOUND, new Object[] { ROLE }, null))));
	}

	public RoleDTO createOrUpdate(RoleDTO dto) {
		return new RoleDTO(this.roleRepository.save(new Role(dto)));
	}

	public void delete(Long id) {
		Optional<Role> role = this.roleRepository.findById(id);

		try {
			this.roleRepository.delete(role.orElseThrow(() -> new ObjectNotFoundException(
					messageSource.getMessage(MESSAGE_NOT_FOUND, new Object[] { ROLE }, null))));
		} catch (DataIntegrityViolationException e) {
			throw new SQLOperationsException(messageSource.getMessage(MESSAGE_NOT_AUTHORIZED, null, null));
		}
	}

}
