package parametrizaveis;

import campotreinamento.CampoTreinamentoAction;
import campotreinamento.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

	private WebDriver driver;
	private CampoTreinamentoAction ctAction;
	private CampoTreinamentoPage ctPage;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public String[] comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	

	@Before
	public void goToHomePage(){
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		this.ctAction = new CampoTreinamentoAction(driver);
		driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
	}

	@After
	public void setDown(){
		this.driver.quit();
	}


	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"", "", "", new String[]{}, new String[]{}, "Nome eh obrigatorio"},
			{"Ana", "", "",new String[]{}, new String[]{}, "Sobrenome eh obrigatorio"},
			{"Ana", "Pedroso", "", new String[]{}, new String[]{}, "Sexo eh obrigatorio"},
			{"Ana", "Pedroso", "Feminino", new String[]{"Frango", "Vegetariano"}, new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Ana", "Pedroso", "Feminino", new String[]{"Frango"}, new String[]{"Corrida", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras(){
		ctAction.preencheTextFieldNome(nome);
		ctAction.preencheTextFieldSobrenome(sobrenome);
		ctAction.selecionaRadioButton(sexo);
		ctAction.selecionaCheckComida(comidas);
		ctAction.selecionaEsportes(esportes);
		ctAction.clicaBtnCadastrar();
		System.out.println(msg);
		Assert.assertEquals(msg, ctAction.mudarParaAlertaAceitar());
	}
}
