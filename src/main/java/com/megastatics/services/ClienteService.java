package com.megastatics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.megastatics.domain.Cliente;
import com.megastatics.dto.ClienteDTO;
import com.megastatics.repositories.ClienteRepository;
import com.megastatics.services.exception.DataIntegrityException;
import com.megastatics.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente cliente = findById(obj.getId());
		updateData(cliente, obj);
		return repository.save(cliente);
	}
	
	private void updateData(Cliente cliente, Cliente obj) {
		cliente.setEmail(obj.getEmail());
		cliente.setNome(obj.getNome());
	}

	public void delete(Integer codigo) {
		findById(codigo);
		try {
			repository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possível excluir um Cliente que possui pedidos associados!");
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

}
