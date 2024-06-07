<h1> Desafio Backend Encurtador de URLs - TDS Company 🚀 </h1>

Para executar a aplicão, siga os seguintes tópicos: 
- execute a aplicação
- Por padrão, ela será executada no: http://localhost:8080
- `Utilize o Postman`
- No Postman, escolha o método POST e URL: http://localhost:8080/shorten
- O header: 'key: Content-Type,  vallue: application/json'
- o body da solicitação: {
  "originalUrl": "https://url-que-voce-escolheu"
}
- aperte Send
- a solicitação virá nesse escopo: {
  "id": 1,
  "originalUrl": "https://url-que-voce-escolheu",
  "shortUrl": "abcdef",
  "createdAt": "2024-06-07T10:17:23",
  "accessCount": 0
}
- Para acessar a URL encurtada no navegador: Pegue o valor do campo "shortUrl" (no exemplo, "abcdef") da resposta.
- Abra o navegador e coloque a URL: http://localhost:8080/abcdef, que irá lhe redirecionar para o site da url original
- `Para ver as estatísticas`
- Faça uma nova solicitação no Postman, com o método GET e a url encurtada (no exemplo que dei: http://localhost:8080/abcdef)
- Clique em send
- A resposta virá assim : {
  "originalUrl": "https://url-que-voce-escolheu",
  "shortUrl": "abcdef",
  "createdAt": "2024-06-07T10:15:30",
  "accessCount": 2,
  "averageAccessPerDay": 1.67
}
- accessCount: = é o número total de vezes que a URL encurtada foi acessada
- averageAccessPerDay =  A média de acessos por dia desde que a URL foi criada
- createdAt = A data e hora em que a URL foi encurtada.







