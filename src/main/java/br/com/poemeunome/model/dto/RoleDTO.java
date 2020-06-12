package br.com.poemeunome.model.dto;

import java.io.Serializable;

import br.com.poemeunome.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	public RoleDTO() {
	}

	public RoleDTO(Role role) {
		this.id = role.getId();
		this.nome = role.getNome();
	}

}
