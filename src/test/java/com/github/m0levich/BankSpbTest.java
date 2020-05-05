package com.github.m0levich;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.m0levich.pages.LoginPage;
import com.github.m0levich.pages.OverviewPage;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

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
        Assert.assertTrue(title().contains("Интернет банк - Банк Санкт-Петербург"), "Title is not contains expected text");
        try {
            loginPage
                    .login(readingFromFile.getLogin(), readingFromFile.getPassword())
                    .twoFactorsAuth(readingFromFile.getSmsPin())
                    .navigationMenu
                    .selectMenu("Обзор");
        } catch (FileNotFoundException e) {
            Assert.fail("Файл не найден в корне проекта", e);
        }
        OverviewPage overviewPage = new OverviewPage();
        step("Проверка на матчер суммы в блоке Финансовая свобода", () -> {
            overviewPage.financialFreedom.financialFreedomBlock.should(matchText(overviewPage.financialFreedom.matcher));
        });
        step("Перемещение курсора на блок", () -> {
            Actions act = new Actions(getWebDriver());
            act.moveToElement(overviewPage.financialFreedom.financialFreedomBlock).perform();
        });
        step("Проверка на видимость элемента", () ->
            overviewPage.financialFreedom.myAssets.shouldBe(visible)
        );
        step("Проверка на матчер Мои средства", () -> {
            String myAssetsMatcher = "Моих средств " + overviewPage.financialFreedom.matcher;
            overviewPage.financialFreedom.myAssets.should(matchText(myAssetsMatcher));
        });
    }
}
