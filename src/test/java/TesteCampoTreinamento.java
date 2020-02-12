import campotreinamento.CampoTreinamentoAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Pessoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TesteCampoTreinamento {

    private CampoTreinamentoAction campoTreinamentoAction;
    private WebDriver driver;

    @BeforeMethod
    private void goToHomePage(){
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.campoTreinamentoAction = new CampoTreinamentoAction(driver);
        driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
    }

    @AfterMethod
    public void setDown(){
        this.driver.quit();
    }

    @Test
    public void testeTextField(){
        String value = campoTreinamentoAction.preencheTextFieldNome("TESTE É POP");
        Assert.assertEquals("TESTE É POP", value);
    }

    @Test
    public void testRadioButtonMasculino(){
        Assert.assertTrue(campoTreinamentoAction.selecionaRadioButton("Masculino"));
    }

    @Test
    public void testRadioButtonFeminino() {
        Assert.assertTrue(campoTreinamentoAction.selecionaRadioButton("Feminino"));
    }

    @Test
    public void testCheckBoxPizza(){
        Assert.assertTrue(campoTreinamentoAction.selecionaCheckComida("pizza"));
    }

    @Test
    public void testCheckBoxCarne(){
        Assert.assertTrue(campoTreinamentoAction.selecionaCheckComida("carne"));
    }

    @Test
    public void testCheckBoxFrango(){
        Assert.assertTrue(campoTreinamentoAction.selecionaCheckComida("frango"));
    }

    @Test
    public void testCheckBoxVegetariano(){
        Assert.assertTrue(campoTreinamentoAction.selecionaCheckComida("Vegetariano"));
    }

    @Test
    public void test1grauincomp(){
      String result = campoTreinamentoAction.selecionarEscolaridadeDropDown("1o grau incompleto");
        Assert.assertEquals(result,"1grauincomp");
    }

    @Test
    public void testDoutorado(){
        String result = campoTreinamentoAction.selecionarEscolaridadeDropDown("Doutorado");
        Assert.assertEquals(result,"doutorado");
    }

    @Test
    public void testMestrado(){
        String result = campoTreinamentoAction.selecionarEscolaridadeDropDown("Mestrado");
        Assert.assertEquals(result,"mestrado");
    }

    @Test
    public void testEspecializacao(){
        String result = campoTreinamentoAction.selecionarEscolaridadeDropDown("Especializacao");
        Assert.assertEquals(result,"especializacao");
    }

    @Test
    public void testSelecionaComboMulti(){
        List esportes = new ArrayList<String>();
        esportes.add("Futebol");
        esportes.add("Karate");
        int qtdElementosCombo = campoTreinamentoAction.selecionaEsportes(esportes);
        Assert.assertTrue(qtdElementosCombo==2);
    }

    @Test
    public void testDeselecionaComboMulti(){
        List esportes = new ArrayList<String>();
        esportes.add("Futebol");
        esportes.add("Karate");
        int qtdElementosCombo = campoTreinamentoAction.selecionaEsportes(esportes);
        esportes.remove("Futebol");
        qtdElementosCombo = campoTreinamentoAction.deselecionaEsportes(esportes);
        Assert.assertTrue(qtdElementosCombo==1);
    }

    @Test
    public void testLink(){
        Assert.assertTrue(campoTreinamentoAction.clicaLink());
    }

    @Test
    public void testAlertSimples(){
        Assert.assertEquals(campoTreinamentoAction.aceitaAlertSimples(),"Alert Simples");
    }

    @Test
    public void testConfirmOK(){
        Assert.assertEquals(campoTreinamentoAction.clicaBtnConfirm(),"Confirm Simples");
        campoTreinamentoAction.mudarParaAlertaAceitar();
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaPegarTexto(),"Confirmado");
    }

    @Test
    public void testConfirmCancelar(){
        Assert.assertEquals(campoTreinamentoAction.clicaBtnConfirm(),"Confirm Simples");
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaNegar(),"Negado");
        campoTreinamentoAction.mudarParaAlertaAceitar();
    }

    @Test
    public void testPromptFeliz(){
        String teste = "13";
        Assert.assertEquals(campoTreinamentoAction.pegaTextoPrompt(teste),"Digite um numero");
        campoTreinamentoAction.mudarParaAlertaEnviarTexto(teste);
        teste = "Era "+teste+"?";
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaAceitarPegarTexto(),teste);
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaAceitarPegarTexto(),":D");
        campoTreinamentoAction.mudarParaAlertaAceitar();
    }

    @Test
    public void testPromptFelizVazio(){
        String teste = "";
        Assert.assertEquals(campoTreinamentoAction.pegaTextoPrompt(teste),"Digite um numero");
        String result = campoTreinamentoAction.mudarParaAlertaAceitarPegarTexto();
        teste = "Era "+teste+"?";
        Assert.assertEquals(result,teste);
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaAceitarPegarTexto(),":D");
        campoTreinamentoAction.mudarParaAlertaAceitar();

    }
    @Test
    public void testPromptTriste(){
        String teste="null";
        Assert.assertEquals(campoTreinamentoAction.pegaTextoPrompt(teste),"Digite um numero");
        String result = campoTreinamentoAction.mudarParaAlertaNegar();
        teste = "Era "+teste+"?";
        Assert.assertEquals(result,teste);
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaNegar(),":(");
        campoTreinamentoAction.mudarParaAlertaAceitar();
    }

    @Test
    public void desafioCasdastroComSucesso(){
        LinkedList esportes = new LinkedList<String>();
        esportes.add("Karate");
        LinkedList comidas = new LinkedList<String>();
        comidas.add("Carne");
        comidas.add("Pizza");
        Pessoa godofredo = new Pessoa("Godofredo","Feliciano","Masculino",comidas,"superior",esportes);

        campoTreinamentoAction.cadastrarPessoaComSucesso(godofredo);

        Assert.assertTrue(campoTreinamentoAction.pegaTextoDeTagPorId("resultado","span").equalsIgnoreCase("Cadastrado!"));

        Assert.assertTrue(campoTreinamentoAction.verificaSeElementoEstaDisponivel("descNome"));
        String[] nomeResult = campoTreinamentoAction.pegaTextoElementoPorId("descNome").split(" ");
        Assert.assertEquals(nomeResult[1],godofredo.getNome());

        Assert.assertTrue(campoTreinamentoAction.verificaSeElementoEstaDisponivel("descSobrenome"));
        String[] sobrenomeResult = campoTreinamentoAction.pegaTextoElementoPorId("descSobrenome").split(" ");
        Assert.assertEquals(sobrenomeResult[1], godofredo.getSobrenome());

        Assert.assertTrue(campoTreinamentoAction.verificaSeElementoEstaDisponivel("descSexo"));
        Assert.assertEquals(campoTreinamentoAction.pegaTextoDeTagPorId("descSexo","span"), godofredo.getGenero());

        Assert.assertTrue(campoTreinamentoAction.verificaSeElementoEstaDisponivel("descComida"));
        String[] comidasResult = campoTreinamentoAction.pegaTextoElementoPorId("descComida").split(" ");
        Assert.assertTrue(Arrays.asList(comidasResult).containsAll(godofredo.getComidaFavorita()));

        Assert.assertTrue(campoTreinamentoAction.verificaSeElementoEstaDisponivel("descEscolaridade"));
        Assert.assertEquals(campoTreinamentoAction.pegaTextoDeTagPorId("descEscolaridade","span"), godofredo.getEscolaridade());

        Assert.assertTrue(campoTreinamentoAction.verificaSeElementoEstaDisponivel("descEsportes"));
        String[] esportesResult = campoTreinamentoAction.pegaTextoElementoPorId("descEsportes").split(" ");
        Assert.assertTrue(Arrays.asList(esportesResult).containsAll(godofredo.getEsportesQuePratica()));
    }

    @Test
    public void interageFrame(){
        String textFrame = campoTreinamentoAction.clickBtnDentroFrame();
        Assert.assertEquals(textFrame, "Frame OK!");
        campoTreinamentoAction.mudarParaAlertaAceitar();
        campoTreinamentoAction.voltarParaConteudoPadrao();
        String textFieldNome = campoTreinamentoAction.preencheTextFieldNome(textFrame);
        Assert.assertEquals(textFieldNome,"Frame OK!");
    }

    @Test
    public void testeRegraNomeObrigatorio(){
        campoTreinamentoAction.preencheTextFieldNome("");
        campoTreinamentoAction.clicaBtnCadastrar();
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaPegarTexto(),"Nome eh obrigatorio");
    }

    @Test
    public void testeRegraSobrenomeObrigatorio(){
        campoTreinamentoAction.preencheTextFieldNome("BLA");
        campoTreinamentoAction.preencheTextFieldSobrenome("");
        campoTreinamentoAction.clicaBtnCadastrar();
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaPegarTexto(),"Sobrenome eh obrigatorio");
    }
    @Test
    public void testeRegraSexoObrigatorio(){
        campoTreinamentoAction.preencheTextFieldNome("BLA");
        campoTreinamentoAction.preencheTextFieldSobrenome("ALB");
        campoTreinamentoAction.clicaBtnCadastrar();
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaPegarTexto(),"Sexo eh obrigatorio");
    }

    @Test
    public void testeRegraVegetarianoComeCarne(){
        campoTreinamentoAction.preencheTextFieldNome("BLA");
        campoTreinamentoAction.preencheTextFieldSobrenome("ALB");
        campoTreinamentoAction.selecionaRadioButton("Masculino");
        campoTreinamentoAction.selecionaCheckComida("carne");
        campoTreinamentoAction.selecionaCheckComida("vegetariano");
        campoTreinamentoAction.clicaBtnCadastrar();
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaPegarTexto(),"Tem certeza que voce eh vegetariano?");
    }

    @Test
    public void testeRegraPraticaSoQueNao(){
        campoTreinamentoAction.preencheTextFieldNome("BLA");
        campoTreinamentoAction.preencheTextFieldSobrenome("ALB");
        campoTreinamentoAction.selecionaRadioButton("Masculino");
        campoTreinamentoAction.selecionaCheckComida("carne");
        ArrayList esportes = new ArrayList();
        esportes.add("Corrida");
        esportes.add("O que eh esporte?");
        campoTreinamentoAction.selecionaEsportes(esportes);
        campoTreinamentoAction.clicaBtnCadastrar();
        Assert.assertEquals(campoTreinamentoAction.mudarParaAlertaPegarTexto(),"Voce faz esporte ou nao?");
    }
}
