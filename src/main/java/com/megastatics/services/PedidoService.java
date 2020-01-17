package com.megastatics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megastatics.domain.Pedido;
import com.megastatics.repositories.PedidoRepository;
import com.megastatics.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
