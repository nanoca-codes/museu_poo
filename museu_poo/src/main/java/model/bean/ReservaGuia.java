package model.bean;

public class ReservaGuia {
    private Reserva reserva;
    private Guia guia;

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }  
        
   @Override
    public String toString() {
        String nome = (guia != null) ? guia.getNomeGuia() : "";
        return "Guia: " + nome;
    }
}