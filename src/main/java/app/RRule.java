package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe baseada nas Regras de um evento do iCalendar.
 * @author Arthur Anastopulos dos Santos.
 */
public class RRule {
    private String freq;
    private int interval;
    private LocalDateTime until;
    private ArrayList<String> byday = new ArrayList<String>();
    private ArrayList<LocalDateTime> exDate = new ArrayList<LocalDateTime>();

    /**
     * Construtor RRule
     * @param freq Frequencia, por exemplo: "DAILY, WEEKLY, MONTLHY, YEARLY".
     * @param interval Indica o intervalo de repetição.
     * @param until término da repetição.
     * @param byday indica que o evento repete em determinados dias da Semana.
     */
    public RRule(String freq, int interval, LocalDateTime until, ArrayList<String> byday) {
        this.freq = freq;
        this.interval = interval;
        this.until = until;
        this.byday = byday;
    }

    public boolean setExDate(LocalDateTime date) {
        return this.exDate.add(date);
    }

    /**
     * toString()
     * @return Retorna uma String das Regras de evento, no formatdo iCalendar.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("RRULE:");
        str.append("FREQ=").append(this.freq);
        str.append(";UNTIL=").append(this.until);
        str.append(";BYDAY:");
        int cont = 0;
        for (String aux : this.byday) {
            str.append(aux);
            cont++;
            if(cont != this.byday.size()) str.append(",");
        }
        cont = 0;
        str.append("\n");
        str.append("EXDATE:");
        for (LocalDateTime aux : this.exDate) {
            str.append(aux.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss")));
            cont++;
            if(cont != this.exDate.size()) str.append("\n").append("EXDATE:");
        }
        return str.toString();
    }
}