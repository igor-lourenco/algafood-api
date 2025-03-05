## algafood-api

Comando para gerar imagem da aplicação:

```
rm algafood-api.tar; docker image rm algafood-api; mvn package && docker image build -t algafood-api . && docker save -o algafood-api.tar algafood-api && scp algafood-api.tar igor@<ip>:/home/igor && scp docker-compose.yaml igor@<ip>:/home/igor
```

Comando para rodar imagem na maquina remota:
```
docker image rm algafood-api; docker image rm -f algafood-proxy; docker image rm -f algafood-api-algafood-redis-compose-1; sudo mv docker-compose.yaml /opt/algafood-api; docker load -i algafood-api.tar && docker compose -f /opt/algafood-api/docker-compose.yaml up -d --scale algafood-api-compose=2 && docker compose -f /opt/algafood-api/docker-compose.yaml logs --follow
```
