# BlueVelvet-Init8_
Repositório oficial da equipe Init8_ para o desenvolvimento do projeto "Blue Velvet Music Store", criado como parte da disciplina de Gestão do Ciclo de Vida da Aplicação.

# Requisitos
Para a utilização completa da aplicação, é necessário ter instalado:
- Java 17 ou superior
- [docker-compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/install.html)

# Utilização

Para utilização da aplicação, é necessário clonar o repositório:
```bash
git clone https://github.com/MaiconMian/BlueVelvet-Init8_
cd BlueVelvet-Init8_
```

Após isso, basta rodar o script **run.sh**, responsável por compilar a aplicação e subir os contâiners docker:

```bash
./run.sh
```

Com os contâiners em execução, é possível acessar o servidor Spring Boot em `localhost:8090` e o servidor HTTP usado no front-end em `localhost:8080`.

# Modelagem
![DER](https://github.com/MaiconMian/BlueVelvet-Init8_/blob/main/DER.png)
