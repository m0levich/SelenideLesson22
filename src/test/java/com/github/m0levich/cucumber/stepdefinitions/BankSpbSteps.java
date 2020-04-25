package com.github.m0levich.cucumber.stepdefinitions;

import com.codeborne.selenide.Selenide;
import com.github.m0levich.BankSpbTest;
import com.github.m0levich.ReadingFromFile;
import com.github.m0levich.blocks.FinancialFreedom;
import com.github.m0levich.pages.*;
import io.cucumber.java.ru.Допустим;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BankSpbSteps {
    private static final Logger LOG = LoggerFactory.getLogger(BankSpbTest.class);
    ReadingFromFile readingFromFile = new ReadingFromFile();

    @Допустим("пользователь переходит на сайт интернет банка")
    public void пользовательПереходитНаСайтИнтернетБанка() {
        Selenide.open("https://idemo.bspb.ru");
    }

    @Допустим("меняет язык на русский")
    public void меняетЯзыкНаРусский() {
        LoginPage loginPage = new LoginPage();
        loginPage.checkLanguage();
        title().contains("Интернет банк - Банк Санкт-Петербург");
    }

    @Допустим("авторизуется")
    public void авторизуется() {
        LoginPage loginPage = new LoginPage();
        try {
            loginPage.login(readingFromFile.getLogin(), readingFromFile.getPassword());
        } catch (FileNotFoundException e) {
            LOG.info("Файл не найден в корне проекта", e);
        }
    }

    @Допустим("вводит код и нажимает Войти")
    public void вводитКодИНажимаетВойти() {
        TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage();
        try {
            twoFactorAuthPage.twoFactorsAuth(readingFromFile.getSmsPin());
        } catch (FileNotFoundException e) {
            LOG.info("Файл c кодом не найден в корне проекта", e);
        }
    }

    @Допустим("переходит в раздел {string}")
    public void переходитВРаздел(String string) {
        BasePage basePage = new MainPage();
        basePage.getNavigationMenu().selectMenu(string);
    }

    @Допустим("проверяет блок Финансовая свобода на матчер")
    public void проверяетБлокФинансоваяСвободаНаМатчер() {
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.getFinancialFreedom().getFinancialFreedomBlock().should(matchText(overviewPage.getFinancialFreedom().getMatcher()));
    }

    @Допустим("Наводит курсор на блок")
    public void наводитКурсорНаБлок() {
        FinancialFreedom financialFreedom = new FinancialFreedom();
        Actions act = new Actions(getWebDriver());
        act.moveToElement(financialFreedom.getFinancialFreedomBlock()).perform();
        financialFreedom.getMyAssets().shouldBe(visible);
    }

    @Допустим("проверяет на матчер появившийся блок Мои средства")
    public void проверяетНаМатчерПоявившийсяБлокМоиСредства() {
        FinancialFreedom financialFreedom = new FinancialFreedom();
        String myAssetsMatcher = "Моих средств " + financialFreedom.getMatcher();
        financialFreedom.getMyAssets().should(matchText(myAssetsMatcher));
    }

}
