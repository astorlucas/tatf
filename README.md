# Taller de Automatización del Testing Funcional

## Contenido

Cada semana se creará un namespace con el número de semana del curso, donde se implementará la solución de las tareas que se soliciten.

### Construcción y testing

Existe una tarea llamada buildTestReports, que realiza el borrado de la última construcción, construye el sistema, corre las pruebas y los reportes de cobertura.

```
gradle buildTestReports
```

### Desarrollo de pruebas

Existe una tarea llamada testDev, que ejecuta solo las pruebas etiquetadas con la palabra test.

```
@Tag("dev")
@Test
public final void test() {
	....
}
```

El objetivo de esta tarea es poder ejecutar solo las pruebas que se están desarrollando. 

```
gradle testDev
```

### Acceso a servicios

Las pruebas que requieren acceso a servicios, necesitan recibir desde configuración, las credenciales necesarias para ingresar a ellos.

Para proporcionar estas credenciales, se debe copiar el archivo /src/test/resources/credentials.example.properties en el mismo directorio, con nombre credentials.properties; y establecer los valores necesarios para ingresar a los servicios.


## GIT

### Configuration

```
git mergetool {--tool-help|--tool=[name]}
git config --global user.{name|email} "..."
```

### Working directory

```
git init
git status
git log
git branch {empty show-list-brach} 
git branch {new-branch-name}
git branch {-v show-confirm|--merged|--no-merged}
git branch {-d,-D delete-branch-name}
git checkout {-b[create-and-switch-branch-name]|branch-name}
git merge {branch-name}
git mergetool
```

### Staging area

```
git add {file-name|.}
git reset
git stash
```

### Respository

```
git commit -m "comment"
git remote add origin {URL.git}
git push -u origin {branch}
git pull 
git clone {url}
```