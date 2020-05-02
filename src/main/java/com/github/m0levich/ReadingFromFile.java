package com.github.m0levich;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingFromFile {

    public String getLogin() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("login"));
        String login = scanner.nextLine();
        scanner.close();
        return login;
    }

    public String getPassword() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("password"));
        String password = scanner.nextLine();
        scanner.close();
        return password;
    }

    public String getSmsPin() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("smsCode"));
        String smsPin = scanner.nextLine();
        scanner.close();
        return smsPin;
    }
}
