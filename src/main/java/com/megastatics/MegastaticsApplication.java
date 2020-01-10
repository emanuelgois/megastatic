package com.megastatics;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.megastatics.domain.Categoria;
import com.megastatics.domain.Cidade;
import com.megastatics.domain.Estado;
import com.megastatics.domain.Produto;
import com.megastatics.repositories.CategoriaRepository;
import com.megastatics.repositories.CidadeRepository;
import com.megastatics.repositories.EstadoRepository;
import com.megastatics.repositories.ProdutoRepository;

@SpringBootApplication
public class MegastaticsApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private ProdutoRepository prodRepository;
	@Autowired
	private EstadoRepository ufRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MegastaticsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria info = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		Categoria limpeza = new Categoria(null, "Limpeza");
		
		Produto note = new Produto(null, "Notebook", new BigDecimal(4999.00));
		note.getCategorias().add(info);
		Produto fone = new Produto(null, "Head Fone JBL", new BigDecimal(399.00));
		note.getCategorias().add(info);
		Produto ssd = new Produto(null, "SSD sanDisk", new BigDecimal(250));
		ssd.getCategorias().add(info);
		info.getProdutos().addAll(Arrays.asList(note,fone,ssd));
		
		Produto mesa = new Produto(null, "Mesa L", new BigDecimal(700));
		Produto cadeira = new Produto(null, "Cadeira de rodinha", new BigDecimal(500));
		escritorio.getProdutos().addAll(Arrays.asList(mesa,cadeira));
		mesa.getCategorias().add(escritorio);
		cadeira.getCategorias().add(escritorio);
		
		repository.saveAll(Arrays.asList(info,escritorio,limpeza));
		prodRepository.saveAll(Arrays.asList(note,fone,ssd,mesa,cadeira));
		
		Estado ce = new Estado(null, "Cerá", "CE");
		Estado pe = new Estado(null, "Pernanbuco", "PE");
		
		Cidade fortaleza = new Cidade(null, "Fortaleza", ce);
		Cidade aquiraz = new Cidade(null, "Aquiraz", ce);
		ce.getCidades().addAll(Arrays.asList(fortaleza,aquiraz));
		
		Cidade recife = new Cidade(null, "Recife", pe);
		Cidade olinda = new Cidade(null, "Olinda", pe);
		pe.getCidades().addAll(Arrays.asList(recife,olinda));
		
		ufRepository.saveAll(Arrays.asList(ce,pe));
		cidadeRepository.saveAll(Arrays.asList(fortaleza,aquiraz,recife,olinda));
		
	}

}
