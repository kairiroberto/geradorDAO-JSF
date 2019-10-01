/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kairi
 */
public class GeradorView {
    
    private List<String> atribudos = new ArrayList<>();
    public static List<String> att = new ArrayList<>();
    public static HashMap<String, Object> map = new HashMap<String, Object>();
    
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

    public GeradorView(Object object) {
        gerarCamposClasse(object);
        atribudos = att;
        String tabela = gerarNomeClasse(object);
        //gerarJSF(tabela);
        System.out.println(getJSF(tabela));
    }

    GeradorView() {
    }
    
    public void gerarJSF(String tabela) {
        //<h:outputLabel for="nome" value="NOME "/>
        //<h:inputText id="nome" value="#{clienteBean.obj.nome}"/>
        for (String s : atribudos) {
            System.out.println("<h:outputLabel for=\"" + s + " \" value=\"" + s.toUpperCase() + " \"/>");
            System.out.println("<h:inputText id=\"" + s + "\" value=\"#{clienteBean.obj." + s + "}\"/>");
            System.out.println();
        }
    }
    
    public String getJSF(String tabela) {
        //<h:outputLabel for="nome" value="NOME "/>
        //<h:inputText id="nome" value="#{clienteBean.obj.nome}"/>
        StringBuffer sb = new StringBuffer();
        for (String s : atribudos) {
            sb.append("\n<h:outputLabel for=\"" + s + " \" value=\"" + s.toUpperCase() + " \"/>");
            sb.append("\n<h:inputText id=\"" + s + "\" value=\"#{clienteBean.obj." + s + "}\"/>");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getJSF(HashMap<String, List<HashMap<String, String>>> tabela) {
        StringBuffer sb = new StringBuffer();
        String tblName = "";
        for (String key : tabela.keySet()) {
            tblName = key;
            List<HashMap<String,  String>> lista = tabela.get(key);
            for (HashMap<String,  String> mapa : lista) {
                for (String s : mapa.keySet()) {
                    atribudos.add(mapa.get(s));
                }
            }
        }
        sb.append(getJSF(tblName));
        return sb.toString();
    }
    
}
