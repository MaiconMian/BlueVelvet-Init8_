# BlueVelvet-Init8
Repositório oficial da equipe Init8_ para o desenvolvimento do projeto "Blue Velvet Music Store", criado como parte da disciplina de Gestão do Ciclo de Vida da Aplicação.

# Requisitos
Para a utilização completa da aplicação, é necessário ter instalado:
- Java 17 ou superior
- [docker-compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/install.html)

# Utilização

Para utilização da aplicação, é necessário clonar o repositório:
```bash
git clone https://github.com/MaiconMian/BlueVelvet-Init8
cd BlueVelvet-Init8
```

É possível customizar a execução da aplicação com variáveis de ambiente que estão presentes no arquivo **.env**:

```
MYSQL_DATABASE=${MYSQL_DATABASE:-mydatabase}        # Nome da DB
MYSQL_PASSWORD=${MYSQL_PASSWORD:-rootpassword}      # Senha do usuário root da DB
DB_PORT=${DB_PORT:-3309}                            # Porta do MySQL
SPRING_PORT=${SPRING_PORT:-8090}                    # Porta do Spring (hosteando back-end)
NGINX_PORT=${NGINX_PORT:-8080}                      # Porta do Nginx (hosteando front-end)
```

Após isso, basta rodar o script responsável por compilar a aplicação e subir os contâiners:

### Linux
```bash
./run.sh
```

### Windows
```bash
.\run.ps1
```

Com os contâiners em execução, é possível acessar o servidor Spring Boot em `http://localhost:$SPRING_PORT` e o servidor Nginx em `http://localhost:$NGINX_PORT`.

# Modelagem
![DER](https://github.com/MaiconMian/BlueVelvet-Init8/blob/main/DER.png)
