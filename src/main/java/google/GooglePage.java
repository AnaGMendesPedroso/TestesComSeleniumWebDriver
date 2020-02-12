package google;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {

    @Getter
    @FindBy(xpath="//input[@name='q']")
    private WebElement inputBusca;

    public GooglePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
