package be.vdab.cultuurhuis.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.*;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Long, Integer> reservaties = new LinkedHashMap<>();

    public boolean isLeeg() {

        return reservaties.isEmpty();
    }
    public void voegToe(long id, int aantal) {
        reservaties.put(id, aantal);
    }
    public boolean bevatReservatie(long id) {
        return reservaties.containsKey(id);
    }
    public long getAantalReservatiesVoorVoorstelling(long id){
        try {
            return reservaties.get(id);}
        catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }

    }
    public long getAantalReservatiesInMandje(){
         return reservaties.size();
    }
    public Set<Long> getIds() {
        return reservaties.keySet();
    }
    public void verwijderMandje() {
        reservaties.clear();
    }
    public void verwijderReservatie(long id) {
        reservaties.remove(id);
    }
    public Map<Long, Integer> getReservaties() {
        return reservaties;
    }
}
