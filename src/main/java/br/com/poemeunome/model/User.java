package br.com.poemeunome.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.poemeunome.model.dto.UserDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@EqualsAndHashCode.Exclude
	@Column(nullable = false)
	private String nome;
	@EqualsAndHashCode.Exclude
	@Column(unique = true, nullable = false)
	private String email;
	@EqualsAndHashCode.Exclude
	@Column(nullable = false)
	private String password;
	@EqualsAndHashCode.Exclude
	@Column(nullable = false)
	private boolean enable;

	@ManyToMany
	@JoinTable(name = "user_has_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> permissoes;

	public User() {
	}

	public User(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public User(UserDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
	}

	public User(User user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.enable = user.isEnable();
		this.permissoes = user.getPermissoes();
	}

}
