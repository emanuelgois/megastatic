package com.megastatics;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.megastatics.domain.Categoria;
import com.megastatics.domain.Cidade;
import com.megastatics.domain.Cliente;
import com.megastatics.domain.Endereco;
import com.megastatics.domain.Estado;
import com.megastatics.domain.Produto;
import com.megastatics.domain.enums.TipoCliente;
import com.megastatics.repositories.CategoriaRepository;
import com.megastatics.repositories.CidadeRepository;
import com.megastatics.repositories.ClienteRepository;
import com.megastatics.repositories.EnderecoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, fortaleza);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, recife);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		
	}

}
