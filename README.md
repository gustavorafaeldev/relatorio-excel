# Relatórios Excel

Esta API gera relatórios de contatos em um arquivo excel.

Relatórios detalhados, onde cada grupos de contatos são separados por colunas no excel.

Relatórios únicos ou simplificados, onde todos os contatos são inseridos em uma mesma coluna.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.


### 📋 Pré-requisitos

De que coisas você precisa para instalar o software e como instalá-lo?

```
Java JDK instalado na máquina.
IDE (usei o Intellij).
MongoDB.
```
Na pasta raiz do projeto contém os arquivos JSON (com 70.000 contatos) para você importar no Mongo
.
## ⚙️ Exemplo de uso

Aqui estamos usando a biblioteca Apache POI para gerar a tabela em Excel, essa biblioteca 
possui três motores de geração de arquivos do tipo xls ou xlsx, que são HSSF, XSSF, SXSSF.

HSSF gera arquivos do tipo xls, que são tipos de arquivos excel mais antigos.

XSSF diferente do HSSF, gera arquivos do tipo xlsx, que são mais atuais.

SXSSF pra mim foi o que funcionou com melhor desempenho, ele também gera arquivos do tipo xlsx
porém ele consome menos memória ram.

### 🔩 Requisição via POSTMAN



```
Você precisa usar o postman para realizar a requisição com o método POST, passando a campanha e 
as datas separadas por vírgula, ex:(14-12-2022, 15-12,2022).
```
![postman_publi](https://user-images.githubusercontent.com/105467235/215152830-c1fdbae0-d857-4db7-bcf4-7efcbcb29514.png)

### 🔩 Gráficos de consumo de memória

A imagem abaixo contém o gráfico mostrando o consumo de memória com os dois motores que eu citei 
anteriormente.

```
O gráfico demonstra o consumo de memória para o mesmo volume de dados. 
Mais de 70.000 contatos.
Se você precisa gerar um relatório em Excel com um grande volume de dados. Com certeza essa biblioteca 
vai te ajudar.
```
![grafico_publi](https://user-images.githubusercontent.com/105467235/215152800-546b5fe6-e701-4b4b-ab2a-518027449c4e.png)
## 🛠️ Construído com

* [Java](https://www.java.com/pt-BR/) - A linguagem de programação usada.
* [Spring Framework](https://spring.io/projects/spring-framework) - O framework usado.
* [Apache POI](https://rometools.github.io/rome/) - A biblioteca usada para gerar o arquivo excel.
* [Mongodb](https://www.mongodb.com/try/download/community) - O banco de dados usado.

## ✒️ Autores

* **Gustavo Rafael** - [gustavorafaeldev](https://github.com/gustavorafaeldev)

## 🎁 Expressões de gratidão

* Conte a outras pessoas sobre este projeto 📢;
* Um agradecimento publicamente 🫂;
* etc.


---
⌨️ com ❤️ por [Gustavo Rafael](https://github.com/gustavorafaeldev) 😊
