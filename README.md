
# 📚 Meu Guia de Estudos sobre Testes de Software

Este documento resume meus aprendizados sobre a importância dos testes de software, os diferentes tipos de teste e uma imersão no framework JUnit 5 para testes em Java, incluindo a manipulação de exceções com `assertThrows()`.

-----

## 💡 Por Que Testar o Código é Crucial?

O teste é uma etapa indispensável no desenvolvimento de software e não deve ser visto como uma "tarefa extra", mas sim como um **investimento** que garante a **qualidade, confiabilidade e sustentabilidade** do código.

  * **Garantia de Qualidade e Confiabilidade:** Confirma que o código funciona como esperado e atende aos requisitos.
  * **Detecção Precoce de Bugs:** Quanto antes um bug é encontrado (especialmente com testes unitários), mais barato e fácil é corrigi-lo.
  * **Melhora a Manutenibilidade e Refatoração:** Uma boa suíte de testes dá confiança para alterar e melhorar o código, sabendo que funcionalidades existentes não serão quebradas. Atua como uma "rede de segurança".
  * **Documentação Viva:** Testes bem escritos mostram como o código deve ser usado e como ele se comporta em diferentes cenários, servindo como uma documentação sempre atualizada.
  * **Validação de Requisitos:** Confirma se o software atende às necessidades dos usuários e stakeholders.
  * **Redução de Custos a Longo Prazo:** Evita bugs em produção, reduz o tempo de depuração e facilita a manutenção, resultando em economia significativa.
  * **Aumento da Confiança da Equipe:** Desenvolvedores se sentem mais seguros ao escrever e implantar código bem testado, levando a maior produtividade.
  * **Suporte à Automação e CI/CD:** Testes automatizados são a base para pipelines de Integração Contínua (CI) e Entrega Contínua (CD), agilizando o ciclo de desenvolvimento e garantindo a qualidade a cada nova alteração.

-----

## 🔬 Tipos de Teste em Desenvolvimento de Software

Os testes podem ser categorizados por sua escala e objetivo:

### 1\. Testes Unitários (Unit Tests)

  * **O que são:** Testes de **menor nível**, focados em verificar a menor unidade de código testável (geralmente um método ou função).
  * **Objetivo:** Garantir que cada unidade individual funcione corretamente **isoladamente**.
  * **Quem faz:** Principalmente os **desenvolvedores**.
  * **Características:**
      * **Isolados:** Não dependem de recursos externos (banco de dados, rede). Dependências são "mockadas" (simuladas).
      * **Rápidos:** Devem ser executados com frequência.
      * **Automatizados:** Essenciais para CI.
  * **Exemplo (Java com JUnit):** Testar se um método `somar(int a, int b)` de uma classe `Calculadora` retorna a soma correta.

<!-- end list -->

```java
// Classe a ser testada
class Calculadora {
    public int somar(int a, int b) {
        return a + b;
    }
    public int dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não permitida");
        }
        return a / b;
    }
}

// Teste Unitário
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {
    @Test
    void deveSomarDoisNumeros() {
        Calculadora calc = new Calculadora();
        assertEquals(5, calc.somar(2, 3));
    }

    @Test
    void deveLancarExcecaoAoDividirPorZero() {
        Calculadora calc = new Calculadora();
        assertThrows(IllegalArgumentException.class, () -> calc.dividir(10, 0));
    }
}
```

### 2\. Testes de Integração (Integration Tests)

  * **O que são:** Verificam como **diferentes módulos ou componentes** interagem entre si.
  * **Objetivo:** Garantir que as unidades, quando combinadas, funcionem **juntas corretamente** (ex: serviço com repositório, backend com API externa).
  * **Quem faz:** Desenvolvedores e testadores.
  * **Características:**
      * **Menos isolados:** Envolvem múltiplos componentes.
      * **Mais lentos:** Por envolverem mais partes do sistema (banco de dados real, servidor web leve).

### 3\. Testes de Sistema / Testes de Ponta a Ponta (E2E Tests)

  * **O que são:** Testam o **sistema completo** como uma única entidade, simulando a interação do usuário.
  * **Objetivo:** Validar o comportamento do software do **ponto de vista do usuário final**, cobrindo fluxos de negócio completos.
  * **Quem faz:** Testadores (QA) e equipes de automação.
  * **Características:**
      * **Abrangentes:** Cobrem da UI ao banco de dados e integrações.
      * **Lentos:** Os mais demorados de executar.
      * **Ferramentas:** Selenium, Cypress, Playwright (Web); Appium (Mobile).

### 4\. Testes de Aceitação (Acceptance Tests)

  * **O que são:** Validam formalmente se o sistema atende aos **requisitos do cliente/usuário final**.
  * **Objetivo:** Confirmar se o software está "aceitável" para a entrega.
  * **Quem faz:** Clientes, usuários finais, Product Owners e testadores.
  * **Ferramentas:** Frameworks BDD como Cucumber (com Gherkin).

**Exemplo (Gherkin):**

```gherkin
Característica: Login de Usuário
    Como um usuário registrado
    Eu quero fazer login no sistema
    Para acessar meu painel pessoal

    Cenário: Login bem-sucedido com credenciais válidas
        Dado que eu estou na página de login
        Quando eu insiro "usuario@exemplo.com" no campo email e "senha123" no campo senha
        E eu clico no botão "Login"
        Então eu devo ser redirecionado para o painel do usuário
        E eu devo ver a mensagem "Bem-vindo, usuario@exemplo.com!"
```

### Outros Tipos de Teste Importantes:

  * **Testes de Performance:** Avaliam velocidade, escalabilidade e estabilidade sob carga.
  * **Testes de Segurança:** Buscam vulnerabilidades (ex: SQL Injection, XSS).
  * **Testes de Usabilidade:** Avaliam a facilidade de uso e experiência do usuário.
  * **Testes de Regressão:** Garantem que novas alterações não quebrem funcionalidades existentes. São executados repetidamente após cada mudança e incluem unitários, integração e E2E.

-----

## 🧪 JUnit 5: O Framework Padrão de Testes em Java

**JUnit 5** é o framework de testes padrão para aplicações Java, fundamental para escrever e executar testes unitários e de integração. Foi reescrito para ser modular e extensível, aproveitando recursos do Java 8+.

### Arquitetura do JUnit 5

1.  **JUnit Platform:** A base para lançar frameworks de teste na JVM. Permite que ferramentas de build e IDEs encontrem e executem testes.
2.  **JUnit Jupiter:** O modelo de programação e a extensão para **escrever testes** no JUnit 5. Contém as anotações e APIs que os desenvolvedores usam (ex: `@Test`, `@BeforeEach`).
3.  **JUnit Vintage:** Oferece **compatibilidade** para executar testes escritos em JUnit 3 e JUnit 4 na JUnit Platform, útil para migrações.

### Principais Anotações do JUnit Jupiter

  * `@Test`: Marca um método como um método de teste.
  * `@BeforeEach`: Executa **antes de cada** método de teste na classe. Ideal para setup.
  * `@AfterEach`: Executa **depois de cada** método de teste na classe. Ideal para limpeza.
  * `@BeforeAll`: Executa **uma única vez antes de todos** os métodos de teste na classe (deve ser `static`). Ideal para setup caro.
  * `@AfterAll`: Executa **uma única vez depois de todos** os métodos de teste na classe (deve ser `static`). Ideal para limpeza global.
  * `@DisplayName("Nome amigável")`: Permite nomes mais legíveis para testes e classes nos relatórios.
  * `@Disabled("Motivo")`: Desativa um teste ou classe de teste.
  * `@RepeatedTest(n)`: Repete um teste `n` vezes.
  * `@ParameterizedTest`: Executa o mesmo teste com **diferentes conjuntos de dados** (requer uma fonte de argumentos como `@ValueSource`).

### Asserções (Assertions)

Métodos estáticos da classe `org.junit.jupiter.api.Assertions` para verificar se um teste passou ou falhou:

  * `assertEquals(expected, actual)`: Verifica igualdade de valores.
  * `assertTrue(condition)` / `assertFalse(condition)`: Verifica condições booleanas.
  * `assertNull(object)` / `assertNotNull(object)`: Verifica se um objeto é nulo/não nulo.
  * `assertThrows(ExpectedException.class, () -> methodCall)`: Verifica se um método lança uma exceção esperada.
  * `assertAll(executable1, executable2, ...)`: Agrupa múltiplas asserções, executando todas mesmo que uma falhe.

### Exemplo de Teste com JUnit 5

```java
import org.junit.jupiter.api.*; // Importa todas as anotações do JUnit Jupiter
import static org.junit.jupiter.api.Assertions.*; // Importa todos os métodos de asserção

class OperacoesMatematicas { /* ... */ } // Classe a ser testada

@DisplayName("Testes para Operações Matemáticas")
class OperacoesMatematicasTest {

    private OperacoesMatematicas operacoes;

    @BeforeAll
    static void setupGlobal() { System.out.println(">>> Iniciando todos os testes..."); }

    @BeforeEach
    void setupCadaTeste() { operacoes = new OperacoesMatematicas(); System.out.println("  - Preparando novo teste."); }

    @AfterEach
    void tearDownCadaTeste() { operacoes = null; System.out.println("  - Teste finalizado. Limpeza realizada."); }

    @AfterAll
    static void tearDownGlobal() { System.out.println("<<< Todos os testes concluídos."); }

    @Test
    @DisplayName("Deve verificar a adição de dois números positivos")
    void testAdicaoDePositivos() {
        assertEquals(5, operacoes.adicionar(2, 3), "2 + 3 deve ser 5");
    }

    @Test
    @DisplayName("Deve lançar exceção ao dividir por zero")
    void testDivisaoPorZero() {
        assertThrows(IllegalArgumentException.class, () -> operacoes.dividir(10, 0));
    }
}
```

### Vantagens do JUnit 5:

  * **Modularidade e Extensibilidade:** Maior flexibilidade e facilidade para criar extensões.
  * **Melhor Suporte a Java 8+:** Aproveita recursos modernos do Java.
  * **Nomeação Flexível:** `@DisplayName` melhora a legibilidade dos relatórios.
  * **Testes Parametrizados:** Reduz duplicação de código ao testar diferentes entradas.

-----

## 📝 Padrões de Nomes para Métodos de Teste

Um padrão de nome consistente é crucial para a **clareza, legibilidade e manutenibilidade** dos testes. O nome do método deve descrever o cenário e o resultado esperado.

### Padrões Recomendados:

1.  **`shouldDoSomethingWhenSomethingHappens()` (BDD Simplificado)**

      * **Formato:** `should<ComportamentoEsperado>When<Condição/Cenário>()`
      * **Exemplos:** `shouldReturnSumOfTwoNumbers()`, `shouldThrowExceptionWhenDividingByZero()`.
      * **Vantagens:** Altamente legível e autoexplicativo.

2.  **`given_when_then()` (Formato BDD Explícito)**

      * **Formato:** `given<PreCondicao>_when<Acao>_then<ResultadoEsperado>()`
      * **Exemplos:** `givenTwoPositiveNumbers_whenAddingThem_thenReturnsCorrectSum()`, `givenZeroAsDenominator_whenDividing_thenThrowsIllegalArgumentException()`.
      * **Vantagens:** Extremamente claro e alinhado com requisitos de negócio.

3.  **`test<NomeDaFuncionalidade><Cenário>` (Tradicional)**

      * **Formato:** `test<NomeDaFuncionalidade><CenárioEspecial>()`
      * **Exemplos:** `testAdicaoNumerosPositivos()`, `testDivisaoPorZero()`.
      * **Vantagens:** Conciso e fácil de usar.

### Convenções Adicionais e Dicas:

  * **Nomes Descritivos para Classes de Teste:** `ClasseSendoTestadaTest`.
  * **Use `@DisplayName` (JUnit 5):** Complementa o nome do método com uma descrição mais amigável para relatórios.
  * **Seja Específico:** Evite nomes vagos.
  * **Um Cenário por Teste:** Cada método de teste deve cobrir um único comportamento ou cenário.

-----

## 💥 `assertThrows()`: Validando Exceções em Testes

O `assertThrows()` é um método crucial do `org.junit.jupiter.api.Assertions` no JUnit 5, usado para verificar se um trecho de código **lança uma exceção esperada**.

### O Que Ele Faz?

Executa o código fornecido (geralmente uma expressão lambda) e verifica:

1.  Se uma exceção do tipo especificado (ou uma de suas subclasses) é lançada.
2.  Se nenhuma outra exceção inesperada é lançada.

### Sintaxe e Uso:

```java
assertThrows(ExpectedExceptionType.class, () -> {
    // Código que espera-se que lance a exceção
});

// Para capturar a exceção e fazer asserções adicionais:
ExpectedExceptionType excecaoCapturada = assertThrows(ExpectedExceptionType.class, () -> {
    // Código que espera-se que lance a exceção
});
assertEquals("Mensagem de erro esperada", excecaoCapturada.getMessage());
```

### Exemplo Prático com `Calculadora`:

```java
// Classe de Teste JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    @Test
    void deveLancarExcecaoAoDividirPorZero() {
        Calculadora calc = new Calculadora();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(10, 0); // Este código deve lançar IllegalArgumentException
        });
    }

    @Test
    void deveLancarExcecaoEVerificarMensagemAoDividirPorZero() {
        Calculadora calc = new Calculadora();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(15, 0);
        });
        assertEquals("Divisão por zero não é permitida!", excecao.getMessage());
    }
}
```

### Por Que e Quando Usar `assertThrows()`?

  * **Testando Comportamentos de Erro:** Essencial para validar como seu código reage a situações de erro (os "caminhos infelizes").
  * **Validação de Entrada:** Garante que métodos lancem exceções para entradas inválidas.
  * **Alternativa Limpa ao `try-catch`:** No JUnit 5, é a forma preferida de testar exceções em comparação com blocos `try-catch` manuais.

-----

## 🧮 `ArithmeticException.class`: Exceção Aritmética

`ArithmeticException` é uma **exceção não verificada (unchecked)** em Java (`java.lang`), lançada para indicar uma condição aritmética excepcional, sendo a mais comum a **divisão de um número inteiro por zero**.

### Onde e Por Que Ocorre?

  * **Divisão por Zero de Inteiros:** `int resultado = 10 / 0;`
      * **Atenção:** Divisão por zero com `float` ou `double` resulta em `Infinity` ou `NaN`, não em `ArithmeticException`.
  * **Operação Módulo por Zero:** `int resto = 10 % 0;`

### Usando `ArithmeticException.class` com `assertThrows()`

É comum usar `assertThrows(ArithmeticException.class, ...)` em testes para verificar se o código lança essa exceção em cenários inválidos, como a divisão inteira por zero.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Calculadora {
    public int dividir(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("Não é possível dividir por zero!");
        }
        return numerador / denominador;
    }
}

public class ExemploArithmeticExceptionTest {

    @Test
    void deveLancarArithmeticExceptionAoDividirInteiroPorZero() {
        Calculadora calc = new Calculadora();
        ArithmeticException excecao = assertThrows(ArithmeticException.class, () -> {
            calc.dividir(20, 0);
        });
        assertEquals("Não é possível dividir por zero!", excecao.getMessage());
    }

    @Test
    void naoDeveLancarExcecaoParaDivisaoValida() {
        Calculadora calc = new Calculadora();
        assertEquals(5, calc.dividir(10, 2));
    }
}
```

-----

🏗️ Padrão AAA (Arrange, Act, Assert) e Cleanup
O padrão AAA define os três estágios essenciais de um bom teste unitário (ou de integração), garantindo que cada teste seja focado, claro e fácil de entender. O Cleanup é um estágio adicional (geralmente implícito ou gerenciado pelo framework) para garantir a independência dos testes.

1. Arrange (Arranjar / Preparar)
O que fazer: Configurar o cenário para o teste.

Inicializar objetos necessários.

Criar dados de entrada (inputs).

Configurar mocks ou stubs para dependências externas.

Definir o estado inicial do sistema/objetos.

Objetivo: Ter tudo pronto para a ação do teste.

Exemplo:

Java

// Arrange
Calculadora calculadora = new Calculadora(); // Objeto a ser testado
int num1 = 5;
int num2 = 3;
2. Act (Agir / Executar)
O que fazer: Executar a ação principal que está sendo testada.

Chamar o método da unidade de código que você quer verificar.

Normalmente, há apenas uma chamada principal neste estágio.

Objetivo: Desencadear o comportamento que você deseja validar.

Exemplo:

Java

// Act
int resultado = calculadora.somar(num1, num2); // Chama o método testado
3. Assert (Verificar / Afirmar)
O que fazer: Verificar o resultado da ação.

Comparar o resultado com o esperado.

Verificar o estado dos objetos após a ação.

Confirmar interações com mocks.

Usar asserções (assertEquals(), assertTrue(), assertThrows(), etc.).

Objetivo: Validar que o código se comportou como esperado.

Exemplo:

Java

// Assert
assertEquals(8, resultado, "A soma de 5 e 3 deve ser 8"); // Verifica o resultado
4. Cleanup (Limpar / Desmontar) - Opcional
O que fazer: Limpar recursos ou estados criados que possam afetar testes subsequentes.

Redefinir estado de objetos compartilhados.

Fechar conexões (banco de dados, rede).

Excluir arquivos temporários.

Objetivo: Garantir que cada teste seja independente e que o ambiente esteja limpo para o próximo teste.

No JUnit: É geralmente feito em métodos anotados com @AfterEach (para limpeza por teste) ou @AfterAll (para limpeza global da classe).

Exemplo Completo no JUnit 5 com AAA:
Java

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable; // Para o exemplo de Act em assertThrows

class Calculadora {
    public int somar(int a, int b) { return a + b; }
    public int dividir(int a, int b) {
        if (b == 0) { throw new ArithmeticException("Não é possível dividir por zero!"); }
        return a / b;
    }
}

@DisplayName("Testes da Calculadora - Padrão AAA")
class CalculadoraAAATest {

    private Calculadora calculadora;

    @BeforeEach // [Arrange] Setup comum para cada teste
    void setup() {
        calculadora = new Calculadora();
        System.out.println("  [Arrange] Calculadora inicializada.");
    }

    @AfterEach // [Cleanup] Limpeza após cada teste
    void teardown() {
        calculadora = null;
        System.out.println("  [Cleanup] Calculadora resetada.");
    }

    @Test
    @DisplayName("Deve somar dois números inteiros positivos")
    void deveSomarDoisNumerosPositivos() {
        // Arrange (específico do teste, se houver, mas a calculadora já está arranjada)
        int num1 = 5;
        int num2 = 3;

        // Act
        int resultado = calculadora.somar(num1, num2);

        // Assert
        assertEquals(8, resultado, "A soma de 5 e 3 deve ser 8");
        System.out.println("    [Assert] Soma verificada. Teste de somar PASSOU.");
    }

    @Test
    @DisplayName("Deve lançar ArithmeticException ao dividir por zero")
    void deveLancarExcecaoAoDividirPorZero() {
        // Arrange (a calculadora já está arranjada)
        int numerador = 10;
        int denominador = 0;

        // Act (encapsulado na lambda para assertThrows)
        Executable divisaoPorZero = () -> calculadora.dividir(numerador, denominador);

        // Assert
        assertThrows(ArithmeticException.class, divisaoPorZero, "Deve lançar ArithmeticException ao dividir por zero");
        System.out.println("    [Assert] Exceção de divisão por zero verificada. Teste de exceção PASSOU.");
    }
}
Por Que Usar o Padrão AAA?
Legibilidade: Torna o código de teste mais fácil de ler e entender, com intenção clara em cada seção.

Foco: Ajuda a garantir que cada teste tenha um único propósito, evitando testar muitas coisas ao mesmo tempo.

Manutenibilidade: Facilita a identificação de problemas quando um teste falha e simplifica modificações ou adições de novos testes.

Uniformidade: Promove um estilo consistente de escrita de testes em toda a equipe ou projeto.

Adotar o padrão AAA é uma das melhores práticas para escrever testes eficientes e robustos, garantindo a qualidade do seu software.



