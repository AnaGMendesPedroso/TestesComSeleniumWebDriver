package google;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleAction {

    private GooglePage googlePage;

    public GoogleAction(WebDriver driver){
        this.googlePage = new GooglePage(driver);
    }

    public void pesquisaAlgo(String algo){
        googlePage.getInputBusca().sendKeys(algo);
        googlePage.getInputBusca().sendKeys(Keys.ENTER);
    }
}
