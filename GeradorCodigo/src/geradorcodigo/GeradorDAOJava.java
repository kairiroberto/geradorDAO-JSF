/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kairi
 */
public class GeradorDAOJava {

    private List<String> atribudos = new ArrayList<>();
    private HashMap<String, Object> mapa = new HashMap<String, Object>();
    
    public String getDAOAtributos(HashMap<String, List<HashMap<String, String>>> tabela) {
        String tblName = "";
        for (String key : tabela.keySet()) {
            tblName = key;
            List<HashMap<String,  String>> lista = tabela.get(key);
            for (HashMap<String,  String> mapa : lista) {
                for (String s : mapa.keySet()) {
                    atribudos.add(mapa.get(s));
                    this.mapa.put(mapa.get(s), GeradorModelJava.converteVariaveis(s).replace("int", "Int"));
                }
            }
        }
        return getDAOString(tblName);
    }
    
    public String getDAOString(String tabela) {
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
        sb.append("\npackage geradorcodigo;");
        sb.append("\n\nimport java.sql.Date;\n\n");
        sb.append("\npublic class " + tabela.substring(0, 1).toUpperCase().concat(tabela.substring(1)) + "DAO {\n");
        
        sb.append(getInsert(tabela, campos, coringas));

        sb.append(getUpdate(tabela, upCampos, id));
        
        sb.append(getDelete(tabela, id));
        
        sb.append(getSelect(tabela));

        sb.append(getSelect(tabela, id));
        
        sb.append("\n}");
        
        return sb.toString();
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
