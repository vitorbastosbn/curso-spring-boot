package br.com.poemeunome.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.poemeunome.model.Role;
import br.com.poemeunome.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	@JsonIgnore
	private String password;
	private boolean enable;
	private List<Role> permissoes;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.enable = user.isEnable();
		this.permissoes = user.getPermissoes();
	}

}
