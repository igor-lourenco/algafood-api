## algafood-api

Comando para gerar imagem da aplicação:

```
rm algafood-api.tar; docker image rm algafood-api; mvn package && docker image build -t algafood-api . && docker save -o algafood-api.tar algafood-api && scp algafood-api.tar igor@<ip>:/home/igor && scp docker-compose.yaml igor@<ip>:/home/igor
```