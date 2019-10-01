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
public class GeradorDAO {

    private List<String> atribudos = new ArrayList<>();
    private HashMap<String, Object> mapa = new HashMap<String, Object>();
    public static List<String> att = new ArrayList<>();
    public static HashMap<String, Object> map = new HashMap<String, Object>();
    

    
    public GeradorDAO() {
        
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
    
    public GeradorDAO(Object object) {
        gerarCamposClasse(object);
        atribudos = att;
        mapa = map;
        String tabela = gerarNomeClasse(object);
        //gerarDAO(tabela);
        getDAO(tabela);
    }
    
    public void gerarDAO(String tabela) {
        String campos = "";
        String coringas = "";
        String upCampos = "";
        String id = "";
        for (int i = 0; i < atribudos.size(); i++) {
            if (i == atribudos.size() - 1) {
                campos = campos + atribudos.get(i);
                coringas = coringas + "?";
            } else {
                campos = campos + atribudos.get(i) + ", ";
                coringas = coringas + "?" + ", ";
            }
            if (i > 0) {
                if (i == atribudos.size() - 1) {
                    upCampos = upCampos + atribudos.get(i) + " = ?";
                } else {
                    upCampos = upCampos + atribudos.get(i) + " = ?, ";
                }
            } else {
                id = atribudos.get(i) + " = ?";
            }
        }
        //System.err.println(campos);
        String sqlInsert = "INSERT INTO %s(%s) VALUES (%s);";
        String sqlUpdate = "UPDATE %s SET %s WHERE %s;";
        String sqlDelete = "DELETE FROM %s WHERE %s;";
        String sqlSELECT = "SELECT * FROM %s;";
        String sqlSELECTONE = "SELECT * FROM %s WHERE %s;";

        System.out.println("public void insert(" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + ") throws SQLException {");
        System.out.println("\tConnection conexao = ConnectionFactory.getConexao();");
        sqlInsert = "\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlInsert + "\");";
        System.out.printf(sqlInsert, tabela, campos, coringas);
        System.out.println();
        int i = 1;
        for (String s : atribudos) {
            System.out.println("\tpstmt.set" + mapa.get(s) + "(" + i + ", " + tabela + ".get" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "());");
            i++;
        }
        System.out.println("\tint resultado = pstmt.executeUpdate();");
        System.out.println("\tpstmt.close();");
        System.out.println("}");

        System.out.println();

        System.out.println("public void update(" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + ") throws SQLException {");
        System.out.println("\tConnection conexao = ConnectionFactory.getConexao();");
        sqlUpdate = "\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlUpdate + "\");";
        System.out.printf(sqlUpdate, tabela, upCampos, id);
        System.out.println();
        int j = 1;
        for (int u = 1; u < atribudos.size(); u++) {
            System.out.println("\tpstmt.set" + mapa.get(atribudos.get(u)) + "(" + j + ", " + tabela + ".get" + atribudos.get(u).substring(0, 1).toUpperCase().concat(atribudos.get(u).substring(1)) + "());");
            j++;
        }
        System.out.println("\tpstmt.setInt(" + j + ", " + tabela + ".get" + atribudos.get(0).substring(0, 1).toUpperCase().concat(atribudos.get(0).substring(1)) + "());");
        System.out.println("\tint resultado = pstmt.executeUpdate();");
        System.out.println("\tpstmt.close();");
        System.out.println("}");

        System.out.println();

        System.out.println("public void delete(int id) throws SQLException {");
        System.out.println("\tConnection conexao = ConnectionFactory.getConexao();");
        sqlDelete = "\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlDelete + "\");";
        System.out.printf(sqlDelete, tabela, id);
        System.out.println();
        System.out.println("\tpstmt.setInt(1, id);");
        System.out.println("\tint resultado = pstmt.executeUpdate();");
        System.out.println("\tpstmt.close();");
        System.out.println("}");

        System.out.println();

        System.out.println("public List<" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "> select() throws SQLException {");
        System.out.println("\tConnection conexao = ConnectionFactory.getConexao();");
        System.out.println("\tList<" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "> lista = new ArrayList();");
        sqlSELECT = "\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlSELECT + "\");";
        System.out.printf(sqlSELECT, tabela);
        System.out.println();
        System.out.println("\tResultSet rs = pstmt.executeQuery();");
        System.out.println("\twhile (rs.next()) {");
        System.out.println("\t\t" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + " " + " = new " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "();");
        //cliente.setNome(rs.getString("nome"));
        for (String s : atribudos) {
            System.out.println("\t\t" + tabela + ".set" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "(rs.get" + mapa.get(s) + "(\"" + s + "\"));");
        }
        System.out.println("\t\tlista.add(" + tabela + ");");
        System.out.println("\t}");
        System.out.println("\treturn lista;");
        System.out.println("}");

        System.out.println();

        System.out.println("public " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " selectOne(int id) throws SQLException {");
        System.out.println("\tConnection conexao = ConnectionFactory.getConexao();");
        System.out.println("\tList<" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "> lista = new ArrayList();");
        sqlSELECTONE = "\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlSELECTONE + "\");";
        System.out.printf(sqlSELECTONE, tabela, id);
        System.out.println();
        System.out.println("\tpstmt.setInt(1, id);");
        System.out.println("\tResultSet rs = pstmt.executeQuery();");
        System.out.println("\twhile (rs.next()) {");
        System.out.println("\t\t" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + " " + " = new " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "();");
        //cliente.setNome(rs.getString("nome"));
        for (String s : atribudos) {
            System.out.println("\t\t" + tabela + ".set" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "(rs.get" + mapa.get(s) + "(\"" + s + "\"));");
        }
        System.out.println("\t\tlista.add(" + tabela + ");");
        System.out.println("\t}");
        System.out.println("\treturn lista.get(0);");
        System.out.println("}");

        System.out.println();
    }
    
    public void getDAO(String tabela) {
        String campos = "";
        String coringas = "";
        String upCampos = "";
        String id = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < atribudos.size(); i++) {
            if (i == atribudos.size() - 1) {
                campos = campos + atribudos.get(i);
                coringas = coringas + "?";
            } else {
                campos = campos + atribudos.get(i) + ", ";
                coringas = coringas + "?" + ", ";
            }
            if (i > 0) {
                if (i == atribudos.size() - 1) {
                    upCampos = upCampos + atribudos.get(i) + " = ?";
                } else {
                    upCampos = upCampos + atribudos.get(i) + " = ?, ";
                }
            } else {
                id = atribudos.get(i) + " = ?";
            }
        }
        //System.err.println(campos);
        
        getInsert(tabela, campos, coringas);

        getUpdate(tabela, upCampos, id);
        
        getDelete(tabela, id);
        
        getSelect(tabela);

        getSelect(tabela, id);
        
    }
    
    private String getInsert(String tabela, String campos, String coringas) {
        StringBuffer sb = new StringBuffer();
        String sqlInsert = "INSERT INTO %t(%c) VALUES (%v);";
        sb.append("public void insert(" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + ") throws SQLException {");
        sb.append("\n\tConnection conexao = ConnectionFactory.getConexao();");
        sqlInsert = "\n\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlInsert + "\");";
        sb.append(sqlInsert.replace("%t", tabela).replace("%c", campos).replace("%v", coringas));
        int i = 1;
        for (String s : atribudos) {
            sb.append("\n\tpstmt.set" + mapa.get(s) + "(" + i + ", " + tabela + ".get" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "());");
            i++;
        }
        sb.append("\n\tint resultado = pstmt.executeUpdate();");
        sb.append("\n\tpstmt.close();");
        sb.append("\n}");
        
        sb.append("\n");
        
        return sb.toString();
    } 
    
    private String getUpdate(String tabela, String upCampos, String id) {
        StringBuffer sb = new StringBuffer();
        String sqlUpdate = "UPDATE %t SET %uc WHERE %id;";
        sb.append("public void update(" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + ") throws SQLException {");
        sb.append("\n\tConnection conexao = ConnectionFactory.getConexao();");
        sqlUpdate = "\n\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlUpdate + "\");";
        //System.out.printf(sqlUpdate, tabela, upCampos, id);
        sb.append(sqlUpdate.replace("%t", tabela).replace("%uc", upCampos).replace("%id", id));
        int j = 1;
        for (int u = 1; u < atribudos.size(); u++) {
            sb.append("\n\tpstmt.set" + mapa.get(atribudos.get(u)) + "(" + j + ", " + tabela + ".get" + atribudos.get(u).substring(0, 1).toUpperCase().concat(atribudos.get(u).substring(1)) + "());");
            j++;
        }
        sb.append("\n\tpstmt.setInt(" + j + ", " + tabela + ".get" + atribudos.get(0).substring(0, 1).toUpperCase().concat(atribudos.get(0).substring(1)) + "());");
        sb.append("\n\tint resultado = pstmt.executeUpdate();");
        sb.append("\n\tpstmt.close();");
        sb.append("\n}");

        sb.append("\n");
        return sb.toString();
    }
    
    private String getDelete(String tabela, String id) {
        StringBuffer sb = new StringBuffer();
        String sqlDelete = "DELETE FROM %t WHERE %id;";
        sb.append("public void delete(int id) throws SQLException {");
        sb.append("\n\tConnection conexao = ConnectionFactory.getConexao();");
        sqlDelete = "\n\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlDelete + "\");";
        //System.out.printf(sqlDelete, tabela, id);
        sb.append(sqlDelete.replace("%t", tabela).replace("%id", id));
        sb.append("\n\tpstmt.setInt(1, id);");
        sb.append("\n\tint resultado = pstmt.executeUpdate();");
        sb.append("\n\tpstmt.close();");
        sb.append("\n}");

        sb.append("\n");
        return sb.toString();
    }
    
    public String getSelect(String tabela) {
        StringBuffer sb = new StringBuffer();
        String sqlSELECT = "SELECT * FROM %t;";
        sb.append("public List<" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "> select() throws SQLException {");
        sb.append("\n\tConnection conexao = ConnectionFactory.getConexao();");
        sb.append("\n\tList<" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "> lista = new ArrayList();");
        sqlSELECT = "\n\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlSELECT + "\");";
        //System.out.printf(sqlSELECT, tabela);
        sb.append(sqlSELECT.replace("%t", tabela));
        sb.append("\n\tResultSet rs = pstmt.executeQuery();");
        sb.append("\n\twhile (rs.next()) {");
        sb.append("\n\t\t" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + " " + " = new " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "();");
        //cliente.setNome(rs.getString("nome"));
        for (String s : atribudos) {
            sb.append("\n\t\t" + tabela + ".set" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "(rs.get" + mapa.get(s) + "(\"" + s + "\"));");
        }
        sb.append("\n\t\tlista.add(" + tabela + ");");
        sb.append("\n\t}");
        sb.append("\n\treturn lista;");
        sb.append("\n}");

        sb.append("\n");
        return sb.toString();
    }
    
    public String getSelect(String tabela, String id) {
        StringBuffer sb = new StringBuffer();
        String sqlSELECTONE = "SELECT * FROM %t WHERE %id;";
        sb.append("public " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " selectOne(int id) throws SQLException {");
        sb.append("\n\tConnection conexao = ConnectionFactory.getConexao();");
        sb.append("\n\tList<" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "> lista = new ArrayList();");
        sqlSELECTONE = "\n\tPreparedStatement pstmt = conexao.prepareStatement(\"" + sqlSELECTONE + "\");";
        //System.out.printf(sqlSELECTONE, tabela, id);
        sb.append(sqlSELECTONE.replace("%t", tabela).replace("%id", id));
        sb.append("\n\tpstmt.setInt(1, id);");
        sb.append("\n\tResultSet rs = pstmt.executeQuery();");
        sb.append("\n\twhile (rs.next()) {");
        sb.append("\n\t\t" + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + " " + tabela + " " + " = new " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "();");
        //cliente.setNome(rs.getString("nome"));
        for (String s : atribudos) {
            sb.append("\n\t\t" + tabela + ".set" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "(rs.get" + mapa.get(s) + "(\"" + s + "\"));");
        }
        sb.append("\n\t\tlista.add(" + tabela + ");");
        sb.append("\n\t}");
        sb.append("\n\treturn lista.get(0);");
        sb.append("\n}");

        sb.append("\n");
        return sb.toString();
    }
    
}
