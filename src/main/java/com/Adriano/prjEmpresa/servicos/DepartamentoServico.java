package com.Adriano.prjEmpresa.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Adriano.prjEmpresa.entidades.Departamento;
import com.Adriano.prjEmpresa.repositorios.DepartamentoRepositorio;

@Service
public class DepartamentoServico {

	private final DepartamentoRepositorio departamentoRepositorio;

	public DepartamentoServico(DepartamentoRepositorio departamentoRepositorio) {
		this.departamentoRepositorio = departamentoRepositorio;
	}

	// preparando as buscas por id
	public Departamento findDepartamentoById(Long depcodigo) {
		Optional<Departamento> Departamento = departamentoRepositorio.findById(depcodigo);
		return Departamento.orElse(null);
	}

	// preparando a busca geral
	public List<Departamento> findAllDepartamento() {
		return departamentoRepositorio.findAll();
	}

	// salvando o Jogo
	public Departamento insertDepartamento(Departamento departamento) {
		return departamentoRepositorio.save(departamento);
	}

	// fazendo o update do jogo com o optional
	public Departamento updateDepartamento(Long depcodigo, Departamento novoDepartamento) {
		Optional<Departamento> departamentoOptional = departamentoRepositorio.findById(depcodigo);
		if (departamentoOptional.isPresent()) {
			Departamento departamentoExistente = departamentoOptional.get();
			departamentoExistente.setDepnome(novoDepartamento.getDepnome());
			departamentoExistente.setDepcodigo(novoDepartamento.getDepcodigo());
			return departamentoRepositorio.save(departamentoExistente);
		} else {
			return null;
		}
	}

	// deletando o update do departamento com o optional
	public boolean deleteDepartamento(Long depcodigo) {
		Optional<Departamento> departamentoExistente = departamentoRepositorio.findById(depcodigo);
		if (departamentoExistente.isPresent()) {
			departamentoRepositorio.deleteById(depcodigo);
			return true;
		} else {
			return false;
		}
	}
	}


