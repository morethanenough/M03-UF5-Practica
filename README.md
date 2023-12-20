# M03-UF5-Practica
## Resum
Farem una aplicació que serà un joc de pedra, paper i tisora. La idea és fer un mínim de dues escenes a les que en una recollirem les dades del jugador amb un formulari, una segona pantalla que serà on es durà a terme l'acció de la partida.
## Classes
### PJ
Farem una classe abstracte de la que sortiran la classe *jugador* i *"monstre"* (com a origen tot i que ja sabem el que vam parlar.
### Monstre
S'haurà d'implementar un mètoda que mitjançant un random esculli pedra, paper o tisores.
Tindrà uns atributs que seran el nom, frase_de_victoria i frase_de_derrota (com a mínim). Ampliar en cas de necessitat.
### Jugador
Tindrà un mètode de desar partida que crearà un ***JSON*** amb la informació del nom del jugador, els punts que porta acumulats, a quina ronda es troba i un array amb els rivals de la seva partida.
