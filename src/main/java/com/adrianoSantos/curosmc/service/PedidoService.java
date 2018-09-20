package com.adrianoSantos.curosmc.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.domain.ItemPedido;
import com.adrianoSantos.curosmc.domain.PagamentoComBoleto;
import com.adrianoSantos.curosmc.domain.Pedido;
import com.adrianoSantos.curosmc.domain.unums.EstadoPagamento;
import com.adrianoSantos.curosmc.exception.AuthorizationException;
import com.adrianoSantos.curosmc.exception.ObjectNotFoundException;
import com.adrianoSantos.curosmc.repositorys.ItemPedidoRepository;
import com.adrianoSantos.curosmc.repositorys.PagamentoRepository;
import com.adrianoSantos.curosmc.repositorys.PedidoRepository;
import com.adrianoSantos.curosmc.security.UserSpringSecurity;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado" + id + ", Tipo:" + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagt = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagt, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip :  obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public Page<Pedido> fingPage(Integer page, Integer linesPages, String orderBy, String direction){
		UserSpringSecurity user = UserService.Authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageResquest = PageRequest.of(page, linesPages, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageResquest);
	}
}
