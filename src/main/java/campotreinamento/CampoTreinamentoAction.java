package campotreinamento;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import util.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class CampoTreinamentoAction {

    private CampoTreinamentoPage campoTreinamentoPage;

    public CampoTreinamentoAction(WebDriver driver){
        this.campoTreinamentoPage = new CampoTreinamentoPage(driver);
    }

    public String preencheTextFieldNome(String nome){
        campoTreinamentoPage.getTextFieldNome().sendKeys(nome);
        return campoTreinamentoPage.getTextFieldNome().getAttribute("value");
    }

    public String preencheTextFieldSobrenome(String sobrenome){
        campoTreinamentoPage.getTextFieldSobrenome().sendKeys(sobrenome);
        return campoTreinamentoPage.getTextFieldNome().getAttribute("value");
    }

    public boolean selecionaRadioButton(String sexo){
        boolean selecionou=false;
        if (sexo.equalsIgnoreCase("Masculino")){
            campoTreinamentoPage.getRadioBtnMasculino().click();
            selecionou = campoTreinamentoPage.getRadioBtnMasculino().isSelected();
        }
        if (sexo.equalsIgnoreCase("Feminino")){
            campoTreinamentoPage.getRadioBtnFeminino().click();
            selecionou = campoTreinamentoPage.getRadioBtnFeminino().isSelected();
        }
        return selecionou;
    }
    public boolean selecionaCheckComida(String... comidas){
        List<WebElement> elementosComida = new ArrayList<>();
        for (String comida: comidas) {
            if (comida.equalsIgnoreCase("carne")){
                campoTreinamentoPage.getCheckCarne().click();
                elementosComida.add(campoTreinamentoPage.getCheckCarne());
            }
            else if(comida.equalsIgnoreCase("frango")){
                campoTreinamentoPage.getCheckFrango().click();
                elementosComida.add(campoTreinamentoPage.getCheckFrango());
            }
            else if (comida.equalsIgnoreCase("pizza")){
                campoTreinamentoPage.getCheckPizza().click();
                elementosComida.add(campoTreinamentoPage.getCheckPizza());
            }
            else if (comida.equalsIgnoreCase("vegetariano")){
                campoTreinamentoPage.getCheckVegetariano().click();
                elementosComida.add(campoTreinamentoPage.getCheckVegetariano());
            }
        }
        return verificaSeTodosElementosEstaoSelecionados(elementosComida);
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

    public int selecionaEsportes(String... esportes){
        Select combo = new Select(campoTreinamentoPage.getComboEsportes());
        for (String s: esportes) {
            combo.selectByVisibleText(s);
        }
        return combo.getAllSelectedOptions().size();
    }

    public int deselecionaEsportes(String... esportes){
        Select combo = new Select(campoTreinamentoPage.getComboEsportes());
        for (String s: esportes) {
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

        for (int i = 0; i <pessoa.getEsportesQuePratica().size() ; i++) {
            selecionaEsportes(pessoa.getEsportesQuePratica().get(i));
        }

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

    public boolean verificaSeElementoEstaSelecionado(WebElement elemento){
        return elemento.isDisplayed();
    }

    public boolean verificaSeTodosElementosEstaoSelecionados(List<WebElement> elementos){
        boolean verificacao = true;
        for (WebElement el: elementos) {
            if (!el.isDisplayed()) {
                verificacao = false;
                System.out.println("Verificação falhou para o elemento: "+ el.getText() + "deveria estar selecionado, mas não está :(");
                break;
            }
        }
        return verificacao;
    }
    public String pegaTextoElementoPorId(String id){
        return campoTreinamentoPage.getDriver().findElement(By.id(id)).getText();
    }

    public String pegaTextoDeTagPorId(String id, String tagName){
        return campoTreinamentoPage.getDriver().findElement(By.id(id)).findElement(By.tagName(tagName)).getText();
    }

    public Object executaComandoJavaScript(String cmd, Object... parametros){
        JavascriptExecutor executor = (JavascriptExecutor) campoTreinamentoPage.getDriver();
        return executor.executeScript(cmd,parametros);
    }
}
