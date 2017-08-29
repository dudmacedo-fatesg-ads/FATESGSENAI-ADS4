package br.com.eduardo.corridadesapos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Podio {

    public static int nsapos = 12;
    public static int percurso = 0;
    public static final HashMap<Long, Sapo> podio = new HashMap<>();

    public static void finalizou(Sapo sapo) {
        synchronized (podio) {
            if (sapo.getProgresso() >= percurso) {
                long time = System.currentTimeMillis();
                podio.put(time, sapo);
            }

            if (podio.size() == nsapos) {
                ArrayList<Long> keys = new ArrayList<>();
                keys.addAll(podio.keySet());
                keys.sort(Long::compareTo);
                for (Long k : keys) {
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(k);
                    System.out.println("Sapo " + podio.get(k).getId() + " chegou. "
                            + new SimpleDateFormat("HH:mm:ss.SSS").format(cal.getTime()));
                }
            }
        }
    }
}
