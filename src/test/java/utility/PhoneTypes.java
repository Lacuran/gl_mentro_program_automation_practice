package utility;

import org.openqa.selenium.WebDriver;
import panda.pages.IPhonePage;
import panda.pages.SamsungGalaxyPage;
import panda.pages.XPeriaPage;


public class PhoneTypes {
    WebDriver driver;

    public PhoneTypes(WebDriver driver) {
        this.driver = driver;
    }

    public String getPhoneCostOnPhonePage(String phoneName) {
        return switch (phoneName){
            case "Sony Xperia" -> xPeriaPage().getPhoneCostString();
            case "IPhone" -> iPhonePage().getPhoneCostString();
            case "Samsung Galaxy" -> samsungGalaxyPage().getPhoneCostString();
            default -> "Check phone name";
        };
    }


    private XPeriaPage xPeriaPage(){
        return new XPeriaPage(driver);
    }

    private IPhonePage iPhonePage(){
        return new IPhonePage(driver);
    }

    private SamsungGalaxyPage samsungGalaxyPage(){
        return new SamsungGalaxyPage(driver);
    }
}
