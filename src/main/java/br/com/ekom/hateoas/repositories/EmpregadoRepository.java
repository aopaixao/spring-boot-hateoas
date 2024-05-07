package br.com.ekom.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ekom.hateoas.entities.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado,Long> {

}
