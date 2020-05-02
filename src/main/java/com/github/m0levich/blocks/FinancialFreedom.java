package com.github.m0levich.blocks;

import com.codeborne.selenide.SelenideElement;
import com.github.m0levich.pages.OverviewPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
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

    @Step("Перемещение курсора на блок")
    public static void moveToBlock(OverviewPage overviewPage, Actions act) {
        act.moveToElement(overviewPage.getFinancialFreedom().getFinancialFreedomBlock()).perform();
    }


    @Step("Проверка на матчер Мои средства")
    public void checkMyAssetsOnMather(OverviewPage overviewPage) {
        String myAssetsMatcher = "Моих средств " + overviewPage.getFinancialFreedom().getMatcher();
        overviewPage.getFinancialFreedom().getMyAssets().should(matchText(myAssetsMatcher));
    }

    @Step("Проверка на видимость элемента")
    public void checkMyAssetsVisible(OverviewPage overviewPage) {
        overviewPage.getFinancialFreedom().getMyAssets().shouldBe(visible);
    }

    @Step("Проверка на матчер суммы в блоке Финансовая свобода")
    public void checkFinancialFreedomOnMatcher(SelenideElement financialFreedomBlock, String matcher) {
        financialFreedomBlock.should(matchText(matcher));
    }
}
