# Desafio techlead - Thayssa Lima 👩‍💻

-**Cargo** : Desenvolvedor Back-end

-**Senioridade**: Pleno

-**Tecnologias** : Java 8, sprint boot, angular, postgres, flyway, spring security jwt.

-**Encriptação de propriedades**:
Para armazenar informações sigilosas, como a senha do usuário admin master, foi utilizada a biblioteca jasypt, que encripta a informação de forma que o código compilado consiga descriptar.
Senha utilizada para encriptação: axshwa
É necessário passar na execução da api a propriedade jasypt.encryptor.password=axshwa

-**Variáveis de ambiente**:
Poderão ser passadas variáveis no momento da execução da aplicação:
API:

DB_URL - URL de conexão com o banco de dados.

DB_USERNAME - Usuário de acesso ao banco de dados.

DB_PASSWORD - Senha de acesso ao banco de dados.

ADMIN_PASSWORD - Senha do usuário admin master do sistema, Padrão 1234.

-**Flyway**:
Versionador de banco de dados  




