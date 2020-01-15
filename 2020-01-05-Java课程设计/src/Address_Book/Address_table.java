package Address_Book;

public class Address_table {
    private int id;  //id号
    private String person_name; //姓名
    private String address;  //地址
    private String phone;   //电话
    private String zip;  //邮编
    private String email; //邮箱
    private String home_phone; //家庭电话

    public int getId() {  //获得id号
        return id;
    }

    public void setId(int id) {  //设置id号
        this.id = id;
    }

    public String getPerson_name() {  //获得姓名
        return person_name;
    }

    public void setPerson_name(String person_name) {  //设置姓名
        this.person_name = person_name;
    }

    public String getAddress() {  //获得地址
        return address;
    }

    public void setAddress(String address) {  //设置地址
        this.address = address;
    }

    public String getPhone() {  //获得电话
        return phone;
    }

    public void setPhone(String phone) {  //设置电话
        this.phone = phone;
    }

    public String getZip() {  //获得邮编
        return zip;
    }

    public void setZip(String zip) {  //设置邮编
        this.zip = zip;
    }

    public String getEmail() {  //获得邮箱
        return email;
    }

    public void setEmail(String email) {  //设置邮箱
        this.email = email;
    }

    public String getHome_phone() {  //获得家庭电话
        return home_phone;
    }

    public void setHome_phone(String home_phone) {  //设置家庭电话
        this.home_phone = home_phone;
    }
}
