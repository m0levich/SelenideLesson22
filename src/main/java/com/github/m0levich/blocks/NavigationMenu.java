package com.github.m0levich.blocks;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NavigationMenu {

    @Step("Переход по меню")
    public void selectMenu(String menuName) {
        String selector = String.format("//a[.='%s']", menuName);
        $(By.xpath(selector)).click();
    }
}
