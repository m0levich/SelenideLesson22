package com.github.m0levich;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.m0levich.blocks.FinancialFreedom;
import com.github.m0levich.pages.LoginPage;
import com.github.m0levich.pages.OverviewPage;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BankSpbTest {

    @BeforeMethod(description = "Переход на страницу интернет-банка")
    public void initDriver() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        open("https://idemo.bspb.ru");
    }

    @Owner("Molkov Egor")
    @Epic("Страница Обзор")
    @Story("Я как клиент банка хочу просматривать блок Финансовая свобода")
    @TmsLink("1234")
    @Issue("issue-21")
    @Test(description = "Тест на проверку отображения блока Финансовая свобода")
    @Severity(SeverityLevel.CRITICAL)
    public void myAssetsTest() {

        LoginPage loginPage = new LoginPage();
        ReadingFromFile readingFromFile = new ReadingFromFile();
        loginPage.checkLanguage();
        title().contains("Интернет банк - Банк Санкт-Петербург");
        try {
            loginPage
                    .login(readingFromFile.getLogin(), readingFromFile.getPassword())
                    .twoFactorsAuth(readingFromFile.getSmsPin())
                    .getNavigationMenu()
                    .selectMenu("Обзор");
        } catch (FileNotFoundException e) {
            Assert.fail("Файл не найден в корне проекта", e);
        }
        OverviewPage overviewPage = new OverviewPage();
        FinancialFreedom financialFreedom = new FinancialFreedom();
        financialFreedom.checkFinancialFreedomOnMatcher(overviewPage.getFinancialFreedom().getFinancialFreedomBlock(), overviewPage.getFinancialFreedom().getMatcher());
        Actions act = new Actions(getWebDriver());
        financialFreedom.moveToBlock(overviewPage, act);
        financialFreedom.checkMyAssetsVisible(overviewPage);
        financialFreedom.checkMyAssetsOnMather(overviewPage);
    }
}
