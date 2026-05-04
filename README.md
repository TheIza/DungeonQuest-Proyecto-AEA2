# DungeonQuest-Proyecto-AEA2

Projecte acadèmic de Programació Orientada a Objectes avançada. Digitalització d'un joc de taula de masmorres.

---

## Descripció

El jugador controla un personatge que explora una masmorra de 6x6 sales. L'objectiu és sortir de la masmorra pels seus extrems recollint tresors i derrotant monstres pel camí.

---

## Estructura del projecte

```
POO/
├── main.java           → Punt d'entrada, bucle principal del joc
├── Masmorra.java       → Controlador del joc (estàtic)
├── Personatge.java     → Classe del jugador
├── Monstre.java        → Classe dels enemics
├── Tresor.java         → Classe base dels tresors
├── TresorMagic.java    → Tresor que recupera vida (ampliació)
├── TresorMaleit.java   → Tresor que fa dany (ampliació)
├── Combatent.java      → Interfície de combat
├── Sala.java           → Classe abstracta de sala
├── SalaComuna.java     → Sala estàndard
├── SalaPont.java       → Sala amb tirada d'agilitat per sortir
├── SalaTeranyina.java  → Sala amb tirada de força per sortir
└── SalaTrampa.java     → Sala que fa dany en explorar (ampliació)
```

---

## Com jugar

En cada torn tens fins a 3 opcions disponibles:

| Opció | Quan apareix | Descripció |
|---|---|---|
| `0. Explorar` | Sala no explorada | Troba el tresor de la sala |
| `1. Moure` | Sempre | Mou el personatge (N/S/E/O) |
| `2. Atacar` | Hi ha monstre viu | Combat contra el monstre |

### Moviment
- `N` → Amunt
- `S` → Avall  
- `E` → Dreta
- `O` → Esquerra

El personatge comença a la cantonada superior esquerra **(0,0)**.

---

## ipus de sales

| Símbol | Tipus | Efecte |
|---|---|---|
| `&` | Posició actual | — |
| `*` | Explorada | — |
| `X` | Sala trampa | — |
| `-` | No explorada | — |

| Tipus | Probabilitat | Efecte en intentar sortir |
|---|---|---|
| `SalaComuna` | 50% | Sempre es pot sortir |
| `SalaPont` | 20% | Tirada d'agilitat, si falla rep 1 de dany |
| `SalaTeranyina` | 15% | Tirada de força, si falla no es pot sortir |
| `SalaTrampa` | 15% | En explorar rep dany automàticament |

> Si hi ha un monstre i intentes fugir, el monstre et penalitza amb la seva penalització de fugida.

---

## Combat

1. El personatge calcula el seu atac (`calcularAtac()`)
2. El monstre rep el dany
3. Si el monstre sobreviu, contraataca
4. Si el personatge sobreviu, el torn acaba
5. Si el monstre mor, el personatge guanya experiència (`vida monstre × 2`)

---

## Tipus de tresors

| Tipus | Efecte |
|---|---|
| `Tresor` | Tresor normal, té valor en or |
| `TresorMagic` | En recollir-lo recuperes vida |
| `TresorMaleit` | En recollir-lo perds vida |

L'inventari del personatge té una mida igual a la seva **força**.

---

## Fi de la partida

### Victòria
El personatge surt de la masmorra per qualsevol extrem (excepte per on va entrar).

Mostra:
- Experiència total
- Nombre de tresors recollits
- Total de monedes d'or
- Vida restant
- % de masmorra explorada

### Derrota
La vida del personatge arriba a 0.

Mostra:
- Experiència aconseguida
- Causa de la mort
- % de masmorra explorada

---

## Atributs del personatge

| Atribut | Rang | Descripció |
|---|---|---|
| `vida` | 5 - 20 | Punts de vida |
| `atac` | = força | Dany màxim per atac |
| `agilitat` | 4 - 11 | Tirades de pont |
| `força` | 4 - 11 | Tirades de teranyina i mida inventari |
| `experiència` | 0+ | Acumulada derrotant monstres |

---

## Ampliacions implementades

1. **`SalaTrampa`** — sala especial que fa dany automàtic en explorar-la
2. **`TresorMagic`** — tresor que recupera vida en recollir-lo
3. **`TresorMaleit`** — tresor maleït que fa dany en recollir-lo
4. **Drac Final** — monstre especial amb 30 de vida al centre de la masmorra, el monstre pot estar en qualsevol de les 4 posicions centrals del tauler

---

## Tecnologies

- Java
- Programació Orientada a Objectes (Herència, Classes Abstractes, Interfícies)
