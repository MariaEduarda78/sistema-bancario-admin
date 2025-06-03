/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.dao;

import java.sql.ResultSet;
import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class GerenteDao {

    private List<Gerente> gerentes;

    public void inserirGerente(Gerente gerente) throws SQLException {
        String sql = "INSERT INTO gerentes(nome,cpf, data_nascimento, email, telefone,senha_hash, agencia) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = MySQL.connect(); PreparedStatement psd = con.prepareStatement(sql)) {
            psd.setString(1, gerente.getNome());
            psd.setString(2, gerente.getCpf());
            psd.setDate(3, Date.valueOf(gerente.getDataNascimento()));
            psd.setString(4, gerente.getEmail());
            psd.setString(5, gerente.getTelefone());
            psd.setString(6, gerente.getSenhaHash());
            psd.setLong(7, gerente.getAgencia().getIdAgencia());
            psd.execute();

        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Gerente> buscarTodosGerentes() {
        List<Gerente> gerentes = new ArrayList<>();
        String sql = "SELECT * FROM gerentes";
        try (Connection con = MySQL.connect(); PreparedStatement psd = con.prepareStatement(sql)) {
            ResultSet rd = psd.executeQuery();
            while (rd.next()) {
                Gerente g = new Gerente();
                g.setIdGerente(rd.getLong("id_gerente"));
                g.setNome(rd.getString("cpf"));
                g.setDataNascimento(rd.getDate("data_nascimento").toLocalDate());
                g.setEmail(rd.getString("email"));
                g.setTelefone(rd.getString("telefone"));
                g.setSenhaHash(rd.getString("senha_hash"));

                Agencia agencia = new Agencia();
                agencia.setIdAgencia(rd.getLong("agencia"));
                g.setAgencia(agencia);
                gerentes.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gerentes;
    }

}
