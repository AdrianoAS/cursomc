package com.adrianoSantos.curosmc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adrianoSantos.curosmc.domain.Categoria;
import com.adrianoSantos.curosmc.domain.Cidade;
import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.domain.Endereco;
import com.adrianoSantos.curosmc.domain.Estado;
import com.adrianoSantos.curosmc.domain.Produto;
import com.adrianoSantos.curosmc.domain.unums.TipoCliente;
import com.adrianoSantos.curosmc.repositorys.CategoriaRepository;
import com.adrianoSantos.curosmc.repositorys.CidadeRepository;
import com.adrianoSantos.curosmc.repositorys.ClienteRepository;
import com.adrianoSantos.curosmc.repositorys.EnderecoReposiroty;
import com.adrianoSantos.curosmc.repositorys.EstadoRepository;
import com.adrianoSantos.curosmc.repositorys.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaReposiotory;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoReposiroty enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
	
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaReposiotory.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria silva","Maria@gmail.com","363789127330",TipoCliente.PessoaFisica);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null,"Rua flores","300","Apto 303","Jardim","38220234",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida","105","sala 800","centro","38777012",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
		
		
		
		
	}
}
