# Projeto-Web-II

### AQUÁRIO DE NATAL
 
O turismo é caracterizado como um importante fenômeno que resulta na geração de emprego e renda. Mas para que isso ocorra, o mesmo tem de ser realizado de uma maneira planejada e responsável, o que permite ampliar a valorização da imagem de um determinado local de destino, motivar a responsabilidade em torno da preservação ambiental e cultural, aumentar a qualidade de vida da população, promover o intercâmbio cultural e a inclusão social (Neves, 2013). Nessa perspectiva, a empresa privada Aquário Natal foi fundada em Janeiro de 1999, com finalidade de ampliar o cuidado com a fauna e promover o trabalho voltado à conscientização ambiental, e para que isso seja possível a empresa proporciona um espaço de visitação de instituições educacionais públicas e privadas de ensino fundamental/superior. 

Carvalho(2022), afirma que o aquário é uma ferramenta de grande relevância turística e para poder desempenhar o seu real papel de educador ambiental se faz necessário ações de inovação. Mas para que isso seja possível, sugerimos desenvolver uma aplicação funcional, na qual possa realizar as seguintes funções: 

- [ ] __*Cadastro de funcionários*__ 
- [ ] __*Registrar os moradores do aquário*__ 
- [ ] __*Agendar visitações*__ 
- [ ] __*Cadastrar palestras sobre educação ambiental*__ 
- [ ] __*Receber moradores temporários para reabilitação*__ 
- [ ] __*Cadastrar candidatos a potenciais vagas de estágio/trabalho*__

### CONFIGURAÇÕES
## ![Tools](https://img.shields.io/badge/Tools-0078D4?style=for-the-badge&logo=visual-studio-code&logoColor=white)

- Baixe o MySQL Installer: __*https://dev.mysql.com/downloads/installer/*__

- Execute o instalador: Selecione MySQL Server e MySQL Workbench.

- Configure a senha do usuário __*root*__.

- Verifique pelo terminal se o mysql foi instalado:

      mysql -u root -p

## ![Banco de Dados](https://img.shields.io/badge/Banco%20de%20Dados-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

- Abra o MySQL Workbench.
- Crie um novo Schema no servidor e nomei como: springboot2 

      use springboot2;

## ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

No projeto Spring Boot temos que configurar a conexão com o MySQL:

__*application.properties*__

    spring.application.name=projetoweb2

    spring.datasource.url=jdbc:mysql://localhost:3306/springboot2
    spring.datasource.username=root
    spring.datasource.password=senha 
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    spring.jpa.hibernate.ddl-auto=update

__*Para iniciar a API:*__

- Import o projeto: Utilize o Eclipse IDE
- Para visualizar funcionando:

      http://localhost:8080

__*Entidades:*__

- __*Usuarios:*__ 

      http://localhost:8080/usuarios

- __*Funcionários:*__

      http://localhost:8080/funcionarios

- __*Palestras:*__

      http://localhost:8080/palestras

- __*Visitas:*__

      http://localhost:8080/visitas

### REFERÊNCIA

__*Neves, T. R. TURISMO E ACESSIBILIDADE: um estudo nos equipamentos turísticos natalenses, aquário Natal e Fortaleza dos Reis Magos com base na Associação Brasileira de Normas Técnicas (ABNT). Trabalho de Conclusão de Curso em Turismo, UFRN, Natal, RN, 2013.*__

__*Carvalho, A.V. L. Aquários como atrativo turístico e ferramenta de educação ambiental: um estudo na cidade de Natal - RN. Trabalho de Conclusão de Curso em Turismo, UFRN, Natal, RN, 2022.*__

__*Disponível em:<Aquário Natal>. Acesso em: 10 de Outubro de 2025.*__ 
