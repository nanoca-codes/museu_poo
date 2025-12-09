/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author acoli
 */
public class Visitante {
    private int idVisitante;
    private String nomeVisitante;
    private String emailVisitante;
    private String telVisitante;
    private Date dataNasc;
    private String senha;
    private String tipoUsuario;

    public int getIdVisitiante() {
        return idVisitante;
    }

    public void setIdVisitiante(int idVisitiante) {
        this.idVisitante = idVisitiante;
    }

    public String getNomeVisitante() {
        return nomeVisitante;
    }

    public void setNomeVisitante(String nomeVisitante) {
        this.nomeVisitante = nomeVisitante;
    }

    public String getEmailVisitante() {
        return emailVisitante;
    }

    public void setEmailVisitante(String emailVisitante) {
        this.emailVisitante = emailVisitante;
    }

    public String getTelVisitante() {
        return telVisitante;
    }

    public void setTelVisitante(String telVisitante) {
        this.telVisitante = telVisitante;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    @Override
    public String toString() {
        return this.nomeVisitante;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Visitante tp = (Visitante)obj;
        return this.idVisitante == tp.idVisitante;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(idVisitante);
    }
}