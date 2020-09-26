package com.nan.day11_pattern_decorator.simple4;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("xxx");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
