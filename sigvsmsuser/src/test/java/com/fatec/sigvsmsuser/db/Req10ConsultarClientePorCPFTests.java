package com.fatec.sigvsmsuser.db;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.service.ClienteRepository;
@DataJpaTest
class Req10ConsultarClientePorCPFTests {

	private Cliente cliente;
	@Autowired
	private ClienteRepository clienteRepository;
	@BeforeEach
	public void setUp() {
	    // Initialize test data before each test method
		cliente = new Cliente();
		cliente.setCpf("80983098000");
		cliente.setNome("Jose da Silva");
		cliente.setCep("01310-100");
		cliente.setEndereco("Av. Paulista");
		cliente.setEmail("jose@gmail.com");
		cliente.setDataCadastro();
		clienteRepository.save(cliente);
	}
	@Test
	void ct01_quando_cliente_cadastrado_retorna_detalhes() {
		Optional<Cliente> cliente = clienteRepository.findByCpf("80983098000");
		assertTrue (cliente.isPresent());
	}
	
	@Test
	void ct02_quando_cliente_nao_cadastrado_retorna_null() {
		Cliente cliente = clienteRepository.findByCpf("80983098001").orElse(null);
		assertNull (cliente);
	}
	@Test
	void ct03_quando_cliente_invalido_retorna_null() {
		Cliente cliente = clienteRepository.findByCpf("").orElse(null);
		assertNull (cliente);
	}
}
