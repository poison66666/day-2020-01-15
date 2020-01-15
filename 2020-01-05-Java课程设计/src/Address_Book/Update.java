package Address_Book;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.*;
import java.util.Scanner;

import static Address_Book.Select.select_person;

public class Update {
    public static void update_person(MysqlConnectionPoolDataSource dataSource) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "update address_table set address=?,phone=?,zip=?,email=?,home_phone=? where person_name=?";
        //要发送给数据库的sql语句
        PreparedStatement ps = con.prepareStatement(sql);//预编译

        Scanner in = new Scanner(System.in);
        Address_table person = new Address_table();

        System.out.println("请输入姓名:");
        person.setPerson_name(in.nextLine());
        System.out.println("请输入地址:");
        person.setAddress(in.nextLine());
        System.out.println("请输入电话:");
        person.setPhone(in.nextLine());
        System.out.println("请输入邮编:");
        person.setZip(in.nextLine());
        System.out.println("请输入email:");
        person.setEmail(in.nextLine());
        System.out.println("请输入家庭电话:");
        person.setHome_phone(in.nextLine());


        ps.setString(1, person.getAddress());
        ps.setString(2, person.getPhone());
        ps.setString(3, person.getZip());
        ps.setString(4, person.getEmail());
        ps.setString(5, person.getHome_phone());
        ps.setString(6, person.getPerson_name());

        int result = ps.executeUpdate(); //存放insert语句的返回值，执行成功为1
        if (result > 0) {
            select_person(dataSource);
            System.out.println("修改成员信息成功\n");
        } else {
            System.out.println("修改成员信息失败\n");
        }
    }
}

