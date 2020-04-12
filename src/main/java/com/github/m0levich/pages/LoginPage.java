package com.github.m0levich.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class LoginPage {

    private SelenideElement userNameField = $(By.xpath("//input[@name='username']"));

    private SelenideElement userPasswordField = $(By.xpath("//input[@name='password']"));

    private SelenideElement enterButton = $(By.id("login-button"));

    private SelenideElement returnLanguage = $(By.xpath("//a[@class='chevron locale inline-block']"));

    @Step("Авторизация")
    @Description("Ввод логина и пароля")
    public TwoFactorAuthPage login(String userName, String userPassword) {
        userNameField.clear();
        userNameField.sendKeys(userName);
        userPasswordField.clear();
        userPasswordField.sendKeys(userPassword);
        enterButton.click();
        return new TwoFactorAuthPage();
    }

    @Step("Выбор русского языка")
    @Description("Проверка на выбранный язык на странице")
    public void checkLanguage(){
        if(title().contains("Bank Saint-Petersburg")){
            returnLanguage.click();
        }
    }
}
