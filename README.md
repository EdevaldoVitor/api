# Projeto Java Spring Boot - API com Autenticação JWT

Este é um projeto desenvolvido em Java 21 com Spring Boot, utilizando um banco de dados H2 embutido, sem a necessidade de instalação externa. O projeto foi criado com o objetivo de implementar uma API que utiliza autenticação via JWT e controle de acesso com base em permissões de usuários.

# Como Rodar o Projeto

$ git clone https://github.com/EdevaldoVitor/api.git
$ cd /repositorio
$ mvn spring-boot:run

A aplicação será iniciada na URL: http://localhost:8080
O banco de dados H2 estará disponível em http://localhost:8080/h2-console, com as configurações padrão de acesso (usuário: sa, senha:)

# Detalhes Técnicos

Autenticação JWT
A aplicação utiliza JSON Web Tokens (JWT) para autenticação de usuários. Quando um usuário se autentica, um token JWT é gerado e deve ser incluído no cabeçalho das requisições subsequentes para acessar endpoints protegidos.

Endpoint de create: /auth/register
Endpoint de login: /auth/login

Roles e Permissões
A aplicação possui dois tipos de roles:

ADMIN: Acesso total à aplicação.
USER: Acesso restrito, com algumas funcionalidades limitadas.

Controle de Usuários
Não é permitido cadastrar usuários com o mesmo email. Caso tente registrar um usuário com um email duplicado, o sistema retornará um erro.

# Decisões Técnicas Importantes
Autenticação JWT: A escolha por JWT foi feita para garantir que as requisições sejam seguras, usando um token que pode ser verificado em cada acesso, sem a necessidade de manter sessões no servidor.

Roles (ADMIN/USER): Para garantir uma segregação de permissões e funcionalidades, criei dois perfis de usuário. Isso possibilita um controle mais granular sobre quem pode acessar certas rotas e realizar determinadas ações.

Banco H2: Utilizei o H2 como banco de dados embutido, o que facilita o uso e a configuração do projeto sem a necessidade de uma instalação adicional. Ele é ideal para testes e desenvolvimento, mas pode ser facilmente substituído por outro banco em um ambiente de produção, se necessário.



# Explicação do Front-End
Por que não foi feito o front-end:

Não implementei o front-end neste projeto porque minha experiência é mais consolidada no desenvolvimento back-end utilizando Java. Embora eu tenha conhecimento em Java Server Faces (JSF) para a criação de interfaces simples em Java, meu conhecimento em JavaScript e frameworks modernos de front-end (como React, Angular ou Vue) é limitado. Por essa razão, optei por focar na parte de back-end e segurança do sistema, deixando a parte front-end de fora.

Considero que este projeto pode ser avaliado como um teste completo de back-end e segurança. Estou aberto a colaborar com a implementação do front-end em uma fase futura, caso necessário, ou a integrar a solução com um front-end desenvolvido por outra pessoa.
