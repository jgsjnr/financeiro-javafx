/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.financeiro;

/**
 *
 * @author junior
 */
public class Usuario {
    private String usuario;
    private String senha;
    private boolean sessao;
    private int id;
    public Usuario(){};
    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
        this.id = id;
    }
    public Usuario(String usuario, String senha, int id){
        this.usuario = usuario;
        this.senha = senha;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getSessao() {
        return sessao;
    }

    public void setSessao(boolean sessao) {
        this.sessao = sessao;
    }
    
    @Override
    public String toString(){
        return this.usuario;
    }
    
    
}
