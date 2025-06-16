/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.FuncionarioDao;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class FuncionarioService {

    private static Funcionario funcionario;

    private final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public void salvarFuncionario(Funcionario funcionario) throws CadastroException, SQLException {
        funcionarioDao.inserirFuncionario(funcionario);
    }

    public List<Funcionario> buscarFuncionarios() {
        return funcionarioDao.buscarTodosFuncionarios();
    }

    private void validandoFuncionario(Funcionario funcionario) throws CadastroException {
        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new CadastroException("Nome do funcionário não informado!");
        }
        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroException("CPF do funcionário não informado!");
        }
        if (funcionario.getDataNascimento() == null) {
            throw new CadastroException("Data de nascimento não informada!");
        }
        if (funcionario.getDataNascimento().isAfter(LocalDate.now())) {
            throw new CadastroException("Data de nascimento inválida!");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().isBlank()) {
            throw new CadastroException("E-mail do funcionário não informado!");
        }
        if (funcionario.getTelefone() == null || funcionario.getTelefone().isBlank()) {
            throw new CadastroException("Telefone do funcionário não informado!");
        }
        if (funcionario.getSenhaHash() == null || funcionario.getSenhaHash().isBlank()) {
            throw new CadastroException("Senha do funcionário não informada!");
        }
        
        if (funcionario.getAgencia() == null || funcionario.getAgencia().getClass() == null) {
            throw new CadastroException("Agência do funcionário não informada!");
        }
    }

    public static void main(String[] args) throws SQLException {
        FuncionarioService funcionarioService = new FuncionarioService();
 
        try {
            funcionarioService.salvarFuncionario(funcionario);
        } catch (CadastroException ex) {
            Logger.getLogger(AgenciaService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
