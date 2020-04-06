package com.github.m0levich.blocks;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NavigationMenu {

    public void selectMenu(String menuName) {
        String selector = String.format("//a[.='%s']", menuName);
        $(By.xpath(selector)).click();
    }
}
