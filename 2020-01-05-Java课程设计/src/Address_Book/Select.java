package Address_Book;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.*;
import java.util.Scanner;

public class Select {
    public static void select_person(MysqlConnectionPoolDataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql="select * from address_table where person_name=?";;//要发送给数据库的sql语句
        PreparedStatement ps=connection.prepareStatement(sql);//预编译
        Scanner in=new Scanner(System.in);
        Address_table person = new Address_table();
        System.out.println("请输入要查询的姓名:");
        person.setPerson_name(in.nextLine());
        ps.setString(1,person.getPerson_name());
        ResultSet result=ps.executeQuery();
        if (result.next()==false){
            System.out.println("查无此人");
        }else result.previous();
        System.out.println("id"+"\t"+"姓名"+"\t"+"地址"+"\t"+"\t"+"\t"+"电话"+"\t"+"\t"+"\t"+"邮编"+"\t"+"\t"+"邮箱"+"\t"+"\t"+"\t"+"\t"+"\t"+"家庭电话");
        while(result.next())
        {
            System.out.println(result.getInt("id") + "\t"
                    + result.getString("person_name") + "\t"
                    + result.getString("address") + "\t"
                    + result.getString("phone") + "\t"+"\t"
                    + result.getString("zip") + "\t"+"\t"
                    + result.getString("email") + "\t"+"\t"
                    + result.getString("home_phone"));
        }
    }
}
