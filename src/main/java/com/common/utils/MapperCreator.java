package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.util.List;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yujingyang on 2017/4/17.
 */
public class MapperCreator {
    private static final char UNDERLINE='_';
    private static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    private static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    private static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));  
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }
    static Connection conn = null;

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/shop";
        String username = "root";
        String password = "123";
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static String createInsert(String tableName){
        Connection conn = getConn();
        String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name = \""+tableName+"\";";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> list = new ArrayList<>();

            while (rs.next()){
                list.add(rs.getString(1));
            }
            StringBuilder sql1 = new StringBuilder("insert into "+tableName+"(");
            for(String str : list){
                sql1.append(str+",");
            }
            sql1.deleteCharAt(sql1.length()-1);
            sql1.append(") values (");
            for(String str : list){
                sql1.append("#{"+underlineToCamel(str)+"},");
            }
            sql1.deleteCharAt(sql1.length()-1);
            sql1.append(")");
            rs.close();
            pstmt.close();
            conn.close();
            return sql1.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String createUpdate(String tableName){
        Connection conn = getConn();
        String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name = \""+tableName+"\""+" and TABLE_SCHEMA=\"shop\"";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> list = new ArrayList<>();

            while (rs.next()){
                list.add(rs.getString(1));
            }
            StringBuilder sql1 = new StringBuilder("update "+tableName+" set ");
            for(String str : list){
                sql1.append(str+"="+"#{"+underlineToCamel(str)+"},");
            }
            sql1.deleteCharAt(sql1.length()-1);
            sql1.append(" where id = #{id} ");
            rs.close();
            pstmt.close();
            conn.close();
            return sql1.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private static String convertClass(String cloumnType){
        switch (cloumnType){
            case "int":
                return "Integer";
            case "varchar":
                return "String";
            case "text" :
                return "String";
            case "decimal":
                return "Double";
            case "datetime":
                return "Date";
            case "date":
                return "Date";
            case "timestamp":
                return "Long";
        }
        return "error";
    }


    public static String createPO(String tableName){
        Connection conn = getConn();
        String sql = "select COLUMN_NAME,DATA_TYPE from information_schema.COLUMNS where table_name = \""+tableName+"\";";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> list = new ArrayList<>();
            Map<String,String> map = new HashMap<>();
            while (rs.next()){
                list.add(rs.getString(1));
                map.put(rs.getString(1),rs.getString(2));
            }
            StringBuilder sql1 = new StringBuilder();
            for(String str : list){
                sql1.append(convertClass(map.get(str))+" "+underlineToCamel(str)+";");
                sql1.append("\n");
            }
            rs.close();
            pstmt.close();
            conn.close();
            return sql1.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) {
        System.out.println(createUpdate(""));
    }
    
}
