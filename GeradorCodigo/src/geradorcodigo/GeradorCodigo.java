/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import classes.Marca;
import classes.Pessoa;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.List;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kairi
 */
public class GeradorCodigo {

    public static List<String> att = new ArrayList<>();
    public static HashMap<String, Object> map = new HashMap<String, Object>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Marca m = new Marca();
        Pessoa p = new Pessoa();
        //GeradorDAO gdao = new GeradorDAO(m);
        //GeradorView gv = new GeradorView(m);
        GeradorDAO gdao2 = new GeradorDAO(p);
        GeradorView gv2 = new GeradorView(p);
    }

    public static void gerarCamposClasse(Object object) {
        att.clear();
        map.clear();
        Class classe = object.getClass();
        Field fields[] = classe.getDeclaredFields();
        String nomeAtributo = "";
        //Object valorAtributo = null;
        Object tipoAtributo = null;
        //System.out.println(fields.length);
        for (Field field : fields) {
            try {
                field.setAccessible(true); //Necessário por conta do encapsulamento (private)
                nomeAtributo = field.getName();
                //System.out.println(nomeAtributo);
                att.add(nomeAtributo);
                //valorAtributo = field.get(object);
                tipoAtributo = field.getType(); //Obtendo o tipo do atributo
                String tipo = String.valueOf(tipoAtributo);
                tipo = tipo.substring(tipo.lastIndexOf(".") + 1);
                if (tipo.equals("int")) {
                    tipo = "Int";
                }
                //System.out.println(tipo);
                map.put(nomeAtributo, tipo);
            } catch (Exception ex) {
                Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String gerarNomeClasse(Object object) {
        String nomeAbs = object.getClass().getName();
        int inicio = nomeAbs.lastIndexOf(".") + 1;
        String nomeClasse = nomeAbs.substring(inicio).toLowerCase();
        return nomeClasse;
    }

}
