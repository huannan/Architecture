package com.nan.day17_pattern_prototype.simple5;

public class Address implements Cloneable {
    public Address(String addressName, String city) {
        this.addressName = addressName;
        this.city = city;
    }

    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    public String addressName;
    public String city;
}
