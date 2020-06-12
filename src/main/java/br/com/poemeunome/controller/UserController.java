package br.com.poemeunome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poemeunome.model.Role;
import br.com.poemeunome.model.dto.UserDTO;
import br.com.poemeunome.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	private ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> usuarios = userService.findAll();
		return ResponseEntity.ok().body(usuarios);
	}

	@GetMapping("/user/{id}")
	private ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO usuario = this.userService.findById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@GetMapping("/user/{id}/roles")
	private ResponseEntity<List<Role>> findRoles(@PathVariable Long id) {
		List<Role> permissoes = this.userService.findRoles(id);
		return ResponseEntity.ok().body(permissoes);
	}

	@PostMapping("/user")
	private ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		UserDTO usuario = this.userService.createOrUpdate(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}

	@PutMapping("/user")
	private ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
		UserDTO usuario = this.userService.createOrUpdate(dto);
		return ResponseEntity.ok().body(usuario);
	}

	@DeleteMapping("/user/{id}")
	private ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		this.userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
