package com.github.m0levich.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class LoginPage {

    private SelenideElement userNameField = $(By.xpath("//input[@name='username']"));

    private SelenideElement userPasswordField = $(By.xpath("//input[@name='password']"));

    private SelenideElement enterButton = $(By.id("login-button"));

    private SelenideElement returnLanguage = $(By.xpath("//a[@class='chevron locale inline-block']"));

    public TwoFactorAuthPage login(String userName, String userPassword) {
        userNameField.clear();
        userNameField.sendKeys(userName);
        userPasswordField.clear();
        userPasswordField.sendKeys(userPassword);
        enterButton.click();
        return new TwoFactorAuthPage();
    }

    public void checkLanguage(){
        if(title().contains("Bank Saint-Petersburg")){
            returnLanguage.click();
        }
    }
}
