package org.qamation.charities.extractor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qamation.web.page.Page;
import org.qamation.web.page.WebPageFactory;

import java.util.ArrayList;
import java.util.List;

public class CharityLinkExtractor extends AbstractExtractor{
    private static final String CHARITY_LINK_XPATH ="//*/a[@class='title lnk']";
    private static final String LINK_ATTRIBUTE_NAME = "href";
    private static By xpath = By.xpath(CHARITY_LINK_XPATH);



    public CharityLinkExtractor(WebDriver driver, String url) {
        super(driver,url);
    }

    public List<String> getCharityLinks(WebDriver driver) {
        ArrayList<String> links = new ArrayList<>();
        List<WebElement> els = driver.findElements(xpath);
        if (els.size()>0) {
            for (WebElement e : els) {
                links.add(e.getAttribute(LINK_ATTRIBUTE_NAME));
            }
        }
        return links;
    }

}
