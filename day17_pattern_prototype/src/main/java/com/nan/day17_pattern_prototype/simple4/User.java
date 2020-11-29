package com.nan.day17_pattern_prototype.simple4;

/**
 * 用户的对象
 */
public class User implements Cloneable {
    public String userName;
    public int age;
    public Address userAddress;

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public static class Address {
        public Address(String addressName, String city) {
            this.addressName = addressName;
            this.city = city;
        }

        public String addressName;
        public String city;
    }
}
