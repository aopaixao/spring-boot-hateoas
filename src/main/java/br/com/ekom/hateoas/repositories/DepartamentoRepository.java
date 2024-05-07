package br.com.ekom.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ekom.hateoas.entities.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {

}