# Tarea corta #1

Curso IC-6821 Diseño de software  
Profesor Diego Munguía Molina

## Objetivos ##

* Aplicar el principio de encapsulamiento en un ejercicio de programación orientada a objetos.
* Aplicar el principio de herencia en un ejercicio de programación orientada a objetos.

## Guía de trabajo ##

Queremos implementar una aplicación de línea de comando para jugar equis-cero (X/O), también conocido como Gato (#), o
*tic tac toe* en inglés.

En X/O dos jugadores compiten colocando fichas en un tablero de 3 x 3 casillas, un jugador utiliza la ficha X mientras
que el otro utiliza la ficha O.

El objetivo del juego es lograr colocar tres fichas en línea, ya sea en la misma columna, la misma fila o en alguna de
las diagonales.

Vamos a iniciar el proyecto creando un modelo orientado a objetos del tablero.

Queremos implementar el tablero como una matriz de 3 x 3 que va a contener fichas. Sin embargo, atendiendo a los
principios de la programación orientada a objetos, necesitamos **encapsular** esta estructura de datos en un objeto.

Ya hemos adelantado trabajo y hemos modelado la ficha como una enumeración que llamamos `Token`.

De esta forma nuestro tablero será una matriz de la forma

```java
Token[][] board = Token[3][3]
```

### Requerimiento 1 ###

Implementar una clase `Board` en el paquete `edu.tec.ic6821.tictactoe` que encapsule correctamente nuestra matriz de `Token`s.

### Requerimiento 2 ###

También necesitamos agregar comportamiento a nuestro tablero, a través de modelar la acción de colocar fichas en el
tablero.

Al colocar una ficha en el tablero queremos validar una serie de condiciones:

- ¿Estamos colocando la ficha en una posición válida del tablero?
- ¿Estamos colocando la ficha en una casilla que no está ocupada por otra ficha?
- ¿Gana el jugador con esta movida?
- ¿Se empata el juego con esta movida?

Al aplicar la acción de colocar la ficha, esperamos obtener como resultado la respuesta a alguna de estas preguntas.
Estas respuestas ya se encuentran debidamente representadas con la enumeración `MoveStatus`.

Implementar entonces el siguiente método en `Board`

```java
MoveStatus applyMove(int row, int col, Token token);
```

### Requerimiento 3 ###

Finalmente, necesitamos una representación visual de nuestro tablero. Puesto que nuestra implementación es para una
aplicación de línea de comando, entonces vamos a desplegar el tablero como una hilera.

Para lograr esto, queremos aplicar el principio de **herencia** extender la funcionalidad de la clase `java.lang.Object` 
la cual provee un método `toString()` que produce una representación en hilera del objeto.

Recordemos que toda clase en Java extiende directa o indirectamente de `java.lang.Object`.

Debemos sobrecargar el método `toString()` en la clase `Board` para que retorne una representación en hilera del
tablero que siga el formato del siguiente ejemplo:

```
+---+---+---+
|   |   | X |
+---+---+---+
| O | O | X |
+---+---+---+
|   |   | X |
+---+---+---+
```

## Ambiente de desarrollo ##

El código requiere de Java 11 y gradle.

Para correr las pruebas:

```bash
./gradlew clean check
```

Para ejecutar la aplicación:

```bash
./gradlew run
```

## Aspectos operativos ##

- No modificar ningún archivo de código ya existente en el repositorio.
- No modificar ninguna prueba automatizada.
- No modificar ningún chequeo de estilo. 
- No utilizar spanglish.
- Escribir código limpio.
- Las pruebas de código verifican todos los requerimientos funcionales.
- Como entrega se considera el último `push` de código al repositorio antes de la revisión del trabajo.
- Puede encontrar información sobre cómo corregir problemas identificados por los chequeos de estilo en el siguiente 
  enlace: https://checkstyle.sourceforge.io/checks.html 

## Rúbrica ##

#### Total 10 pts #### 

#### Entrega (2 pts) ####
- (2 pts) El historial del repositorio contiene commits significativos de la persona estudiante.
- (0 pts) El historial del repositorio no contiene commits significativos de la persona estudiante. Si no hay commits 
  significativos, el resto de criterios no serán aplicados.

#### Chequeo de estilo (2 pts) ####
- (2 pts) El código en el repositorio pasa todos los chequeos de estilo.
- (0 pts) El código en el repositorio no pasa todos los chequeos de estilo.

#### Chequeo de diseño (3 pts) ####
- (3 pts) El código en el repositorio pasa todos los chequeos de diseño.
- (1 pts) El código en el repositorio pasa la mayoría de los chequeos de diseño.
- (0 pts) El código en el repositorio no pasa los chequeos de diseño.

#### Pruebas de código (3 pts)
- (3 pts) El código en el repositorio pasa todas las pruebas de código.
- (1 pts) El código en el repositorio pasa la mayoría de las pruebas de código.
- (0 pts) El código en el repositorio no pasa las pruebas de código.

