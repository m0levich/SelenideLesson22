package com.github.m0levich.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TwoFactorAuthPage {

    private SelenideElement twoFactorsCodeFill = $(By.id("otp-code"));

    private SelenideElement enterButton = $(By.id("login-otp-button"));


    public MainPage twoFactorsAuth(String code){
        twoFactorsCodeFill.clear();
        twoFactorsCodeFill.sendKeys(code);
        enterButton.click();
        return new MainPage();
    }
}
