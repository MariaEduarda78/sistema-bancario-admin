/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.ClienteDao;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class ClienteService {

    private static Cliente cliente;

    private final ClienteDao clienteDao = new ClienteDao();

    public void salvarCliente(Cliente cliente) throws CadastroException {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new CadastroException("Cliente não possui um nome informado!!");
        }
        
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new CadastroException("Cliente não possui um cpf informado!!");
        }
        
        if (cliente.getDataNascimento() == null) {
            throw new CadastroException("Cliente não possui uma data de nascimento informada!!");
        }
        
        clienteDao.inserirCliente(cliente);
    }

    public List<Cliente> buscarClientes() {
        return clienteDao.buscarTodosClientes();
    }

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();

        Cliente cliente = new Cliente(1L, "João da Silva", "18546984579",LocalDate.of (2008,05,5), "joaodasilva78@gmail.com", "15426joao", "35998759648");
        
        try {
            clienteService.salvarCliente(cliente);
        } catch (CadastroException ex) {
            Logger.getLogger(AgenciaService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}


