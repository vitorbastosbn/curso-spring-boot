package br.com.poemeunome.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.poemeunome.model.dto.RoleDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Role implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public Role() {

	}

	public Role(RoleDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
