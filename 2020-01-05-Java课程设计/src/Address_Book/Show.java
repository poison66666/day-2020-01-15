package Address_Book;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.*;

public class Show {
    public static void show_person(MysqlConnectionPoolDataSource dataSource) throws SQLException {
        Connection con=dataSource.getConnection();
        String sql="select * from address_table";
        PreparedStatement ps=con.prepareStatement(sql);//预编译
        ResultSet result=ps.executeQuery();
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
