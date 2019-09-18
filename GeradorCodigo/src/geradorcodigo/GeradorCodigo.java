/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        listTables();
        //Marca marca = new Marca();
        //gerarCodigo(marca);
        /*
            Pessoa[] pessoa = new Pessoa[1];
            pessoa[0] = new Pessoa();
            
            for (Pessoa p : pessoa) {
            gerarCodigo(p);
            }
         */
 /*
            try {
            // TODO code application logic here
            Pessoa p = new Pessoa();
            p.setId(1);
            p.setNome("JOÁO");
            p.setEndereco(1);
            p.setTelefone(1);
            PessoaDao dao = new PessoaDao();
            dao.update(p);
            p.setNome("JROBERTO");
            dao.insert(p);
            for (Pessoa p1 : dao.select()) {
            System.out.println(p1.getNome());
            }
            System.out.println(dao.selectOne(1));
            } catch (SQLException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
         */

    }

    private static List<String> att = new ArrayList<>();
    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static void gerarCodigo(Object object) {
        Class classe = object.getClass();
        Field fields[] = classe.getDeclaredFields();
        String nomeAtributo = "";
        //Object valorAtributo = null;
        Object tipoAtributo = null;
        for (Field field : fields) {
            try {
                field.setAccessible(true); //Necessário por conta do encapsulamento (private)
                nomeAtributo = field.getName();
                att.add(nomeAtributo);
                //valorAtributo = field.get(object);
                tipoAtributo = field.getType(); //Obtendo o tipo do atributo
                String tipo = String.valueOf(tipoAtributo);
                tipo = tipo.substring(tipo.lastIndexOf(".") + 1);
                if (tipo.equals("int")) {
                    tipo = "Int";
                }
                map.put(nomeAtributo, tipo);
            } catch (Exception ex) {
                Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String nomeAbs = object.getClass().getName();
        int inicio = nomeAbs.lastIndexOf(".") + 1;
        String nomeClasse = nomeAbs.substring(inicio).toLowerCase();
        gerarDAO(nomeClasse);
        System.out.println();
        System.out.println();
        System.out.println();
        gerarJSF(nomeClasse);
    }

    public static void gerarDAO(String tabela) {
        String campos = "";
        String coringas = "";
        String upCampos = "";
        String id = "";
        for (int i = 0; i < att.size(); i++) {
            if (i == att.size() - 1) {
                campos = campos + att.get(i);
                coringas = coringas + "?";
            } else {
                campos = campos + att.get(i) + ", ";
                coringas = coringas + "?" + ", ";
            }
            if (i > 0) {
                if (i == att.size() - 1) {
                    upCampos = upCampos + att.get(i) + " = ?";
                } else {
                    upCampos = upCampos + att.get(i) + " = ?, ";
                }
            } else {
                id = att.get(i) + " = ?";
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
        for (String s : att) {
            System.out.println("\tpstmt.set" + map.get(s) + "(" + i + ", " + tabela + ".get" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "());");
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
        for (int u = 1; u < att.size(); u++) {
            System.out.println("\tpstmt.set" + map.get(att.get(u)) + "(" + j + ", " + tabela + ".get" + att.get(u).substring(0, 1).toUpperCase().concat(att.get(u).substring(1)) + "());");
            j++;
        }
        System.out.println("\tpstmt.setInt(" + j + ", " + tabela + ".get" + att.get(0).substring(0, 1).toUpperCase().concat(att.get(0).substring(1)) + "());");
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
        for (String s : att) {
            System.out.println("\t\t" + tabela + ".set" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "(rs.get" + map.get(s) + "(\"" + s + "\"));");
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
        for (String s : att) {
            System.out.println("\t\t" + tabela + ".set" + s.substring(0, 1).toUpperCase().concat(s.substring(1)) + "(rs.get" + map.get(s) + "(\"" + s + "\"));");
        }
        System.out.println("\t\tlista.add(" + tabela + ");");
        System.out.println("\t}");
        System.out.println("\treturn lista.get(0);");
        System.out.println("}");

        System.out.println();
    }

    public static void gerarJSF(String tabela) {
        //<h:outputLabel for="nome" value="NOME "/>
        //<h:inputText id="nome" value="#{clienteBean.obj.nome}"/>
        for (String s : att) {
            System.out.println("<h:outputLabel for=\"" + s + " \" value=\"" + s.toUpperCase() + " \"/>");
            System.out.println("<h:inputText id=\"" + s + "\" value=\"#{clienteBean.obj." + s + "}\"/>");
            System.out.println();
        }
    }
    
    public static void listTables() {
        String login = "root";
        //String login = "kairiroberto1";
        String senha = "";
        String url = "jdbc:mysql://localhost:3306/bd_farmacia";
        try {
            System.out.println("Conectando ao banco.");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, login, senha);
            System.out.println("Conexão estabelecida.");
            DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
            ResultSet rs = null;
            
            rs = meta.getTables(null, null, null, new String[]{
                "TABLE"
            });
            
            int count = 0;
            
            System.out.println("All table names are in test database:");
            HashMap<String, List<HashMap<String, String>>> tabelas = new HashMap<>();
            while (rs.next()) {
                String tblName = rs.getString("TABLE_NAME");
                //System.out.println("<<<<< " + tblName + " >>>>>");
                ResultSet columns = meta.getColumns(null, null, tblName, null);
                List<HashMap<String, String>> tabela = new ArrayList<>();
                while (columns.next()) {
                    String nome = columns.getString("COLUMN_NAME");
                    String tipo = columns.getString("TYPE_NAME");
                    //System.out.println(nome + " : " + tipo);
                    HashMap<String, String> atributos = new HashMap<>();
                    atributos.put(tipo, nome);
                    tabela.add(atributos);
                }
                tabelas.put(tblName, tabela);
                System.out.println();
                count++;
            }
            System.out.println(count + " Rows in set ");
            gerarModelClasse(tabelas);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void gerarModelClasse(HashMap<String, List<HashMap<String, String>>> tabelas) {
        for (String key1 : tabelas.keySet()) {
                System.out.println();
                System.out.println("public class " + key1.substring(0,1).toUpperCase().concat(key1.substring(1)) + " {");
                List<HashMap<String, String>> lista = tabelas.get(key1);
                for (int i = 0; i < lista.size(); i++) {
                    for (String s : lista.get(i).keySet()) {
                        System.out.println("\tprivate " + converteVariaveis(s) + " " + lista.get(i).get(s) + ";");
                    }
                }
                for (int i = 0; i < lista.size(); i++) {
                    for (String s : lista.get(i).keySet()) {
                        System.out.println();
                        System.out.println("\tpublic " + converteVariaveis(s) + 
                                " get" + lista.get(i).get(s).substring(0,1).toUpperCase().concat(lista.get(i).get(s).substring(1)) + " () {");
                        System.out.println("\t\treturn " + lista.get(i).get(s) + ";");
                        System.out.println("\t}");
                    }
                }
                System.out.println("}");
            }
    }
    
    private static String converteVariaveis(String s) {
        if (s.equals("INT")) {
            return "int";
        } else if (s.equals("VARCHAR") || s.equals("TEXT")) {
            return "String";
        } else if (s.equals("DOUBLE")) {
            return "Double";
        } else if (s.equals("DATE")) {
            return "Date";
        }
        return s;
    }
    
    /*
        ResultSet primaryKeys = meta.getPrimaryKeys(null, null, tblName);
        while (primaryKeys.next()) {
            //System.out.println("PK: " + primaryKeys.getString("COLUMN_NAME"));
        }
        primaryKeys.close();
        ResultSet indexInfo = meta.getIndexInfo(null, null, tblName, false, false);
        while (indexInfo.next()) {
            //System.out.println("INDEX: " + indexInfo.getString("COLUMN_NAME"));
        }
        indexInfo.close();
        ResultSet foreignKeys = meta.getImportedKeys(null, null, tblName);
        while (foreignKeys.next()) {
            //System.out.println("FK: " + foreignKeys.getString("PKTABLE_NAME") + "-" + foreignKeys.getString("PKCOLUMN_NAME") + "\nFK: " + foreignKeys.getString("FKTABLE_NAME") + "-" + foreignKeys.getString("FKCOLUMN_NAME"));
        }
        foreignKeys.close();
    */

}
