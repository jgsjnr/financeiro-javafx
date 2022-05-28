/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.financeiro;

import java.math.BigDecimal;
import java.sql.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author junior
 */
public class Valores {
    private int id, fk_id, fk_depara;
    private BigDecimal preco;
    private Date data;

    public Valores(int id, BigDecimal preco, Date data, int fk_id, int fk_depara) {
        this.id = id;
        this.fk_id = fk_id;
        this.fk_depara = fk_depara;
        this.preco = preco;
        this.data = data;
    }

    public Valores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    /*
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final FloatProperty preco = new SimpleFloatProperty();
    private final StringProperty data = new SimpleStringProperty();
    private final IntegerProperty fk_id = new SimpleIntegerProperty();
    private final IntegerProperty fk_depara = new SimpleIntegerProperty();
    public void SetId( id){
        this.id = id;
    }
    public IntegerProperty getId() {
        return id;
    }

    public FloatProperty getPreco() {
        return preco;
    }

    public StringProperty getData() {
        return data;
    }

    public IntegerProperty getFk_id() {
        return fk_id;
    }

    public IntegerProperty getFk_depara() {
        return fk_depara;
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_id() {
        return fk_id;
    }

    public void setFk_id(int fk_id) {
        this.fk_id = fk_id;
    }

    public int getFk_depara() {
        return fk_depara;
    }

    public void setFk_depara(int fk_depara) {
        this.fk_depara = fk_depara;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
    
    
}
