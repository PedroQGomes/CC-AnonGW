# MIEI - 3ºAno - Comunicações por Computador

## Projeto AnonGW

O trabalho pratico proposto consiste em desenhar e implementar uma rede Overlay de anonimização do originador.Os clientes
de origem e os servidores de destino não são implementados visto que são genéricos (ex: clientes e servidores HTTP). A rede de anonimização recebe pedidos TCP vindos
dos clientes e pedidos UDP vindos dos seus pares. Dado isto é desenhado um protocolo para funcionar sobre UDP que garanta a
entrega ordenada e a encriptação dos dados de uma ou mais conexões de clientes.Para a implementação do trabalho foi usada a linguagem **Java**


## Compilação e Testes

É aconselhável o uso de um **IDE** para a a compilação e execução do projeto. No caso de escolher o **intellij** é simplesmente criar um projeto com a opção de "from existing sources" e escolher a diretoria do repositório. A classe que contem o método main e que por isso inicializa o programa é a **AnonGW**.
Para testar é aconselhável o uso da topologia CORE com varias instâncias das rede de anonimização e um servidor mini-http.