package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Classe que representa um Evento do iCalendar.
 * @author Arthur Anastopulos dos Santos
 */

public class VEvent {
    //attributes
    private String summary;
    private String uID;
    private LocalDateTime dtStamp;
    private LocalDateTime dtStart;
    private LocalDateTime dtEnd;
    private String location;
    private RRule rRule;
    private final String[] FREQ = {"DAILY", "WEEKLY", "MONTLHY", "YEARLY"};

    /**
     * Construtor VEvent
     * @param summary O assunto do evento.
     * @param dtStart Indica a data e hora que o evento começa.
     * @param dtEnd Indica a data e hora que o evento termina.
     * @param location Um texto livre para indicar o local do evento.
     */
    public VEvent(String summary, LocalDateTime dtStart, LocalDateTime dtEnd, String location) {
        this.summary = summary;
        this.uID = UUID.randomUUID().toString();
        this.dtStamp = LocalDateTime.now();
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.location = location;
    }

    /**
     * Atualiza um Evento.
     * @param summary O assunto do evento.
     * @param dtStart Indica a data e hora que o evento começa.
     * @param dtEnd Indica a data e hora que o evento termina.
     * @param location Um texto livre para indicar o local do evento.
     */
    public void updateVEvent(String summary, LocalDateTime dtStart, LocalDateTime dtEnd, String location) {
        this.summary = summary;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.location = location;
    }

    /**
     * Coloca uma Regra ao Evento.
     * @param freq Frequencia, por exemplo: "DAILY, WEEKLY, MONTLHY, YEARLY".
     * @param interval Indica o intervalo de repetição.
     * @param until término da repetição.
     * @param byday ndica que o evento repete em determinados dias da Semana.
     * @return True ou False dependendo se não houve erro no método.
     */
    public boolean setRule(String freq, int interval, LocalDateTime until, ArrayList<String> byday) {
        if(!freq.equalsIgnoreCase(FREQ[0]) && !freq.equalsIgnoreCase(FREQ[1]) &&
                !freq.equalsIgnoreCase(FREQ[2]) && !freq.equalsIgnoreCase(FREQ[3]))
            return false;
        this.rRule = new RRule(freq,interval,until,byday);
        return true;
    }

    public boolean setExDate(LocalDateTime date) {
        return this.rRule.setExDate(date);
    }

    private String dataformatter(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
    }

    public String getSummary() {
        return summary;
    }

    public String getuID() {
        return uID;
    }

    public String getDtStamp() { return dataformatter(dtStamp); }

    public String getDtStart() { return dataformatter(dtStart); }

    public String getDtEnd() {
        return dataformatter(dtEnd);
    }

    public String getLocation() {
        return location;
    }

    public RRule getrRule() {
        return rRule;
    }

}