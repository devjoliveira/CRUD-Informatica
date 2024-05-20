/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.lojainformatica.models;

/**
 *
 * @author joaov
 */
public class Computador {
    private static String marca  = "JoaoVOCFerreira";
    private int idComputador;
    private String hd;
    private String processador;

    public String getMarca() {
        return marca;
    }

    public String getHd() {
        return hd;
    }

    public String getProcessador() {
        return processador;
    }

    public int getIdComputador() {
        return idComputador;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public void setIdComputador(int idComputador) {
        this.idComputador = idComputador;
    }

    public Computador() {
        
    }

    public Computador(int idComputador, String hd, String processador) {
        this.idComputador = idComputador;
        this.hd = hd;
        this.processador = processador;
    }
    
    public Computador(int idComputador, String marca, String hd, String processador) {
        this.idComputador = idComputador;
        Computador.marca = marca;
        this.hd = hd;
        this.processador = processador;
    }

    public Computador(String hd, String processador) {
        this.hd = hd;
        this.processador = processador;
    }
    
    
    
}
