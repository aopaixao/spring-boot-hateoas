package br.com.ekom.hateoas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ekom.hateoas.entities.Empregado;
import br.com.ekom.hateoas.exceptions.NoSuchElementException;
import br.com.ekom.hateoas.repositories.EmpregadoRepository;

@Service
public class EmpregadoService {
	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public List<Empregado> findAll(){
		return empregadoRepository.findAll();
	}
	
	public Empregado findById(Long id) {
		
		return empregadoRepository.findById(id)
		        .orElseThrow(() -> new NoSuchElementException("Empregado", id));
		        
	}
	
	public Empregado save(Empregado Empregado) {
		return empregadoRepository.save(Empregado);
	}
	
	public Empregado update(Empregado Empregado) {
		return empregadoRepository.save(Empregado);
	}

	public Boolean delete(Empregado Empregado) {
		if(Empregado == null)
			return false;
		
		Empregado EmpregadoExistente = findById(Empregado.getEmpregadoId());
		
		if(EmpregadoExistente == null)
			return false;
		
		empregadoRepository.delete(Empregado);
		
		Empregado EmpregadoContinuaExistindo = 
				findById(Empregado.getEmpregadoId());
		
		if(EmpregadoContinuaExistindo == null)
			return true;

		return false;
	}	
}
