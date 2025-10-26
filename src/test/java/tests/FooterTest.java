package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lib.ui.pages.MainPageObject;
import org.openqa.selenium.Dimension;

import static lib.ui.elements.FooterElements.*;
import static lib.ui.elements.HeaderElements.*;
import static lib.ui.pages.ProjectsPageObject.*;
import static lib.ui.pages.StartPageObject.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooterTest extends CoreTestCase {

    @Test
    @DisplayName("Наличие футера на главной сранице")
    public void testFooterOnMainPage()
    {
        MainPageObject pageObject = new MainPageObject(driver, MAIN_PAGE_ADDRESS);

        pageObject.swipeUpToElement(FOOTER_BLOCK, "футер не отображается", 30);
        assertTrue(pageObject.isElementLocatedOnTheScreen(FOOTER_BLOCK));
    }

    @Test
    @DisplayName("Наличие логотипа в футере на главной странице")
    public void testFooterLogoOnMainPage()  {
        MainPageObject pageObject = new MainPageObject(driver, MAIN_PAGE_ADDRESS);

        pageObject.swipeUpToElement(FOOTER_LOGO, "футер не отображается", 30);
        assertTrue(pageObject.isElementLocatedOnTheScreen(FOOTER_LOGO));
    }

    @Test
    @DisplayName("Наличие политики конфиденцальности на главной странице")
    public void testFooterConfPoliticOnMainPage()    {
        MainPageObject pageObject = new MainPageObject(driver, MAIN_PAGE_ADDRESS);

        pageObject.swipeUpToElement(CONFIDENTIAL_POLITIC, "футер не отображается", 30);
        assertTrue(pageObject.isElementLocatedOnTheScreen(CONFIDENTIAL_POLITIC));
    }

    @Test
    @DisplayName("Наличие логотипа в футере на странице проектов")
    public void testFooterLogoOnProjetsPage()    {
        MainPageObject pageObject = new MainPageObject(driver, PROGECTS_PAGE_ADRESS);

        pageObject.swipeUpToElement(FOOTER_LOGO, "футер не отображается", 30);
        assertTrue(pageObject.isElementLocatedOnTheScreen(FOOTER_LOGO));
    }

    @Test
    @DisplayName("наличие футера после перехода на страницу проектов")
    public void TestFooterElementAfterMoveBySite() throws InterruptedException {
        MainPageObject pageObject = new MainPageObject(driver, MAIN_PAGE_ADDRESS);
        Dimension windowSize = driver.manage().window().getSize();


        if(windowSize.getWidth()>1295) {
            pageObject.waitForElementPresents(LINK_TO_PROJECT_PAGE);
            pageObject.clickOnElement(LINK_TO_PROJECT_PAGE, "Не получилось перейти на страницу проектов");
        } else {
            Thread.sleep(3000);
            pageObject.clickOnElement(BURGER_BUTTON,"Не получилось нажать на кнопку-бургер");
            Thread.sleep(1000);
            pageObject.clickOnElement(LINK_TO_PROJECT_PAGE_UNDER_BURGER, "Не получилось перейти на страницу проектов через буургер");
        }

        pageObject.swipeUpToElement(FOOTER_LOGO, "футер не отображается", 30);
        assertTrue(pageObject.isElementLocatedOnTheScreen(FOOTER_LOGO));
    }



}