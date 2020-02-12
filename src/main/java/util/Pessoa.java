package util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;

@AllArgsConstructor
@Getter
public class Pessoa {
    private String nome;
    private String sobrenome;
    private String genero;
    private LinkedList<String> comidaFavorita;
    private String escolaridade;
    private LinkedList<String>  esportesQuePratica;
}
