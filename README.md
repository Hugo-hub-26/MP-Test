# MP-PracticaImplementacion
Repositorio para el trabajo de MP.

Se trata de un videojuego de fantasía en el que los jugadores pueden combatir y ganar oro a través de sus personajes.

## Tecnología
- Java JDK 21
- Apache Ant
- SonarQube (local)
- SonarScanner CLI
- Jenkins

Compilación:
  ant clean compile

Ejecución:
A pesar de que ant trae el comando ``ant run`` recomendamos correr ``ant jar`` y ejecutar ``java -jar dist/MP-PracticaImplementacion.jar`` para evitar que se rompa la interfaz de línea de comandos.

Análisis de calidad:
  ant sonar

## Ejecución
Compilcación y ejecución con Ant desde código fuente.
Ejecución del .jar con java.

## Requirements
Dependencias para correr el proyecto:
- JDK 21.0.10 (Oracle): recomendamos instalarlo a través a través del ejecutable (.exe), porque así se asignan las variables de entorno automáticamente.
- Uno de los siguientes:
  - Apache Netbeans: en este caso bastará con abrir el proyecto desde Netbeans y ejecutarlo asegurandose de que el JDK utilizado es la versión especificada anteriormente. Para ello se hace click derecho sobre el nombre del proyecto y se busca la opción Propiedades > Librerias > Plataforma Java y se selecciona la correspondiente.
  - Apache Ant 1.10.17: podemos encontrar las instrucciones de instalación aquí: https://ant.apache.org/manual/install.html
  Resumen:
  1. Descargar los binarios adecuados para cada plataforma.
  2. Asumiendo Windows, descomprime el archivo en una carpeta que no necesite privilegios de edición, por ejemplo ``C:\ant`` o ``C:\Program Files\ant``
  3. Ajustar las variables de entorno apropiadamente. JAVA_HOME a nuestro entorno de Java (probablemente ``C:\Program Files\Java\jdk-21.0.10``) y añadir  ``%ANT_HOME%\bin`` al ``PATH``. 
  4. Y correr ``ant -f fetch.xml -Ddest=system`` en ANT_HOME para descargar todas las dependencias de Ant.

## Integración Continua

El proyecto utiliza Jenkins como servidor de integración continua.
Cada ejecución del pipeline realiza:

1. Clonado del repositorio desde GitHub
2. Compilación del proyecto mediante Apache Ant
3. Análisis de calidad del código con SonarQube

El análisis de calidad se ejecuta contra una instancia local de SonarQube.
Las credenciales de acceso (token) no se almacenan en el repositorio y se
gestionan externamente.

## Interacción
El programa pide entradas de teclado.
