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

## ℹ️ ¿Qué hace git pull?

1. El comando:

      ```bash
      git pull origin <rama>
      
### 💡 Traducción simple:
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

# 🔧 Estructura de ramas recomendada
1. 
      ```bash
      main        ← Rama estable, solo se sube lo que ya está probado.
      develop     ← Rama de desarrollo general, base para nuevas funciones.
      feature/*   ← Ramas para cada funcionalidad nueva.
      hotfix/*    ← Ramas para arreglos rápidos a producción.

## 🛠️ Configuración inicial (solo una vez)
1. Estás en main, creas develop a partir de ahí:

      ```bash
      git checkout -b develop
      git push -u origin develop
      
      
2. A partir de ahora, todo el desarrollo nuevo sale desde develop.

## 🚀 Agregar una nueva funcionalidad (feature)

1. Cuando quieras trabajar en algo nuevo (por ejemplo, login):

      ```bash
      git checkout develop
      git pull origin develop  # Asegúrate de estar actualizado
      git checkout -b feature/login
      
Haz tus cambios, commits, etc.

2. Cuando termines:

      ```bash
      git checkout develop
      git merge feature/login
      git push origin develop
      
3. Opcional: eliminar la rama local y remota de feature

      ```bash
      git branch -d feature/login
      git push origin --delete feature/login
      
🧪 Pasar a producción (main)

4. Cuando tu develop ya tiene funcionalidades probadas y estables:

      ```bash
      git checkout main
      git pull origin main
      git merge develop
      git push origin main
      
## 🐞 ¿Algo se rompió en producción? Usa hotfix

1. Si necesitas arreglar algo rápido en producción:
         
      ```bash
      git checkout main
      git pull origin main
      git checkout -b hotfix/fix-login
      
2. Arreglas el bug, haces commit, y luego:

      ```bash
      git checkout main
      git merge hotfix/fix-login
      git push origin main
      
3. Y también lo pasas a develop:

      ```bash
      git checkout develop
      git merge hotfix/fix-login
      git push origin develop
      
 ---     
## 📌 Tips finales
- Usa nombres claros para ramas: feature/login, hotfix/crash, feature/producto-v2

- Nunca trabajes directamente en main

- Siempre parte desde develop para nuevas funciones

- Usa pull antes de hacer push para evitar conflictos


