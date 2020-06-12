package br.com.poemeunome.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_has_role")
public class UserRole {

	@EmbeddedId
	UserRoleID id;
	
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name="user_id")
	User user;
	
	@ManyToOne
	@MapsId("role_id")
	@JoinColumn(name="role_id")
	Role role;

}
