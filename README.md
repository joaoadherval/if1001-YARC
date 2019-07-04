## Projeto para a cadeira IF1001 - Programação 3
### Programação para Dispositivos Móveis com Android - Centro de Informática - UFPE
Disciplina ministrada pelo professor Leopoldo Mota Teixeira (lmt@cin.ufpe.br)

#### O Projeto - YARC (Yet Another Reddit Client)

Esse projeto visa aplicar os conhecimentos adquiridos em sala de aula, na implementação de um cliente para o Reddit. A plataforma é um local amplamente usado por jovens e adultos mundo afora, para espalhar conhecimento, opniões, notícias, entre outras coisas. Formatado em threads, onde múltiplus usuários engajam numa mesma publicação (ou post). 

Dessa forma, com esse cliente, os usuários podem acessar de maneira mais confortável e agradável, posts highlights, trending, ou fazer uma busca específica, de forma rápida, simples e objetiva, com alguns cliques, podendo utilizar em seu tempo livre, durante algum período de espera (seja ele longo ou curto), sem precisar acessar o navegador via telefone para tal.

Existem alguns aplicativos pelas lojas virtuais que já fazem isso, porém o objetivo não é trazer algo inovador, mas algo que agregue para o usuário. Afinal, nada melhor do que sentir prazer em utilizar uma alicação com um design agradável, e que nos deixe a vontade durante o tempo de uso.

A intenção é ter uma tela de autenticação (login), uma tela principal com os itens (posts) mais atuais, cada um com uma breve descrição (ou início do conteúdo), imagem se existir, número de replies, avaliações, etc; um campo de busca, uma tela de settings, e filtros.

Por: João Adherval Carvalho de Barros (jacb@cin.ufpe.br)

#### Finalização do Projeto

A ideia inicial do projeto, que seria permitir o login, navegação dentre duas funcionalidades de tela, entre elas uma com os top posts no momento, e outra listando posts pela localização do usuário. Não foi encontrado na documentação da api uma chamada que permitisse essa filtro por localização na chamada.

Logo, a ideia seguinte, seria oferecer pelo menos um campo de pesquisa para o usuário poder utilizar. Não foi possível também, pois durante o desenvolvimento, encontrou-se problemas com a implementação usando RxAndroid e RxJava, juntamente com Dependency Injection com o Dagger 2, e as tarefas de forma assíncrona. Depois de muita pesquisa, a melhor solução encontrada para resolução desse problema foi com a utilização de Coroutines junto com Dependency Injection e o descarte do RxAndroid e RxJava.

Dessa forma, o projeto ficou estruturado da sequinte maneira:

O NewsFragment é responsável por mostrar os últimos posts na tela, usando um RecyclerView, com seu layout definido em resources. Além disso é utilizado aqui (assim como em alguns outros locais) o recurso lazy de Android.

Seguindo o padrão de Delegate Adapter, foi implementado um NewsAdapter, para cada ViewType ser associado a um Delegate Adapter, de acordo com os dois modelos existentes na aplicação, que nesse caso é uma news, ou um loading news.

De acordo com o JSON retornado da api do Reddit, foram criadas duas classes base: ReddidNews e RedditNewsChild, para que a resposta possa ser parseada e utilizada de maneira adequada. 

Hà também uma interface e classes para fazer a chamada para a API Reddit, RestAPI e RedditAPI por exemplo, e um NewsViewModel que controla todo o fluxo de chamadas de API e respostas retornadas por elas.

Para a dependency injection, foram adicionados as anotações devidas, além da criação dos devidos modulos para prover as instâncias necessárias pro funcionamento, todas no pacote "di".

Por fim, Coroutines estão sendo utilizadas em classes como modelos de di e o NewsViewModel que gerencia as chamadas da API.
