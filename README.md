# Avalia-o2Compasso

Optei por realizar os dois programs em um so gerenciando através dos pacotes é reaproveitando codigo, conexão com banco de dados e tratamentos de exeções personalizada. O programa foi feito em mysql localhost a conexão esta setada em user: "root", passaword:"root" 

### Script database
#### CREATE DATABASE AVALIACAO

### Script tabela Emoticons
#### <br> CREATE TABLE EMOTICONS( <br/>
#### <br> ID_EMOTICONS SMALLINT PRIMARY KEY AUTO_INCREMENT, <br/>
#### <br> FRASE VARCHAR(200), <br/>
#### <br> EXPRESSAO VARCHAR(45) <br/>
#### <br> ) <br/>
### Script tabela Produto
#### <br> CREATE TABLE PRODUTO(  <br/>
#### <br> id SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,  <br/>
#### <br> nome VARCHAR(200) NOT NULL,  <br/>
#### <br> desrição VARCHAR(200) NOT NULL,  <br/>
#### <br> desconto FLOAT NOT NULL,  <br/>
#### <br> preço FLOAT NOT NULL,  <br/>
#### <br> data_inicio VARCHAR(15) NOT NULL  <br/>
#### <br> )  <br/>
### Gerenciamento de dependências
#### <br> MAVEN <br/>
Achei melhor em realizar um poll de conexão ou seja estou usando as dependencias c3p0 e o mchange
  
  
