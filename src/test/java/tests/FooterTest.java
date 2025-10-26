package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lib.ui.pages.MainPageObject;


import static lib.ui.elements.FooterElements.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooterTest extends CoreTestCase {

    @Test
    @DisplayName("Наличие футера на главной сранице")
    public void testFooterPresent() throws InterruptedException {
        MainPageObject pageObject = new MainPageObject(driver);

        Thread.sleep(3000);
        pageObject.swipeUpToElement(FOOTER_BLOCK, "футер не отображается", 5);
        assertTrue(pageObject.waitForElementPresents(FOOTER_BLOCK).isDisplayed());
    }

}