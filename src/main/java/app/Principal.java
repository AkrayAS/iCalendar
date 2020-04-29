package app;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Classe Principal do Programa.
 * @author Arthur Anastopulos dos Santos.
 */
public class Principal {
    public static void main(String[] args) {
        VCalendar vCalendar = new VCalendar("-//Arthur A.//Blá Blá//PT_BR");
        LocalDateTime dtStart = LocalDateTime.of(2020, 04, 8, 13, 30, 00);;
        LocalDateTime dtEnd = LocalDateTime.of(2020, 04, 8, 23, 59, 00);;

        if (vCalendar.addVEvent("Projeto", dtStart, dtEnd, "Casa")) System.out.println("Sucesso ao adicionar.");
        else System.out.println("Erro ao adicionar.");

        if (vCalendar.addVEvent("Projeto2", dtStart, dtStart, "Casa")) System.out.println("Sucesso ao adicionar.");
        else System.out.println("Erro ao adicionar.");

        if(vCalendar.removeVEvent("Projeto")) System.out.println("Sucesso ao remover.");
        else System.out.println("Erro ao remover.");

        if(vCalendar.updateVEvent("Projeto2","Projeto", dtStart, dtEnd, "Casa")) System.out.println("Sucesso ao atualizar.");
        else System.out.println("Erro ao atualizar.");

        ArrayList<String> byday = new ArrayList<String>();
        byday.add("MO");
        byday.add("TU");
        LocalDateTime until = LocalDateTime.of(2020, 12, 8, 16, 00, 00);
        if(vCalendar.setRule(0, "WEEKLY", 1, until,byday)) {
            System.out.println("Sucesso em adicioar Rules.");
        } else System.out.println("Erro ao adicionar rules.");

        LocalDateTime exDate = LocalDateTime.of(2020, 10, 8, 16, 00 , 00);
        if(vCalendar.setExDate(0, exDate)) System.out.println("Sucesso em adiconar ExDate.");
        else System.out.println("Erro ao adicionar ExDate.");

        if(vCalendar.addVEvent("Evento", dtStart, dtEnd, "IFSC-SJ"))
            System.out.println("Sucesso.");
        if(vCalendar.setRule(1, "DAILY", 3, until,byday))
            System.out.println("Sucesso.");
        if(vCalendar.setExDate(1, exDate))
            System.out.println("Sucesso em adiconar ExDate.");

        System.out.println("\n");

        System.out.println(vCalendar.toString());
    }
}