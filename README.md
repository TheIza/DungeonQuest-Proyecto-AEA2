# DungeonQuest-Proyecto-AEA2

Proyecto académico de Programación Orientada a Objetos avanzada. Digitalización de un juego de mesa de mazmorras.

---

## Descripción

El jugador controla un personaje que explora una mazmorra de 6x6 salas. El objetivo es salir de la mazmorra por sus extremos recogiendo tesoros y derrotando monstruos por el camino.

---

## Estructura del proyecto


POO/

|-- main.java → Punto de entrada, bucle principal del juego

|-- Mazmorra.java → Controlador del juego (estático)

|-- Personaje.java → Clase del jugador

|-- Monstruo.java → Clase de los enemigos

|-- Tesoro.java → Clase base de los tesoros

|-- TesoroMagico.java → Tesoro que recupera vida (ampliación)

|-- TesoroMaldito.java → Tesoro que hace daño (ampliación)

|-- Combatiente.java → Interfaz de combate

|-- Sala.java → Clase abstracta de sala

|-- SalaComun.java → Sala estándar

|-- SalaPuente.java → Sala con tirada de agilidad para salir

|-- SalaTelaraña.java → Sala con tirada de fuerza para salir

|__ SalaTrampa.java → Sala que hace daño al explorar (ampliación)



---

## Cómo jugar

En cada turno tienes hasta 3 opciones disponibles:

| Opción | Cuándo aparece | Descripción |
|--------|----------------|-------------|
| `0. Explorar` | Sala no explorada | Encuentra el tesoro de la sala |
| `1. Mover` | Siempre | Mueve el personaje (N/S/E/O) |
| `2. Atacar` | Hay un monstruo vivo | Combate contra el monstruo |
| `3. Informacion` | Siempre | Muestra informacion del personaje |

### Movimiento
- `N` → Arriba  
- `S` → Abajo  
- `E` → Derecha  
- `O` → Izquierda  

El personaje comienza en la esquina superior izquierda **(0,0)**.

---

## Tipos de salas

| Símbolo | Tipo | Descripción |
|--------|------|--------|
| `&` | Posición actual | Es donde se encuentra el jugador |
| `*` | Sala explorada | Muestra las salas exploradas |
| `X` | Sala trampa | Marca la sala trampa |
| `-` | No explorada | Zona del mapa no explorada |

---

## Mecánica de salida de salas

| Tipo | Probabilidad | Efecto al intentar salir |
|------|--------------|--------------------------|
| SalaComun | 50% | Siempre se puede salir |
| SalaPuente | 20% | Tirada de agilidad, si falla recibe 1 de daño |
| SalaTelaraña | 15% | Tirada de fuerza, si falla no puede salir |
| SalaTrampa | 15% | Al explorar recibe daño automático, siempre se puede salir |

> Si hay un monstruo e intentas huir, el monstruo aplica su penalización de huida.

---

## Combate

1. El personaje calcula su ataque (`calcularAtaque()`)
2. El monstruo recibe daño
3. Si el monstruo sobrevive, contraataca
4. Si el personaje sobrevive, el turno termina
5. Si el monstruo muere, el personaje gana experiencia (`vida del monstruo × 2`)

---

## Tipos de tesoros

| Tipo | Efecto |
|------|--------|
| Tesoro | Tesoro normal con valor en oro |
| TesoroMagico | Recupera vida al recogerlo |
| TesoroMaldito | Pierde vida al recogerlo |

El inventario del personaje tiene un tamaño igual a su **fuerza**.

---

## Fin de la partida

### Victoria
El personaje debe lograr 2 cosas, 1 matar al jefe final y salir de la mazmorra por cualquier de los 3 extremos (el unico extremo por el cual no puede escapar es por donde entro).

Se muestra:
- Experiencia total
- Número de tesoros recogidos
- Oro total
- Vida restante
- Porcentaje de mazmorra explorada
- creditos
  
### Derrota
La vida del personaje llega a 0.

Se muestra:
- Experiencia obtenida
- Causa de la muerte
- Porcentaje de mazmorra explorada
- creditos
  
---

## Atributos del personaje

| Atributo | Rango | Descripción |
|----------|-------|-------------|
| Vida | 5 - 20 | Puntos de vida |
| Ataque | = fuerza | Daño máximo por ataque |
| Agilidad | 4 - 11 | Tiradas en salas puente |
| Fuerza | 4 - 11 | Tiradas en telarañas e inventario |
| Experiencia | 0+ | Ganada al derrotar monstruos |

---

## Ampliaciones implementadas

1. SalaTrampa → daño automático al explorar  
2. TesoroMagico → recupera vida al recogerlo  
3. TesoroMaldito → hace daño al recogerlo  
4. Dragón Final → monstruo especial con 20 de vida en el centro de la mazmorra
5. Tercer opcion en el menú, muestra la informacion del personaje
6. Subir de nivel, puedes subir de nivel y mejorar tus estadisticas 

---
## Sistema de experiencia

Al matar monstruos ganas experiencia, si tu experiencia llega a 30 subiras de nivel, reiniciando la experiencia a 0.

Si subes de nivel y ganaste 10 de experiencia de mas ( experiencia actual + experiencia ganada > 40 ) ganaras +5 putnos en vida, agilidad, fuerza y ataque,
si tienes entre 30 y 40 de exp, ganaras +3 puntos de los valores mencionados.


---

## Tecnologías

- Java  
- Programación Orientada a Objetos (herencia, clases abstractas, interfaces)
