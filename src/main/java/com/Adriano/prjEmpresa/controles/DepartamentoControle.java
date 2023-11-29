package com.Adriano.prjEmpresa.controles;

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

import com.Adriano.prjEmpresa.entidades.Departamento;
import com.Adriano.prjEmpresa.servicos.DepartamentoServico;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/departamento")
public class DepartamentoControle {
	private final DepartamentoServico departamentoServico;

	public DepartamentoControle(DepartamentoServico departamentoServico) {
		this.departamentoServico = departamentoServico;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza departamento por ID")
	public ResponseEntity<Departamento> findDepartamentobyId(@PathVariable Long depcodigo) {
		Departamento departamento = departamentoServico.findDepartamentoById(depcodigo);
		if (departamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os departamentos")
	public ResponseEntity<List<Departamento>> findAllDepartamentos() {
		List<Departamento> departamentos = departamentoServico.findAllDepartamento();
		return ResponseEntity.ok(departamentos);
	}

	@PostMapping
	@Operation(summary = "Cadastra um departamento")
	public ResponseEntity<Departamento> insertDepartamentosControl(@RequestBody @Valid Departamento departamento) {
		Departamento novoDepartamento = departamentoServico.insertDepartamento(departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoDepartamento);
	}

	@PutMapping("/id")
	@Operation(summary = "Altera um departamento")
	public ResponseEntity<Departamento> updateDepartamentoControl(@PathVariable Long depcodigo, @RequestBody @Valid Departamento departamento) {
		Departamento mudadepartamento = departamentoServico.updateDepartamento(depcodigo, departamento);
		if (mudadepartamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id")
	@Operation(summary = "Exclui um departamento")
	public ResponseEntity<String> deleteDepartamento(@PathVariable Long depcodigo) {
		boolean remover = departamentoServico.deleteDepartamento(depcodigo);
		if (remover) {
			return ResponseEntity.ok().body("departamento Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
