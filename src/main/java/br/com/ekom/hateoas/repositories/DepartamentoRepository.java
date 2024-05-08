package br.com.ekom.hateoas.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ekom.hateoas.entities.Departamento;

public interface DepartamentoRepository extends CrudRepository<Departamento,Long> {

}