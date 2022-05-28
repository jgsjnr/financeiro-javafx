/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.financeiro;

/**
 *
 * @author junior
 */
public class Depara {
    private int id, depara;
    private String tipo;

    public Depara(int id, String tipo, int depara) {
        this.id = id;
        this.depara = depara;
        this.tipo = tipo;
    }

    public Depara() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepara() {
        return depara;
    }

    public void setDepara(int depara) {
        this.depara = depara;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString(){
        return this.tipo;
    }
    
}
