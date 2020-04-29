package app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que representa um VCALENDAR, o principal elemento de um objeto iCalendar.
 *
 * @author Arthur Anastopulos dos Santos
 */

public class VCalendar {
    //attributes
    private String version;
    private final String VER = "2.0";
    private String proID;
    private ArrayList<VEvent> vEvents = new ArrayList<VEvent>();

    /**
     * Construtor VCalendar.
     * @param proID Identificação do aplicativo que gerou o agendamento.
     */
    public VCalendar(String proID) {
        this.version = VER;
        this.proID = proID;
    }

    /**
     * Construtor VCalendar.
     * @param version Informação obrigatória que indica a versão da especificação. Padrão = 2.0
     * @param proID Identificação do aplicativo que gerou o agendamento.
     * @param vEvents responsável por representar um evento.
     */
    public VCalendar(String version, String proID, ArrayList<VEvent> vEvents) {
        this.version = version;
        this.proID = proID;
        this.vEvents = vEvents;
    }

    /**
     * Adiciona um Evento.
     * @param summary O assunto do evento.
     * @param dtStart Indica a data e hora que o evento começa.
     * @param dtEnd  Indica a data e hora que o evento termina.
     * @param location Um texto livre para indicar o local do evento.
     * @return True ou False dependendo se não houve erro no método.
     */
    public boolean addVEvent(String summary, LocalDateTime dtStart, LocalDateTime dtEnd, String location) {
        try {
            vEvents.add(new VEvent(summary, dtStart, dtEnd, location));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Coloca Regras a um Evento.
     * @param pIndex posição do ArrayList de Eventos
     * @param freq Frequencia, por exemplo: "DAILY, WEEKLY, MONTLHY, YEARLY".
     * @param interval Indica o intervalo de repetição.
     * @param until  término da repetição.
     * @param byday indica que o evento repete em determinados dias da Semana.
     * @return True ou False dependendo se não houve erro no método.
     */
    public boolean setRule(int pIndex, String freq, int interval, LocalDateTime until, ArrayList<String> byday) {
        try {
            VEvent vEvent =this.vEvents.get(pIndex);
            vEvent.setRule(freq, interval, until, byday);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Coloca em uma Regra, uma exceção de Data.
     * @param pIndex posição do ArrayList de Eventos.
     * @param date uma data e hora que deverá ser excluída de eventos recorrentes.
     * @return True ou False dependendo se não houve erro no método.
     */
    public boolean setExDate(int pIndex, LocalDateTime date) {
        try {
            VEvent vEvent =this.vEvents.get(pIndex);
            vEvent.setExDate(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Remove um Evento, apartir de seu assunto.
     * @param summary O assunto do evento.
     * @return True ou False dependendo se não houve erro no método.
     */
    public boolean removeVEvent(String summary) {
        try {
            Iterator<VEvent> iterator = vEvents.iterator();
            while (iterator.hasNext()) {
                VEvent element = iterator.next();
                String str = element.getSummary();
                if(str.equalsIgnoreCase(summary)) iterator.remove();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Atualiza um Evento.
     * @param summary O assunto do evento original.
     * @param newSummary O novo assunto do evento.
     * @param dtStart Indica a data e hora que o evento começa.
     * @param dtEnd Indica a data e hora que o evento termina.
     * @param location Um texto livre para indicar o local do evento.
     * @return True ou False dependendo se não houve erro no método.
     */
    public boolean updateVEvent(String summary,String newSummary, LocalDateTime dtStart, LocalDateTime dtEnd, String location) {
        try {
            for (VEvent element : vEvents) {
                String str = element.getuID();
                if (str.equalsIgnoreCase(summary)) {
                    element.updateVEvent(newSummary, dtStart, dtEnd, location);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * toString().
     * @return Retorna uma String, no formatdo iCalendar.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("BEGIN:VCALENDAR" + "\n");
        str.append("VERSION:").append(this.version).append("\n");
        str.append("PRODID:").append(this.proID).append("\n");

        for (VEvent vEvent : this.vEvents) {
            str.append("BEGIN:VEVENT" + "\n");
            str.append("SUMMARY:").append(vEvent.getSummary()).append("\n");
            str.append("DTSTAMP:").append(vEvent.getDtStamp()).append("\n");
            str.append("DTSTART:").append(vEvent.getDtStart()).append("\n");
            str.append("DTEND:").append(vEvent.getDtEnd()).append("\n");
            str.append("UID:").append(vEvent.getuID()).append("\n");
            str.append(vEvent.getrRule()).append("\n");
            str.append("LOCATION:").append(vEvent.getLocation()).append("\n");
            str.append("END:VEVENT" + "\n");
        }
        str.append("END:VCALENDAR");
        return str.toString();
    }
}