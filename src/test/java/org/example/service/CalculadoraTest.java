package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractSet;

public class CalculadoraTest {

@Test
    //Padrão de AAA (Sempre fazer teste assim) PAG.26 DOC PORLIMORFISMO
    void nomeDoTeste(){
        // ARRANGE

        // ACT

        // ASSERT

        // CLEANP (SE NECESSARIO)
    }
@Test
void deveSomarDoisNumeros_DeveDividirOResultadoDaSoma(){
        //ARRANGE
    Calculadora calc = new Calculadora();
    int valorPositivo = 10;
    int valorPositiv2 = 5;
    int valorDivisao = 3;
    int expected = 5;


        //act
   int resuldado = calc.somar(valorPositivo, valorPositiv2) / valorDivisao;

    //assert

    Assertions.assertEquals(expected, resuldado);
}

@Test
    void somaDoisNumerosUmPositivoEOutroNegativo_DeeDiminuirUmDoOutro(){

         //ARRANE - PREPARAR DADOS E OBJETOS // Oque eu preciso preparar antes de começar oo test, define vlores,
        Calculadora calc = new Calculadora(); // Inicializa o objeto a ser testado
        int valorPositivo = 10;
        int valorNegativo = -5;
        int expected = 5;

        //ACT - EXECUTAR A AÇÃO/MÉTODO QUE SERÁ TESTADO, Aqui fica todo o comportamento (coração do teste)
        int resultado = calc.somar(valorNegativo, valorPositivo);// Chama o método a ser testado



        //ASSERT - VERIFICAÇAO SE O RESULTADO ESTÁ CORRETO, o que eu espero como resultado,
        // o que precisamos usar aqui ? Assertivas (métodos como assertEquals(), assertTrue(), assertThrows(), etc.) são usadas aqui.
        Assertions.assertEquals(expected, resultado);  // Verifica o resultado

        // Assert
        // assertThrows(ArithmeticException.class, divisaoPorZero, "Deve lançar ArithmeticException ao dividir por zero");




        //CLEANUP - OPCINAL EM TESTE  UNITARIOS
        //FECHAR CONNECTION

    }


    @Test  //Precisamos mostrar para JVM que isso é um teste(@Test)
    @DisplayName("deveSomarDoisNumeros_DeveRetornarCorreto")
            // Criando uma função
    void somarDoisNumeros_DeveRetornaResultadoCorreto(){
        //Chamando a class Calculadora();
        var calc = new Calculadora();
        //atribuinto a função somar a Calculadora metádo principal, onde vamos atribuir 2 valores.

        //Aqui invocamos a class Principal (Calculadora) e atribuimos o metádo dela(Somar) com duas entra a e b.
        var actual = calc.somar(5, 4); //Somar de 5 + 4 = 9
                        //Esperado //Resultado atual
        //assertEquals(expected, actual): Verifica se dois valores são iguais.
        Assertions.assertEquals(9, actual);
    }

    //Test negativo de erro do codigo de  cima.
    @Test
    @DisplayName("deveSomarDoisNumeros_DeveRetornarErro")
    void somarDoisNumero_DeveRetornaResultadoErrado(){
            var cacl = new Calculadora();//Chamando a class Calculadora();
            var actual = cacl.somar(2,4); //Somar 2 + 4 = 6
        Assertions.assertEquals(2, actual, "A soma de dois numero deve ser maoir que a posição real");  //erro de proposito.
    }

    @Test
    void somaDoisNumerosNegativos_DeveRetornaASomaNegativo(){
            var calc = new Calculadora();//Chamando a class Calculadora();
            var actual = calc.somar(-6,-4); //Somar Negativa -10
            Assertions.assertEquals(-10, actual);
    }


    // Test de divisão
    @Test
    @DisplayName("divisãoPorZero_DeveLançarUmaExececao")
    void divisãoPorZero_DeveLançarUmaExececao(){
        var calc = new Calculadora();//Chamando a class Calculadora();

                                //
        Assertions.assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));
    }

}
