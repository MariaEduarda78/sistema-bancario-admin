/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.GerenteDao;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class GerenteService {

    private static Gerente gerente;

    private final GerenteDao gerenteDao = new GerenteDao();

    public void salvarGerente(Gerente gerente) throws CadastroException {
        if (gerente.getNome() == null || gerente.getNome().isBlank()) {
            throw new CadastroException("Gerente não possui um nome.");
        }
        if (gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroException("Gerente não possui um cpf informado!!");
        }
        try {
            gerenteDao.inserirGerente(gerente);
        } catch (SQLException ex) {
            throw new CadastroException("Erro ao salvar gerente: " + ex.getMessage());
        }
    }

    public List<Gerente> buscarGerentes() {
        return gerenteDao.buscarTodosGerentes();
    }

    public static void main(String[] args) throws SQLException {
        GerenteService gerenteService = new GerenteService();
        Gerente gerente = new Gerente();
        gerente.setNome("Henrique Lima");
        gerente.setCpf("14515264515");
        gerente.setDataNascimento(LocalDate.of(2008, 9, 12));
        gerente.setEmail("henriquelimaa90@gmail.com");
        gerente.setTelefone("35998561896");
        gerente.setSenhaHash("senha123");
        var agencia = new Agencia();
        agencia.setIdAgencia(1L);
        gerente.setAgencia(agencia);

        try {
            gerenteService.salvarGerente(gerente);
        } catch (CadastroException ex) {
            Logger.getLogger(GerenteService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
