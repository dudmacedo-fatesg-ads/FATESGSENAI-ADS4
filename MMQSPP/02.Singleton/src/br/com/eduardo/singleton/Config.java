package br.com.eduardo.singleton;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class Config {

    private static Config configuracao = null;

    private String DIR_ENTRADA;
    private String DIR_PENDENTE;
    private String DIR_PROCESSADOS;

    private String AMBIENTE;
    private String SISTEMA;

    private int TIMEOUT;

    private Config() {

    }
    
    public static Config getInstancia() {
        if (configuracao == null) {

            XStream xstream = new XStream(new DomDriver());
            
            xstream.addPermission(NoTypePermission.NONE);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.allowTypeHierarchy(Config.class);
            xstream.alias("Config", Config.class);

            configuracao = (Config) xstream.fromXML(new File("config.xml"));

        }
        return configuracao;
    }


    public String getDIR_ENTRADA() {
        return DIR_ENTRADA;
    }

    public String getDIR_PENDENTE() {
        return DIR_PENDENTE;
    }

    public String getDIR_PROCESSADOS() {
        return DIR_PROCESSADOS;
    }

    public String getAMBIENTE() {
        return AMBIENTE;
    }

    public String getSISTEMA() {
        return SISTEMA;
    }

    public int getTIMEOUT() {
        return TIMEOUT;
    }
}
