/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorcodigo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import sun.rmi.server.Activation;

/**
 *
 * @author kairi
 */
public class GeradorModel {

    String tableName;

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
    public List<String> getTabelas(String sgbd, String login, String senha, String bd, String host, String porta) {
        String url = "jdbc:" + sgbd + "://" + host + ":" + porta + "/" + bd;
        List<String> lista = new ArrayList<>();
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
            while (rs.next()) {
                String tblName = rs.getString("TABLE_NAME");
                lista.add(tblName);
            }
            con.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ie) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ie);
        }
        return null;
    }

    public HashMap<String, List<HashMap<String, String>>> getClasseModelo(String sgbd, String login, String senha, String bd, String host, String porta, String tblName) {
        String url = "jdbc:" + sgbd + "://" + host + ":" + porta + "/" + bd;
        HashMap<String, List<HashMap<String, String>>> tabela = new HashMap<>();
        try {
            System.out.println("Conectando ao banco.");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, login, senha);
            System.out.println("Conexão estabelecida.");
            DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
            ResultSet rs = null;

            ResultSet columns = meta.getColumns(null, null, tblName, null);
            List<HashMap<String, String>> atributos = new ArrayList<>();
            while (columns.next()) {
                String nome = columns.getString("COLUMN_NAME");
                String tipo = columns.getString("TYPE_NAME");
                //System.out.println(nome + " : " + tipo);
                HashMap<String, String> atributo = new HashMap<>();
                atributo.put(tipo, nome);
                atributos.add(atributo);
            }
            tabela.put(tblName, atributos);
            con.close();
            return tabela;
        } catch (SQLException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ie) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ie);
        }
        return null;
    }
    
    public String getArquivoClasse(HashMap<String, List<HashMap<String, String>>> tabela) {
        StringBuffer sb = new StringBuffer();
        sb.append("\npackage geradorcodigo;");
        sb.append("\n\nimport java.sql.Date;\n\n");
        for (String key1 : tabela.keySet()) {
            sb.append("\n");
            sb.append("public class " + key1.substring(0, 1).toUpperCase().concat(key1.substring(1)) + " {");

            sb.append("\n\tpublic " + key1.substring(0, 1).toUpperCase().concat(key1.substring(1)) + "() {}\n");

            List<HashMap<String, String>> lista = tabela.get(key1);
            for (int i = 0; i < lista.size(); i++) {
                for (String s : lista.get(i).keySet()) {
                    sb.append("\n\tprivate " + converteVariaveis(s) + " " + lista.get(i).get(s) + ";");
                }
            }
            for (int i = 0; i < lista.size(); i++) {
                for (String s : lista.get(i).keySet()) {
                    sb.append("\n");
                    sb.append("\n\tpublic " + converteVariaveis(s)
                            + " get" + lista.get(i).get(s).substring(0, 1).toUpperCase().concat(lista.get(i).get(s).substring(1)) + " () {");
                    sb.append("\n\t\treturn " + lista.get(i).get(s) + ";");
                    sb.append("\n\t}");

                    sb.append("\n");

                    sb.append("\n\tpublic void set"
                            + lista.get(i).get(s).substring(0, 1).toUpperCase().concat(lista.get(i).get(s).substring(1))
                            + " (" + converteVariaveis(s) + " " + lista.get(i).get(s) + ") {");
                    sb.append("\n\t\tthis." + lista.get(i).get(s) + " = " + lista.get(i).get(s) + ";");
                    sb.append("\n\t}");
                }
            }
            sb.append("\n}");
        }
        return sb.toString();
    }

    public String gerarClasseModelo(String sgbd, String login, String senha, String bd, String host, String porta) {
        String url = "jdbc:" + sgbd + "://" + host + ":" + porta + "/" + bd;
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
                this.tableName = tblName;
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
            String script = gerarArquivoClasse(tabelas);
            System.out.println(script);
            //gerarArquivo(script, this.tableName);
            con.close();
            return script;
        } catch (SQLException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ie) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ie);
        }
        return null;
    }

    public String gerarArquivoClasse(HashMap<String, List<HashMap<String, String>>> tabelas) {
        StringBuffer sb = new StringBuffer();
        sb.append("\npackage geradorcodigo;");
        sb.append("\n\nimport java.sql.Date;\n\n");
        for (String key1 : tabelas.keySet()) {
            sb.append("\n");
            sb.append("public class " + key1.substring(0, 1).toUpperCase().concat(key1.substring(1)) + " {");

            sb.append("\n\tpublic " + key1.substring(0, 1).toUpperCase().concat(key1.substring(1)) + "() {}\n");

            List<HashMap<String, String>> lista = tabelas.get(key1);
            for (int i = 0; i < lista.size(); i++) {
                for (String s : lista.get(i).keySet()) {
                    sb.append("\n\tprivate " + converteVariaveis(s) + " " + lista.get(i).get(s) + ";");
                }
            }
            for (int i = 0; i < lista.size(); i++) {
                for (String s : lista.get(i).keySet()) {
                    sb.append("\n");
                    sb.append("\n\tpublic " + converteVariaveis(s)
                            + " get" + lista.get(i).get(s).substring(0, 1).toUpperCase().concat(lista.get(i).get(s).substring(1)) + " () {");
                    sb.append("\n\t\treturn " + lista.get(i).get(s) + ";");
                    sb.append("\n\t}");

                    sb.append("\n");

                    sb.append("\n\tpublic void set"
                            + lista.get(i).get(s).substring(0, 1).toUpperCase().concat(lista.get(i).get(s).substring(1))
                            + " (" + converteVariaveis(s) + " " + lista.get(i).get(s) + ") {");
                    sb.append("\n\t\tthis." + lista.get(i).get(s) + " = " + lista.get(i).get(s) + ";");
                    sb.append("\n\t}");
                }
            }
            sb.append("\n}");
        }
        return sb.toString();
    }

    public void gerarArquivoClasse2(HashMap<String, List<HashMap<String, String>>> tabelas) {
        for (String key1 : tabelas.keySet()) {
            System.out.println();
            System.out.println("public class " + key1.substring(0, 1).toUpperCase().concat(key1.substring(1)) + " {");
            List<HashMap<String, String>> lista = tabelas.get(key1);
            for (int i = 0; i < lista.size(); i++) {
                for (String s : lista.get(i).keySet()) {
                    System.out.println("\tprivate " + converteVariaveis(s) + " " + lista.get(i).get(s) + ";");
                }
            }
            for (int i = 0; i < lista.size(); i++) {
                for (String s : lista.get(i).keySet()) {
                    System.out.println();
                    System.out.println("\tpublic " + converteVariaveis(s)
                            + " get" + lista.get(i).get(s).substring(0, 1).toUpperCase().concat(lista.get(i).get(s).substring(1)) + " () {");
                    System.out.println("\t\treturn " + lista.get(i).get(s) + ";");
                    System.out.println("\t}");

                    System.out.println();

                    System.out.println("\tpublic void set"
                            + lista.get(i).get(s).substring(0, 1).toUpperCase().concat(lista.get(i).get(s).substring(1))
                            + " (" + converteVariaveis(s) + " " + lista.get(i).get(s) + ") {");
                    System.out.println("\t\tthis." + lista.get(i).get(s) + " = " + lista.get(i).get(s) + ";");
                    System.out.println("\t}");
                }
            }
            System.out.println("}");
        }
    }

    public static String converteVariaveis(String s) {
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

    private void gerarArquivo(String script, String tableName) {
        File file = null;
        FileWriter fileWriter = null;
        try {
            String curDir = System.getProperty("user.dir") + "\\src\\geradorcodigo\\" + tableName.substring(0, 1).toUpperCase().concat(tableName.substring(1)) + ".java";
            System.out.println(curDir);
            file = new File(curDir);
            if (file.createNewFile()) {
                fileWriter = new FileWriter(file);
                PrintWriter writer = new PrintWriter(fileWriter);
                writer.print(script);
            }
        } catch (IOException ex) {
            Logger.getLogger(GeradorModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(GeradorModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
