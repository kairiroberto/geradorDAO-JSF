/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kairi
 */
public class GeradorModel {
    
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
    
    public static void listTables() {
        String login = "root";
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
    
}
