package Address_Book;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.SQLException;
import java.util.Scanner;

import static Address_Book.Add.add_person;
import static Address_Book.Select.select_person;
import static Address_Book.Show.show_person;
import static Address_Book.Sort.sort_person;
import static Address_Book.Update.update_person;

public class Main {
    public static MysqlConnectionPoolDataSource dataSource;
    static {
        dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setServerName("127.0.0.1");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("yiqieanhao520");
        dataSource.setDatabaseName("address_book");
        dataSource.setUseSSL(false);
        dataSource.setCharacterEncoding("UTF8");
    }

    private static void menu(){
        System.out.println("|通讯录|");
        System.out.println("|1.添加||2.显示||3.查找||4.修改||5.排序|");
        System.out.println("请选择：");
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    //1.注册Driver
        Class.forName("com.mysql.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        while (true){
            menu(); //1.打印操作界面
            int selected = scanner.nextInt();//2.用户输入选择
            scanner.nextLine();
            switch (selected){ //3.根据用户的需求，执行部同的动作
                case 1:
                    add_person(dataSource);
                    break;
                case 2:
                    show_person(dataSource);
                    break;
                case 3:
                    select_person(dataSource);
                    break;
                case 4:
                    update_person(dataSource);
                    break;
                case 5:
                    sort_person(dataSource);
                    break;
                default:
                    System.out.println("指令错误");
            }
        }
    }
}
