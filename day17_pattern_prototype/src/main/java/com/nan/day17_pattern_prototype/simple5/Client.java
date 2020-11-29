package com.nan.day17_pattern_prototype.simple5;

/**
 * 测试客户端
 */
public class Client {
    public static void main(String[] args) {
        User user = new User();
        user.age = 18;
        user.userName = "Darren";
        user.userAddress = new Address("湖南长沙", "长沙");

        // 深拷贝
        try {
            // 拷贝对象
            User copyUser = user.clone();

            // System.out.print("姓名："+user.userName+" 地址："+user.userAddress.addressName+"\n");
            // System.out.print("姓名："+copyUser.userName+" 地址："+copyUser.userAddress.addressName);
            // 把拷贝的地址做修改
            copyUser.userAddress.addressName = "深圳珠海";
            copyUser.userName = "GG";
            System.out.print("姓名：" + user.userName + " 地址：" + user.userAddress.addressName + "\n");
            System.out.print("姓名：" + copyUser.userName + " 地址：" + copyUser.userAddress.addressName);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
