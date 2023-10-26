# Desafio techlead - Thayssa Lima 👩‍💻

-**Cargo** : Desenvolvedor Back-end.

-**Senioridade** : Pleno.

-**Linguagem escolhida** : Java para api, css, html e javascript utilizando o angular para front-end.

-**Desafio concluído até o nível 2**

-**Frameworks** : Foram utilizados os frameworks spring boot para a api e o angular para o front-end. Frameworks utilizados por já ter uma certa familiriedade e por serem os utilizados na empresa que propõe o desafio.

-**Tecnologias** : Java 8, sprint boot, angular, postgres, flyway, spring security, jwt, swagger.

-**Encriptação de propriedades** :
Para armazenar informações sigilosas, como a senha do usuário admin master, foi utilizada a biblioteca jasypt, que encripta a informação de forma que o código compilado consiga descriptar.
Senha utilizada para encriptação: axshwa.
É necessário passar na execução da api a propriedade jasypt.encryptor.password=axshwa, que pode ser passada como um environment JASYPT_PASSWORD.

-**Flyway** :
Para a criação das tabelas no banco de dados, foi utilizada a biblioteca flyway, um versionador que executa scripts ao iniciar a aplicação (desafio-api). Para sua execução, é necessário criar os scripts em arquivos no formato SQL dentro da pasta db/migration.

-**Dados banco de dados** : 
Foi utilizado o banco H2 no modo Postgres, porém as configurações de banco podem ser passadas no momento da execução da aplicação através de environments:

DB_URL - URL de conexão com o banco de dados.

DB_DRIVER_CLASSNAME - Classe do Driver utilizado.

DB_USERNAME - Usuário de acesso ao banco de dados.

DB_PASSWORD - Senha de acesso ao banco de dados.

-**Cadastros criados ao rodar aplicação** : 
Os usuários com perfil administrador e bibliotecário estão sendo criados no momento que a aplicação é iniciada através de um CommandLineRunner do java.
Administrador - Cpf:36174272001 Senha: 1234.
Bibiotecário - Cpf:61182391001  Senha: 1234.

-**Como rodar a aplicação ao baixar**:
Estão separadas as pastas em desafio-api (back-end) e desafio-web (front-end). Para rodar o desafio-api é necessário que dentro da pasta seja rodado o comando mvn spring-boot:run, com isso é instalado o spring boot na máquina. Já no desafio-web, é preciso rodar o comando npm install.
