package com.github.m0levich.cucumber.stepdefinitions;

import com.codeborne.selenide.Selenide;
import com.github.m0levich.ReadingFromFile;
import com.github.m0levich.blocks.FinancialFreedom;
import com.github.m0levich.pages.*;
import io.cucumber.java.ru.Допустим;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BankSpbSteps {
    ReadingFromFile readingFromFile = new ReadingFromFile();
    private LoginPage loginPage = new LoginPage();
    private FinancialFreedom financialFreedom = new FinancialFreedom();

    @Допустим("пользователь переходит на сайт интернет банка")
    public void goToBankSpb() {
        Selenide.open("https://idemo.bspb.ru");
    }

    @Допустим("меняет язык на русский")
    public void languageCheck() {
        loginPage.checkLanguage();
        Assert.assertTrue(title().contains("Интернет банк - Банк Санкт-Петербург"), "Title is not contains expected text");
    }

    @Допустим("авторизуется")
    public void authorization() {
        try {
            loginPage.login(readingFromFile.getLogin(), readingFromFile.getPassword());
        } catch (FileNotFoundException e) {
            Assert.fail("Файл не найден в корне проекта", e);
        }
    }

    @Допустим("вводит код и нажимает Войти")
    public void smsCheck() {
        TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage();
        try {
            twoFactorAuthPage.twoFactorsAuth(readingFromFile.getSmsPin());
        } catch (FileNotFoundException e) {
            Assert.fail("Файл не найден в корне проекта", e);
        }
    }

    @Допустим("переходит в раздел {string}")
    public void goToMenu(String string) {
        BasePage basePage = new MainPage();
        basePage.navigationMenu.selectMenu(string);
    }

    @Допустим("проверяет блок Финансовая свобода на матчер")
    public void checkBlockFinancialFreedomOnMatcher() {
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.financialFreedom.financialFreedomBlock.should(matchText(overviewPage.financialFreedom.matcher));
    }

    @Допустим("Наводит курсор на блок")
    public void moveCursorToBlock() {
        Actions act = new Actions(getWebDriver());
        act.moveToElement(financialFreedom.financialFreedomBlock).perform();
        financialFreedom.myAssets.shouldBe(visible);
    }

    @Допустим("проверяет на матчер появившийся блок Мои средства")
    public void checkBlockMyAssetsOnMatcher() {
        String myAssetsMatcher = "Моих средств " + financialFreedom.matcher;
        financialFreedom.myAssets.should(matchText(myAssetsMatcher));
    }

}
