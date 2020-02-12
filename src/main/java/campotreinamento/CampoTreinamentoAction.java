package campotreinamento;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.Pessoa;

import java.util.List;

public class CampoTreinamentoAction {

    private CampoTreinamentoPage campoTreinamentoPage;

    public CampoTreinamentoAction(WebDriver driver){
        this.campoTreinamentoPage = new CampoTreinamentoPage(driver);
    }

    public String preencheTextFieldNome(String texto){
        campoTreinamentoPage.getTextFieldNome().sendKeys(texto);
        return campoTreinamentoPage.getTextFieldNome().getAttribute("value");
    }

    public String preencheTextFieldSobrenome(String texto){
        campoTreinamentoPage.getTextFieldSobrenome().sendKeys(texto);
        return campoTreinamentoPage.getTextFieldNome().getAttribute("value");
    }

    public boolean selecionaRadioButton(String opcao){
        if (opcao.equalsIgnoreCase("Masculino")){
            campoTreinamentoPage.getRadioBtnMasculino().click();
            return campoTreinamentoPage.getRadioBtnMasculino().isSelected();
        }
        else if (opcao.equalsIgnoreCase("Feminino")){
            campoTreinamentoPage.getRadioBtnFeminino().click();
            return campoTreinamentoPage.getRadioBtnFeminino().isSelected();
        }else throw new IllegalArgumentException();
    }
    public boolean selecionaCheckComida(String comida){
        if (comida.equalsIgnoreCase("carne")){
            campoTreinamentoPage.getCheckCarne().click();
            return campoTreinamentoPage.getCheckCarne().isSelected();
        }
        else if(comida.equalsIgnoreCase("frango")){
            campoTreinamentoPage.getCheckFrango().click();
            return campoTreinamentoPage.getCheckFrango().isSelected();
        }
        else if (comida.equalsIgnoreCase("pizza")){
            campoTreinamentoPage.getCheckPizza().click();
            return campoTreinamentoPage.getCheckPizza().isSelected();
        }
        else if (comida.equalsIgnoreCase("vegetariano")){
            campoTreinamentoPage.getCheckVegetariano().click();
            return campoTreinamentoPage.getCheckVegetariano().isSelected();
        }
        else throw new IllegalArgumentException();
    }

    public String selecionarEscolaridadeDropDown(String escolaridade){
        List <WebElement> optionElements = campoTreinamentoPage.getDropDownEscolaridade().findElements(By.tagName("option"));
        WebElement result = null;
        for (WebElement w: optionElements) {
            if(w.getText().equalsIgnoreCase(escolaridade)){
                w.click();
                result=w;
                break;
            }
        }
        return result.getAttribute("value");
    }

    public int selecionaEsportes(List<String> listaEsportes){
        Select combo = new Select(campoTreinamentoPage.getComboEsportes());
        for (String s: listaEsportes) {
            combo.selectByVisibleText(s);
        }
        return combo.getAllSelectedOptions().size();
    }

    public int deselecionaEsportes(List<String> listaEsportes){
        Select combo = new Select(campoTreinamentoPage.getComboEsportes());
        for (String s: listaEsportes) {
            combo.deselectByVisibleText(s);
        }
        return combo.getAllSelectedOptions().size();
    }

    public boolean clicaLink(){
        campoTreinamentoPage.getLinkVoltar().click();
        return campoTreinamentoPage.getResultadoVoltar().isDisplayed();
    }

    public String aceitaAlertSimples(){
        campoTreinamentoPage.getBtnAlertSimples().click();
        String result = mudarParaAlertaPegarTexto();
        campoTreinamentoPage.getDriver().switchTo().alert().accept();
        return result;
    }
    public String pegaTextoPrompt(String texto){
        campoTreinamentoPage.getBtnPrompt().click();
        mudarParaAlertaEnviarTexto(texto);
        return mudarParaAlertaPegarTexto();
    }

    public String clicaBtnConfirm(){
        campoTreinamentoPage.getBtnConfirm().click();
        return campoTreinamentoPage.getDriver().switchTo().alert().getText();
    }
    public String mudarParaAlertaPegarTexto() {
        return campoTreinamentoPage.getDriver().switchTo().alert().getText();
    }

    public String mudarParaAlertaAceitar() {
        Alert alert = campoTreinamentoPage.getDriver().switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();
        return textAlert;
    }

    public String mudarParaAlertaAceitarPegarTexto() {
        Alert alert = campoTreinamentoPage.getDriver().switchTo().alert();
        alert.accept();
        String textAlert = alert.getText();
        return textAlert;
    }

    public String mudarParaAlertaNegar() {
        Alert alert = campoTreinamentoPage.getDriver().switchTo().alert();
        alert.dismiss();
        String textAlert = alert.getText();
        return textAlert;
    }
    public void mudarParaAlertaEnviarTexto(String texto) {
        campoTreinamentoPage.getDriver().switchTo().alert().sendKeys(texto);
    }
    public WebDriver voltarParaConteudoPadrao() {
        return campoTreinamentoPage.getDriver().switchTo().defaultContent();    }

    public void clicaBtnCadastrar(){
        campoTreinamentoPage.getBtnCadastrar().click();
    }

    public void cadastrarPessoaComSucesso(Pessoa pessoa){

        preencheTextFieldNome(pessoa.getNome());
        preencheTextFieldSobrenome(pessoa.getSobrenome());
        selecionaRadioButton(pessoa.getGenero());
        for (String comida:pessoa.getComidaFavorita()) {
            selecionaCheckComida(comida);
        }
        selecionarEscolaridadeDropDown(pessoa.getEscolaridade());
        selecionaEsportes(pessoa.getEsportesQuePratica());

        campoTreinamentoPage.getBtnCadastrar().click();
    }

    public String clickBtnDentroFrame(){
        campoTreinamentoPage.getDriver().switchTo().frame(campoTreinamentoPage.getFrame());
        campoTreinamentoPage.getBtnDentroFrame().click();
        return campoTreinamentoPage.getDriver().switchTo().alert().getText();
    }

    public boolean verificaSeElementoEstaDisponivel(String id){
        return campoTreinamentoPage.getDriver().findElement(By.id(id)).isDisplayed();
    }

    public String pegaTextoElementoPorId(String id){
        return campoTreinamentoPage.getDriver().findElement(By.id(id)).getText();
    }

    public String pegaTextoDeTagPorId(String id, String tagName){
        return campoTreinamentoPage.getDriver().findElement(By.id(id)).findElement(By.tagName(tagName)).getText();
    }
}
