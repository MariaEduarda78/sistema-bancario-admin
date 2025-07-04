/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.view.tabelas;

import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maria
 */
public class TabelaAgencia extends AbstractTableModel {

    private List<Agencia> agencias = new ArrayList<>();
    private String[] colunas = new String[]{"Códio Agência", "UF", "Cidade", "CEP"};
// quantas linhas vai ter

    @Override
    public int getRowCount() {
        return agencias.size();
    }
// quantas colunas vai ter

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
// oq ele retorna de acordo com a coluna

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var ag = agencias.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> ag.getCodigoAgencia();
            case 1 -> ag.getUf();
            case 2 -> ag.getCidade();
            case 3 -> ag.getCep();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public void setAgencias(List<Agencia> agencias){
        this.agencias = agencias;
        fireTableDataChanged();
        
    }
}
