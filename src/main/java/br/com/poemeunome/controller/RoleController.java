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

import br.com.poemeunome.model.dto.RoleDTO;
import br.com.poemeunome.model.dto.UserDTO;
import br.com.poemeunome.service.RoleService;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/role")
	private ResponseEntity<List<RoleDTO>> findAll() {
		List<RoleDTO> roles = this.roleService.findAll();
		return ResponseEntity.ok().body(roles);
	}

	@GetMapping("/role/{id}")
	private ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
		RoleDTO role = this.roleService.findById(id);
		return ResponseEntity.ok().body(role);
	}

	@PostMapping("/role")
	private ResponseEntity<RoleDTO> create(@RequestBody RoleDTO dto) {
		RoleDTO role = this.roleService.createOrUpdate(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(role);
	}

	@PutMapping("/role")
	private ResponseEntity<RoleDTO> update(@RequestBody RoleDTO dto) {
		RoleDTO role = this.roleService.createOrUpdate(dto);
		return ResponseEntity.ok().body(role);
	}

	@DeleteMapping("/role/{id}")
	private ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		this.roleService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
