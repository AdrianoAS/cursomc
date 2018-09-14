package com.adrianoSantos.curosmc.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagt, Date instanceDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanceDoPedido);
		cal.add(Calendar.DAY_OF_MONTH,7);
		pagt.setDataPagamento(cal.getTime());
	}
}
