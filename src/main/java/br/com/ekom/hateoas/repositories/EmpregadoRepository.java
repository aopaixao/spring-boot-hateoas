package br.com.ekom.hateoas.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.ekom.hateoas.entities.Empregado;

public interface EmpregadoRepository extends CrudRepository<Empregado,Long> {

}