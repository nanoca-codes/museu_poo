package model.bean;

import java.sql.Time;

public class ReservaAtracao {
    private Reserva reserva;
    private Atracao atracao;
    private Time horarioVisita;

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Atracao getAtracao() {
        return atracao;
    }

    public void setAtracao(Atracao atracao) {
        this.atracao = atracao;
    }

    public Time getHorarioVisita() {
        return horarioVisita;
    }

    public void setHorarioVisita(Time horarioVisita) {
        this.horarioVisita = horarioVisita;
    }

     @Override
    public String toString() {
        String nome = (atracao != null) ? atracao.getNomeAtracao() : "";
        return "Atração: " + nome + " - Horário: " + horarioVisita;
    }
}