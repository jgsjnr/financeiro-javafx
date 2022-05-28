/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.financeiro;

import java.util.Date;

/**
 *
 * @author Tiago
 */
public class ListaCompras {
    
    private int ID;
    private int precoCompra;
    private String dataCompra;
    private String Categoria;

    public ListaCompras(int ID, int precoCompra, String dataCompra, String Categoria) {
        this.ID = ID;
        this.precoCompra = precoCompra;
        this.dataCompra = dataCompra;
        this.Categoria = Categoria;
    }
    public ListaCompras(){};

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(int precoCompra) {
        this.precoCompra = precoCompra;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
        
    }
}
