package com.example.srach.interpretator;

import java.util.Scanner;

public class InputNode implements MathNodeInt {
    @Override
    public int evaluate() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
