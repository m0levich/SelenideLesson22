package com.github.m0levich.blocks;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class FinancialFreedom {

    private SelenideElement financialFreedomBlock = $(By.xpath("//span[@class='amount-holder']/child::span"));

    private SelenideElement myAssets = $(By.className("my-assets"));

    private String matcher = "[0-9]{0,3} [0-9]{0,3} [0-9]{1,3}[.]{1}[0-9]{1,2} [₽]{1}$";

    public String getMatcher() {
        return matcher;
    }

    public SelenideElement getFinancialFreedomBlock() {
        return financialFreedomBlock;
    }

    public SelenideElement getMyAssets() {
        return myAssets;
    }
}
