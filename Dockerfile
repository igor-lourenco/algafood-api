# imagem base para contruir o container
FROM openjdk:11-jre-slim

# define o diretorio onde as instrucoes e comandos abaixo irao rodar dentro do container
# Todos os comandos subsequentes (RUN, CMD, ENTRYPOINT, COPY, ADD) serão executados nesse diretorio
WORKDIR /app

# argumento que esta no <JAR_FILE> do pom.xml mas nao esta sendo usado
ARG JAR_FILE

# Copia o arquivo JAR do diretorio target do seu sistema host para o diretorio /app dentro do container
#  e renomeia-o para algafood-api.jar. O uso de target/*.jar permite copiar qualquer arquivo JAR presente no diretorio target.
COPY target/*.jar /app/algafood-api.jar

# Copia o arquivo wait-for-it.sh do diretorio local (onde está o Dockerfile) para o diretorio raiz do sistema de arquivos no container Docker.
# Essencialmente, ele coloca uma cópia do arquivo no container.
COPY wait-for-it.sh /wait-for-it.sh

# Esse comando altera as permissoes do arquivo 'wait-for-it.s'h dentro do container para torna-lo executavel.
RUN chmod +x /wait-for-it.sh

# Informa o Docker que o container escutara na porta 8080. Isso nao publica a porta para fora do container, mas serve
# como uma documentacao e pode ser usado por outros comandos do Docker para configurar o mapeamento de portas.
EXPOSE 8080

# Define o comando padrao que sera executado quando o container iniciar.
# Neste caso, o comando para rodar a aplicação Java (java -jar algafood-api.jar).
CMD ["java", "-jar", "algafood-api.jar"]

# ============================================================
# Comando pra gerar imagem da aplicacao:
# docker image build -t algafood-api-v2 .