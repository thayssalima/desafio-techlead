# Desafio techlead - Thayssa Lima 👩‍💻

-**Cargo** : Desenvolvedor Back-end

-**Senioridade**: Pleno (Desafio concluído até o nível 2)

-**Tecnologias** : Java 8, sprint boot, angular, postgres, flyway, spring security jwt.

-**Encriptação de propriedades**:
Para armazenar informações sigilosas, como a senha do usuário admin master, foi utilizada a biblioteca jasypt, que encripta a informação de forma que o código compilado consiga descriptar.
Senha utilizada para encriptação: axshwa
É necessário passar na execução da api a propriedade jasypt.encryptor.password=axshwa.

-**Flyway**:
Versionador de banco de dados.  

-**Dados banco de dados**: 
Foi necessário criar um banco localmente para a excução do projeto ,utilizou- se o postgres local com as seguintes informações :
spring.datasource.url=jdbc:postgresql://localhost:5432/techlead
spring.datasource.username=ENC(RkgqNMhjBj8ZBy8C09ifpMFNGd1GLmKT) /Username descriptogrador: postgres
spring.datasource.password=ENC(wtFPkxInv5qwmABG9x+nHw==) /Senha descriptografada: 1234





