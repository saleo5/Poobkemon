
# Proyecto POOBkemon

Este es un proyecto de Java basado en la programación orientada a objetos (POO) que simula una batalla de Pokémon. Los jugadores pueden seleccionar sus Pokémon, luchar contra oponentes controlados por la inteligencia artificial, usar objetos y cambiar de Pokémon durante las batallas.

## Requisitos

Para ejecutar este proyecto, debes tener instalados los siguientes programas:

1. **Java JDK 8 o superior**: El proyecto está basado en Java y requiere que tengas el JDK instalado.
2. **Apache Maven (opcional)**: Si prefieres usar Maven para gestionar dependencias y construir el proyecto.

## Instalación

### 1. Clonar el repositorio

Si no tienes el proyecto en tu máquina local, puedes clonarlo desde tu repositorio de GitHub o usar el siguiente comando en la terminal:

### 2. Compilación y ejecución del proyecto

#### Usando Maven (recomendado):
1. Dirígete al directorio raíz del proyecto:
   
   ```bash
   cd POOBkemon
   ```

2. Si no tienes Maven instalado, puedes hacerlo siguiendo [la documentación oficial](https://maven.apache.org/install.html).

3. Una vez tengas Maven instalado, puedes compilar el proyecto con el siguiente comando:

   ```bash
   mvn clean install
   ```

4. Para ejecutar el proyecto, usa el siguiente comando:

   ```bash
   mvn exec:java
   ```

#### Usando la terminal directamente (sin Maven):

1. Navega al directorio `src` del proyecto:

   ```bash
   cd POOBkemon/src
   ```

2. Compila el código fuente de Java:

   ```bash
   javac presentacion/*.java dominio/*.java presentacion/componentes/*.java
   ```

3. Luego, ejecuta el proyecto con el siguiente comando (asegurándote de que el `Main` está en el paquete adecuado):

   ```bash
   java presentacion.MainWindow
   ```

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

```
POOBkemon/
│
├── src/                     # Código fuente
│   ├── dominio/             # Clases relacionadas con la lógica de negocio
│   ├── presentacion/        # Clases para la interfaz de usuario (UI)
│   └── presentacion/componentes/   # Componentes de la UI (botones, tarjetas, etc.)
│
├── resources/               # Imágenes y archivos de recursos
│   ├── items/               # Imágenes de ítems (como pociones)
│   └── pokemons/            # Imágenes de Pokémon
│
└── pom.xml                  # Configuración de Maven (si se utiliza)
```

## Ejecución

Cuando el proyecto esté correctamente compilado, podrás ejecutar el juego en la interfaz gráficam, que esta como una clase llamada MainWindow, la cual incluye:

- **Selección de Pokémon**: El jugador elige un Pokémon para luchar.
- **Opciones de batalla**: El jugador puede atacar, cambiar de Pokémon, usar un ítem o huir.
- **Modo de juego**: Puedes jugar contra otro jugador o contra la inteligencia artificial (IA).

### Interfaz:

1. **Panel superior**: Muestra el Pokémon activo tanto del jugador como del oponente, junto con sus barras de salud.
2. **Panel central**: Muestra el log de la batalla, donde se muestran los turnos y las acciones realizadas.
3. **Panel inferior**: Presenta las opciones de acción: Atacar, Cambiar Pokémon, Usar un ítem o Huir.


## Autor

Este proyecto fue creado por **Samuel Leonardo Albarracín Vergara**.
