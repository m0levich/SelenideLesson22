package com.github.m0levich.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement userNameField = $(By.xpath("//input[@name='username']"));

    private SelenideElement userPasswordField = $(By.xpath("//input[@name='password']"));

    private SelenideElement enterButton = $(By.id("login-button"));

    public TwoFactorAuthPage login(String userName, String userPassword) {
        userNameField.clear();
        userNameField.sendKeys(userName);
        userPasswordField.clear();
        userPasswordField.sendKeys(userPassword);
        enterButton.click();
        return new TwoFactorAuthPage();
    }
}
