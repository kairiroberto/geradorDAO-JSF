/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kairi
 */
public class GeradorView {
    
    private List<String> atribudos = new ArrayList<>();

    public GeradorView(Object object) {
        GeradorCodigo.gerarCamposClasse(object);
        atribudos = GeradorCodigo.att;
        String tabela = GeradorCodigo.gerarNomeClasse(object);
        //gerarJSF(tabela);
        System.out.println(getJSF(tabela));
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
    
}
