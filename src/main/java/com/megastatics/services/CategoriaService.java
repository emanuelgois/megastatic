package com.megastatics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.megastatics.domain.Categoria;
import com.megastatics.dto.CategoriaDTO;
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
		Categoria categoria = findById(obj.getCodigo());
		updateData(categoria, obj);
		return repository.save(obj);
	}
	
	
	private void updateData(Categoria categoria, Categoria obj) {
		categoria.setNome(obj.getNome());
	}

	public void delete(Integer codigo) {
		findById(codigo);
		try {
			repository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possível excluir uma categoria que possui produtos associados!");
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}	
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getCodigo(), dto.getNome());
	}

}
