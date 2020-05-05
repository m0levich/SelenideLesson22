package com.github.m0levich.blocks;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class FinancialFreedom {

    public SelenideElement financialFreedomBlock = $(By.xpath("//span[@class='amount-holder']/child::span"));

    public SelenideElement myAssets = $(By.className("my-assets"));

    public String matcher = "[0-9]{0,3} [0-9]{0,3} [0-9]{1,3}[.]{1}[0-9]{1,2} [₽]{1}$";
}
