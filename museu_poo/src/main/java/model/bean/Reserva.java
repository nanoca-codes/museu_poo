package model.bean;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Reserva {
    private int idReserva;
    private Date dataReserva;
    private Time horario;
    private String status;   
    private Visitante visitante;
    private ArrayList<ReservaAtracao> atracoes = new ArrayList<>();
    private ArrayList<ReservaGuia> guias = new ArrayList<>();

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public ArrayList<ReservaAtracao> getAtracoes() {
        return atracoes;
    }

    public void setAtracoes(ArrayList<ReservaAtracao> atracoes) {
        this.atracoes = atracoes;
    }

    public ArrayList<ReservaGuia> getGuias() {
        return guias;
    }

    public void setGuias(ArrayList<ReservaGuia> guias) {
        this.guias = guias;
    }
    
    public void addAtracao(ReservaAtracao atracao) {
        this.atracoes.add(atracao);
    }
    
    public void addGuia(ReservaGuia guia) {
        this.guias.add(guia);
    }
    
    @Override
    public String toString() {
        return "Reserva " + idReserva + " - Status: " + status;
    }  
}