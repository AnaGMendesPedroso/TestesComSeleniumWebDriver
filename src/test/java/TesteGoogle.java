import google.GoogleAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TesteGoogle {

    private WebDriver driver;
    private GoogleAction googleAction;

    @BeforeClass
    public void setUp() {
         this.driver = new ChromeDriver();
         driver.manage().window().maximize();
         this.googleAction = new GoogleAction(driver);
    }

    @AfterClass
    public void setDown(){
        this.driver.quit();
    }

    @Test
    public void testePesquisa(){
        driver.get("https://www.google.com/");
        googleAction.pesquisaAlgo("Teste de Software");
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("teste de software - Pesquisa Google"));
    }

}
