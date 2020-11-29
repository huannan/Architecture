package com.nan.day17_pattern_prototype.simple4;

/**
 * 测试客户端
 */
public class Client {
    public static void main(String[] args) {
        User user = new User();
        user.age = 18;
        user.userName = "Darren";
        user.userAddress = new User.Address("湖南长沙", "长沙");

        // 浅拷贝
        try {
            // 拷贝对象
            User copyUser = user.clone();

            // System.out.print("姓名："+user.userName+" 地址："+user.userAddress.addressName+"\n");
            // System.out.print("姓名："+copyUser.userName+" 地址："+copyUser.userAddress.addressName);
            // 把拷贝的地址做修改
            copyUser.userAddress.addressName = "深圳珠海";
            copyUser.userName = "GG";
            // 现象是 姓名是各自的 copy: GG 原来的是 Darren
            // 把copy的地址改了一下 ，就都变成了 深圳珠海，按道理应该是 copy:深圳珠海 原来的 湖南长沙
            // 浅拷贝，就是类的类对象实例，是没有被拷贝的，他们还是共用一份
            System.out.print("姓名：" + user.userName + " 地址：" + user.userAddress.addressName + "\n");
            System.out.print("姓名：" + copyUser.userName + " 地址：" + copyUser.userAddress.addressName);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
