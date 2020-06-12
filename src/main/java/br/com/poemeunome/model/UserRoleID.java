package br.com.poemeunome.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleID implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	Long userId;

	@Column(name = "role_id")
	Long roleId;

}
