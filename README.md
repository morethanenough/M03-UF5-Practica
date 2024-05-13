# M03-UF5-Practica
## Resum
La nostra aplicació és un joc de pedra, paper i tisora.
La primera escena és d'accés a l'aplicació amb tres opcions: Començar, Ranking i Sortir.
**_Començar_**: Anirem a una nova escena que ens demanarà el nostre nom i ens demanarà que escollim un avatar.
## Classes
### PJ
Classe abstracte de la que sortiran la classe *jugador* i *enemic*.
Atributs: nom i foto.
Mètodes: getters i setters.
### Enemic
Classe que exten de **_Pj_**.
Atributs: frase_de_victoria i frase_de_derrota.
Mètodes: constructor, setters, getters i elegirAtaque (genera la jugada aleatoria).
### Jugador
Classe que exten de **_Pj_**.
Atributs: puntuacion, partidasGanadas, partidasPerdidas.
Mètodes: constructor, setters i getters.
### User
En la classe de User hi ha mètodes per a cada petició de la base de dades.<br>
Per tant quan volem utilitzar una la cridem al controller i fem una nova funció cridant al mètode que volem utilitzar de la classe.<br>
Cal destacar que en cada mètode s'obre la instància per accedir a la base de dades i quan es finalitza el mètode es tanca la instància.<br>

