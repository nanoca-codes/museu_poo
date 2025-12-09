/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author acoli
 */
public class Guia {
    private int idGuia;
    private String nomeGuia;
    private String especialidade;
    private String emailGuia;

    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    public String getNomeGuia() {
        return nomeGuia;
    }

    public void setNomeGuia(String nomeGuia) {
        this.nomeGuia = nomeGuia;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmailGuia() {
        return emailGuia;
    }

    public void setEmailGuia(String emailGuia) {
        this.emailGuia = emailGuia;
    }
    
    @Override
    public String toString() {
        return this.nomeGuia; 
    }   
}