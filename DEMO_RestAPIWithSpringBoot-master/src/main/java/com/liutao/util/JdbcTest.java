package com.liutao.util;

import java.sql.*;

public class JdbcTest {

    public static void Test() {
        try (
                // Step 1: Allocate a database 'Connection' object -> The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
                Connection conn = DriverManager.getConnection("jdbc:mysql://10.40.0.190:3306/test_user_tag?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "changyin.han", "fjfhhan07");   // For MySQL only
                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
            String strSelect = "select id, studentnumber, tag_id from vd_crm_usertag";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row
                int id = rset.getInt("id");
                String studentnumber = rset.getString("studentnumber");
                String tag_id = rset.getString("tag_id");
                System.out.println(id + ", " + studentnumber + ", " + tag_id);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }

    public static void testJdbcTemplate() {
//        //启动IoC容器
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
//        //获取IoC容器中JdbcTemplate实例
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        String sql = "insert into users (ID,FirstName) values (?,?)";
//        int count = jdbcTemplate.update(sql, new Object[]{"caoyc", 3});
//        System.out.println(count);
    }

}
