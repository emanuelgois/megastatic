package com.megastatics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.megastatics.domain.Categoria;
import com.megastatics.repositories.CategoriaRepository;
import com.megastatics.services.exception.DataIntegrityException;
import com.megastatics.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setCodigo(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getCodigo());
		return repository.save(obj);
	}
	
	public void delete(Integer codigo) {
		findById(codigo);
		try {
			repository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possível excluir uma categoria que possui produtos associados!");
		}
	}	

}
