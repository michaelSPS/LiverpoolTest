# 🧪 Proyecto de Automatización E2E: LiverpoolApexTest

1. ## 📋 Descripción general

Este proyecto automatiza pruebas de extremo a extremo (E2E) sobre el sitio web de **Liverpool**, simulando interacciones de un usuario real como búsqueda de productos, aplicación de filtros, y validación del carrito de compras. Todo está desarrollado en **Java**, utilizando herramientas modernas como **Selenium**, **Cucumber**, **JUnit** y **Allure**.

---

2. ## 🧰 Tecnologías y herramientas utilizadas

| Herramienta             | Rol                                                                 |
|-------------------------|----------------------------------------------------------------------|
| **Java 17**             | Lenguaje base del proyecto                                           |
| **Maven**               | Gestor de dependencias y ejecución de pruebas                       |
| **Selenium WebDriver**  | Interacción con el navegador (Chrome)                               |
| **Cucumber**            | Automatización basada en BDD (Behavior Driven Development)          |
| **JUnit**               | Ejecutor de pruebas                                                  |
| **Allure**              | Generador de reportes detallados y visuales                         |
| **IntelliJ IDEA**       | IDE usado para desarrollo y debugging                               |

---

3. ## 🧬 Estructura del proyecto

      ```bash
      LiverpoolApexTest/
      ├── src/
      │   ├── main/
      │   └── test/
      │       ├── java/
      │       │   ├── runner/         # Clase TestRunner con JUnit + Cucumber
      │       │   └── steps/          # Clases de definición de pasos
      │       ├── resources/
      │       │   ├── features/       # Archivos .feature de Cucumber
      │       │   ├── configfiles/    # config.properties y locators.properties
      │       │   └── allure.properties # Configuración para reportes Allure
      ├── pom.xml                     # Configuración de Maven

---

4. ## 🔧 ¿Qué hace cada tecnología?

### 🔹 Selenium WebDriver
Simula un navegador real (Chrome) y realiza clics, scrolls, inputs de texto, selección de filtros y validación de elementos en pantalla.

### 🔹 Cucumber
Define escenarios en lenguaje natural (`Gherkin`) para facilitar el entendimiento del comportamiento esperado.  
1. Ejemplo:

      ```gherkin
      Scenario: El usuario agrega una PS5 al carrito
        Given The Client navigate to home-page
        When The Client write playstation in the searchbar
        Then The Client must be able to see the playstation 5 on the shopping cart

### 🔹 JUnit
Se utiliza junto con Cucumber para ejecutar los tests desde la clase TestRunner.

### 🔹 Allure
Genera reportes visuales a partir de los resultados de pruebas, incluyendo pasos, duración, estatus e información relevante.

--- 

5. ## 🛠️ Problemas enfrentados y soluciones

Problema	Solución aplicada
❌ target/allure-results no se generaba	Se agregó el adaptador de Allure en pom.xml y se creó el archivo allure.properties
❌ mvn allure:serve no mostraba reporte	Se ejecutaron correctamente los tests y luego se generó el reporte
❌ Advertencia de Selenium CDP (versión no compatible)	Se ignoró por el momento (no afecta funcionalidad); se recomienda actualizar

---

6. ## 🚀 ¿Cómo ejecutar el proyecto?
1. Clonar el repositorio
      ```bash
      git clone https://github.com/tuusuario/LiverpoolApexTest.git
      cd LiverpoolApexTest
      
2. Ejecutar pruebas
      ```bash
      mvn clean test
3. Generar reporte Allure
      ```bash
      mvn allure:serve
      
4. El reporte se abrirá automáticamente en el navegador por http://localhost:port.

--- 

7. ## 📈 Resultado final
El proyecto corre exitosamente 2 escenarios complejos que incluyen búsqueda de productos, aplicación de filtros y validación del carrito de compras. Se generan reportes detallados con Allure que permiten visualizar claramente el paso a paso de la ejecución.

---

8. ## 🧠 Autor
Proyecto realizado por: Michael Perez
🎓 Curso basado en práctica profesional automatizada
💻 Automatización avanzada con Selenium, Cucumber y Allure


---
--- 


# LiverpoolTest

# 🧬 Flujo de Trabajo con Git (Git Flow Simplificado)

Este repositorio sigue un flujo de trabajo sencillo pero profesional para evitar errores en la rama principal y trabajar de forma organizada.

---

## 🛠️ Ramas principales

- `main`: Rama estable. Solo contiene código probado y listo para producción.
- `develop`: Rama de desarrollo general. Desde aquí salen todas las funcionalidades nuevas.

---

## 🌿 Crear una nueva funcionalidad

1. Cambiar a la rama `develop`:
   ```bash
   git checkout develop
   git pull origin develop
   
2. Crear tu rama de funcionalidad:
    ```bash
    git checkout -b feature/nombre-de-la-funcion
    
3. Hacer cambios, guardar con:
    ```bash
    git add .
    git commit -m "Agrega nueva funcionalidad"
    
3. Unirla a develop cuando esté lista:
    ```bash
    git checkout develop
    git merge feature/nombre-de-la-funcion
    git push origin develop
    
4. (Opcional) Borrar la rama:
    ```bash
    git branch -d feature/nombre-de-la-funcion
    git push origin --delete feature/nombre-de-la-funcion

---

## 🚀 Subir a producción (pasar develop a main)

1. Cambiar a main:
    ```bash
    git checkout main
    git pull origin main
    
2. Unir los cambios de develop:
    ```bash
    git merge develop
    git push origin main

---
    
## 🐞 Arreglos urgentes en producción (hotfix)

1. Crear una rama desde main:
    ```bash
    git checkout main
    git pull origin main
    git checkout -b hotfix/fix-nombre
    
2. Arreglar el error y hacer commit.

3. Unir el arreglo a main y develop:
    ```bash
    git checkout main
    git merge hotfix/fix-nombre
    git push origin main
    
    git checkout develop
    git merge hotfix/fix-nombre
    git push origin develop

---

## 🧠 Comandos clave explicados

 Comando	| Qué hace

      git checkout <rama>	Cambia a la rama indicada.
      git pull origin <rama>	Trae los últimos cambios de GitHub y los mezcla con los tuyos.
      git push origin <rama>	Sube tus cambios al repositorio remoto.
      git merge <rama>	Une otra rama a la que estás usando.
      git branch -d <rama>	Borra una rama local.
      git push origin --delete <rama>	Borra una rama remota en GitHub.


## ✅ Buenas prácticas

- Nunca trabajes directo en main.

- Siempre saca ramas desde develop.

- Usa nombres claros: feature/registro, hotfix/pago-error, etc.

- Haz pull antes de hacer push.

---

---

# ESTRUCTURAR RAMAS

1. 🔧 Estructura de ramas recomendada
      ```bash
      main        ← Rama estable, solo se sube lo que ya está probado.
      develop     ← Rama de desarrollo general, base para nuevas funciones.
      feature/*   ← Ramas para cada funcionalidad nueva.
      hotfix/*    ← Ramas para arreglos rápidos a producción.

2. 🛠️ Configuración inicial (solo una vez)
Estás en main, creas develop a partir de ahí:
      ```bash
      git checkout -b develop
      git push -u origin develop
A partir de ahora, todo el desarrollo nuevo sale desde develop.

## 🚀 Agregar una nueva funcionalidad (feature)

3. Cuando quieras trabajar en algo nuevo (por ejemplo, login):
      ```bash
      git checkout develop
      git pull origin develop  # Asegúrate de estar actualizado
      git checkout -b feature/login
   
## Haz tus cambios, commits, etc.

4. Cuando termines:
      ```bash
      git checkout develop
      git merge feature/login
      git push origin develop
      
5. Opcional: eliminar la rama local y remota de feature
      ```bash
      git branch -d feature/login
      git push origin --delete feature/login
      
## 🧪 Pasar a producción (main)

6. Cuando tu develop ya tiene funcionalidades probadas y estables:
      ```bash
      git checkout main
      git pull origin main
      git merge develop
      git push origin main
      
## 🐞 ¿Algo se rompió en producción? Usa hotfix

7. Si necesitas arreglar algo rápido en producción:
   
      ```bash
      git checkout main
      git pull origin main
      git checkout -b hotfix/fix-login
      
8. Arreglas el bug, haces commit, y luego:
      ```bash
      git checkout main
      git merge hotfix/fix-login
      git push origin main
      
9. Y también lo pasas a develop:
      ```bash
      git checkout develop
      git merge hotfix/fix-login
      git push origin develop
      
     
## 📌 Tips finales
- Usa nombres claros para ramas: feature/login, hotfix/crash, feature/producto-v2

- Nunca trabajes directamente en main

- Siempre parte desde develop para nuevas funciones

- Usa pull antes de hacer push para evitar conflictos

---

---

# DESPUES DE CREAR LA FEATURE/LOGIN

🔨 ¿Cómo seguir trabajando en feature/login?

1. Hacé los cambios que quieras en tu proyecto (modificar archivos, agregar nuevas funciones, etc.).
   
3. Una vez que termines algo que querés guardar:
      ```bash
      git add .
(o podés agregar archivos específicos con git add archivo.js)

3. Después hacés un commit con un mensaje claro:
      ```bash
      git commit -m "Agrega formulario de login con validación"

5. Y si querés subir tu rama con los cambios a GitHub:
      ```bash
      git push origin feature/login
      
---      
## 🔄 ¿Cómo volver atrás sin romper nada?
Depende de qué tanto avanzaste:

1. ❌ Aún no hiciste commit y querés descartar cambios:
      ```bash
      git restore .

2. 🔁 Ya hiciste commit pero no hiciste push:
      ```bash
      git reset --soft HEAD~1
      
(o --hard si querés borrar los cambios también, pero cuidado, eso no se puede deshacer fácilmente).

3. 🕐 Volver a como estaba develop:
Si querés descartar todo lo que hiciste y volver a como estaba develop, podés hacer esto
      ```bash
      git checkout develop
      git branch -D feature/login

4. Y si necesitás crear la rama de nuevo:
      ```bash
      git checkout -b feature/login

5. 💡 Tip Pro:
Para ver el historial de commits (como un árbol), podés instalar un plugin o usar este comando:
      ```bash
      git log --oneline --graph --all --decorate


---

---

## ℹ️ ¿Qué hace git pull?

1. El comando:

      ```bash
      git pull origin <rama>
      
💡 Traducción simple:
- Descarga los cambios más recientes del repositorio remoto (GitHub) y los mezcla con tu copia local.

2. ¿Qué pasa detrás?
   
`git pull` = `git fetch` + `git merge`

- `git fetch`: baja los cambios del servidor remoto pero no los aplica aún.

- `git merge`: integra esos cambios en tu rama local.

3. Ejemplo:

      ```bash
      git checkout develop
      git pull origin develop
      
→ Te asegura tener lo más actualizado del repositorio antes de trabajar.

---

---

# COMANDOS DE GIT

1. 🔧 Configuración inicial
      ```bash
      git config --global user.name "Tu Nombre"
      git config --global user.email "tu@email.com"

2. 📦 Inicializar o clonar un repo     
      ```bash
      git init                      # Inicializa un nuevo repositorio
      git clone <url>              # Clona un repositorio remoto

3. 📄 Estado y cambios
      ```bash
      git status                   # Muestra los archivos modificados, staged, etc.
      git diff                     # Muestra los cambios no staged
      git diff --staged            # Muestra los cambios staged

4. ➕ Agregar y confirmar cambios
      ```bash
      git add <archivo>            # Agrega archivo al staging area
      git add .                    # Agrega todos los archivos modificados
      git commit -m "Mensaje"      # Confirma cambios

5. 🔁 Trabajo con ramas
      ```bash
      git branch                   # Lista las ramas
      git branch <nombre>          # Crea una nueva rama
      git checkout <nombre>        # Cambia a otra rama
      git checkout -b <nombre>     # Crea y cambia a una nueva rama
      git merge <rama>             # Fusiona una rama en la actual
      git branch -d <rama>         # Borra una rama local

6.  ⬆️⬇️ Sincronización con repositorio remoto
      ```bash
      git remote -v                # Muestra los repos remotos configurados
      git push origin <rama>       # Sube cambios al remoto
      git pull origin <rama>       # Baja cambios y los fusiona
      git fetch                    # Trae cambios del remoto pero no los fusiona

7. 🕵️‍♂️ Historial y revisión
      ```bash
      git log                      # Muestra el historial de commits
      git log --oneline            # Historial compacto
      git show <commit>            # Muestra detalles de un commit

8. 🔄 Revertir o rehacer cosas
      ```bash
      git checkout -- <archivo>    # Revierte cambios en un archivo (no commiteados)
      git reset HEAD <archivo>     # Saca un archivo del staging
      git reset --soft HEAD~1      # Revierte el último commit (deja cambios)
      git revert <commit>          # Crea un commit que deshace uno anterior

9. 💣 Borrar con cuidado
      ```bash
      git rm <archivo>             # Elimina un archivo del repo y del disco
      git clean -fd                # Elimina archivos/directorios no versionados


---

---

# FORZAR PUSH

🧨 Opción 1: Forzar el push (sobrescribes el remoto)
1. ⚠️ Esta opción elimina los cambios que están en el remoto (los sobrescribe con los tuyos). Solo hazlo si estás seguro de que tus cambios locales son los correctos y quieres ignorar lo que hay en remoto.

      ```bash
      git push --force origin feature/login
      
✅ Esta opción es rápida y te deja el repositorio remoto exactamente como está tu versión local.

🛠️ Opción 2: Hacer un merge y conservar todo
2. Si quieres conservar tus cambios locales pero también integrar lo del remoto, entonces puedes hacer un merge en vez de rebase:

      ```bash
      git pull --no-rebase origin feature/login
      
3. Esto hará un merge entre lo remoto y lo local (y puede generar conflictos como antes, pero esta vez más fácil de manejar). Luego:

      ```bash
      git push origin feature/login
      
🔐 Recomendación
- Si estás trabajando tú solo en el branch, puedes usar --force sin miedo.

- Si más personas están trabajando en el branch, mejor haz el merge y resuelve cualquier conflicto.


---

---

# COMANDOS DE MVN

1. 🧹 Limpiar el proyecto
      ```bash
      mvn clean
Elimina los archivos generados (target/), dejándolo limpio para una nueva compilación.

2. 🏗️ Compilar el código
      ```bash
      mvn compile
Compila el código fuente del proyecto (src/main/java).

 3. ✅ Ejecutar pruebas
      ```bash
      mvn test
Ejecuta las pruebas en src/test/java.

4. 🚀 Empaquetar el proyecto
      ```bash
      mvn package
Compila el código y lo empaqueta (por ejemplo, en un .jar o .war, según el tipo de proyecto).

5. 🔧 Instalar en el repositorio local
      ```bash
      mvn install
Instala el .jar o .war generado en tu repositorio local (~/.m2/repository) para que otros proyectos puedan usarlo.

6. 🔄 Actualizar dependencias forzadamente
      ```bash
      mvn clean install -U
Fuerza a Maven a actualizar todas las dependencias (-U = update snapshots/releases).

7. 📦 Descargar dependencias
      ```bash
      mvn dependency:resolve
Fuerza la descarga de todas las dependencias del proyecto sin compilar ni testear.

8. 📄 Generar el árbol de dependencias
      ```bash
      mvn dependency:tree
Muestra todas las dependencias y sus versiones en forma de árbol. Útil para ver conflictos.

9. 🔍 Ver información del proyecto
      ```bash
      mvn help:effective-pom
Muestra el pom.xml efectivo (incluyendo herencias y dependencyManagement).

10. 🧪 Ejecutar una clase específica con pruebas
      ```bash
      mvn -Dtest=NombreDeLaClaseDeTest test
Ejecuta sólo una clase de test específica.

11. 🛠️ Ejecutar una clase main (con exec plugin)
      ```bash
      mvn exec:java -Dexec.mainClass="com.ejemplo.Main"
Ejecuta una clase que tenga public static void main(String[] args) (requiere configurar el plugin exec).

12. Y si quieres ver el árbol completo:

      ```bash
      mvn dependency:tree -Dverbose
13. Si usas Allure, asegúrate de generar el informe con:

      ```bash
      mvn clean test
      mvn allure:serve


---

---

# OTRO COMANDOS

1. Abre una terminal en la raíz de tu proyecto.
      ```bash
      tree -L 3 src
Si te aparece algo como command not found, puedes instalar tree con:

2. macOS (Homebrew):
      ```bash
      brew install tree
