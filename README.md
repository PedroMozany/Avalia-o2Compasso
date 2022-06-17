# Avalia-o2Compasso

<p> Optei por realizar os dois programs em um so gerenciando através dos pacotes é reaproveitando codigo, conexão com banco de dados e tratamentos de exeções personalizada. <p/>
<p>O programa foi feito em mysql localhost a conexão esta setada em user: "root", passaword:"root"<p/>
<h2>Script database<h2/>
<p>CREATE DATABASE AVALIACAO<p/>
<h2>Script tabela Emoticons<h2/>
CREATE TABLE EMOTICONS(
ID_EMOTICONS SMALLINT PRIMARY KEY AUTO_INCREMENT, 
FRASE VARCHAR(200),
EXPRESSAO VARCHAR(45)
)
<h2>Script tabela Produto<h2/>
  
<br>CREATE TABLE PRODUTO(<br/>
<br>id SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,<br/>
<br>nome VARCHAR(200) NOT NULL,<br/>
<br>desrição VARCHAR(200) NOT NULL,<br/>
<br>desconto FLOAT NOT NULL,<br/>
<br>preço FLOAT NOT NULL,<br/>
<br>data_inicio VARCHAR(15) NOT NULL<br/>
<br>)<br/>
  
<h1>Gerenciamento de dependências<h1/>
<l>MAVEN<l/>
<p>Achei melhor em realizar um poll de conexão ou seja estou usando as dependencias c3p0 e o mchange<p/>
  
  
