package campotreinamento;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampoTreinamentoPage {

    @Getter
    @FindBy(id = "elementosForm:nome")
    private WebElement textFieldNome;

    @Getter
    @FindBy(id = "elementosForm:sobrenome")
    private WebElement textFieldSobrenome;

    @Getter
    @FindBy(id="elementosForm:sexo:0")
    private WebElement radioBtnMasculino;

    @Getter
    @FindBy(id="elementosForm:sexo:1")
    private WebElement radioBtnFeminino;


    @Getter
    @FindBy(id = "elementosForm:comidaFavorita:0")
    private WebElement checkCarne;

    @Getter
    @FindBy(id = "elementosForm:comidaFavorita:1")
    private WebElement checkFrango;

    @Getter
    @FindBy(id = "elementosForm:comidaFavorita:2")
    private WebElement checkPizza;

    @Getter
    @FindBy(id = "elementosForm:comidaFavorita:3")
    private WebElement checkVegetariano;

    @Getter
    @FindBy(xpath = "//td/select[@id='elementosForm:escolaridade']")
    private WebElement dropDownEscolaridade;

    @Getter
    @FindBy(id = "elementosForm:esportes")
    private WebElement comboEsportes;

    @Getter
    @FindBy(linkText = "Voltar")
    private WebElement linkVoltar;

    @Getter
    @FindBy(xpath = "//div[@id='resultado']")
    private WebElement resultadoVoltar;

    @Getter
    @FindBy(id="alert")
    private WebElement btnAlertSimples;

    @Getter
    @FindBy(id = "confirm")
    private WebElement btnConfirm;

    @Getter
    @FindBy(id = "prompt")
    private WebElement btnPrompt;

    @Getter
    @FindBy(id = "elementosForm:cadastrar")
    private WebElement btnCadastrar;

    @Getter
    @FindBy(id = "frameButton")
    private WebElement btnDentroFrame;

    @Getter
    @FindBy(id = "frame1")
    private WebElement frame;

    @Getter
    private WebDriver driver;

    public CampoTreinamentoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
