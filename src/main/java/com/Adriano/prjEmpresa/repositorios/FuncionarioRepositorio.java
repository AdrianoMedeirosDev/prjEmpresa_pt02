package com.Adriano.prjEmpresa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Adriano.prjEmpresa.entidades.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{
	@Query("SELECT f FROM Funcionario f WHERE LOWER(f.funnome) LIKE LOWER(CONCAT('%', :funnome, '%'))")
	List<Funcionario> findByNomeContaining(@Param("funnome") String funnome);

}
