# **DCine** - Tienda de cultura fílmica

<p align="center">
  <img src="./Documentación/5. Logo/DCine - Logo.png" width="35%"/>
</p>

## Sobre el proyecto

**DCine** es la idea que se ha planteado para presentar como proyecto de fin de ciclo de **Desarrollo de Aplicaciones Web** en el curso 2021-2022. Se ha desarrollado una tienda *online* de cine utilizando **Spring Boot** (para el *backend*) y **React** (para el *frontend*) además de algunas librerías de componentes que se enumerarán en los apartados posteriores.

## Software utilizado a la hora de desarrollar el proyecto

Para el desarrollo de este proyecto se ha usado **Visual Studio Code**, **XAMPP** y **Node.js**. Por lo tanto, será recomendable contar con estos u otros programas similares en el equipo para poder ejectuar correctamente el código que se encuentra en este repositorio.

[<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Visual_Studio_Code_1.35_icon.svg/800px-Visual_Studio_Code_1.35_icon.svg.png" alt="Visual Studio Code" width="35"/>](https://code.visualstudio.com/)
[<img src="https://www.expertosdecomputadoras.com/wp-content/uploads/2012/02/como%20instalar%20xampp%20eaccelerator%20en%20un%20mac.png" alt="XAMPP" width="35" style="margin-left: 10px;"/>](https://www.apachefriends.org/es/index.html)
[<img src="https://midu.dev/images/tags/node.png" alt="XAMPP" width="35" style="margin-left: 10px;"/>](https://nodejs.org/es/)

## Librerías usadas para la interfaz de usuario

- Para la página de administración de la web se ha optado por utilizar [Boostrap 5.1](https://getbootstrap.com/docs/5.1/getting-started/introduction/) y [Thymeleaf](https://www.thymeleaf.org/).
- Las librerías de **React** utilizadas para desarrollar el *frontend* de la tienda han sido [TailwindCSS v.3.0](https://tailwindcss.com/blog/tailwindcss-v3), [TailwindUI](https://tailwindui.com/), [MUI](https://mui.com/) y [daisyUI](https://daisyui.com/); de las que se han sacado los componentes y plantillas que luego se han modificado y conforman el frontal de la tienda.

## Librerías usadas para el desarrollo del *backend*

- **Spring Boot** para el desarrollo del *backend* en sí mismo.
- **Spring Security** para la securización del *backend*
- **JavaMail** para el envío de correos automáticos.
- **MapStruct** para el mapeo entre las clases modelo y los DTOs.

## Tratamiento de las imágenes

Se ha optado por alojar las imágenes dinámicas de la aplicación, es decir, las fotografías de los directores y las portadas de las películas que se ofertan, en [**Cloudinary**](https://cloudinary.com), el cual es de implementación muy cómoda con **Spring Boot**.

## Cómo correr el backend de **DCine**

En primer lugar será necesario ejecutar el script de la base de datos que se encuentra en la sección de documentación de este repositorio.

Una vez realizado el paso anterior será el momento de ejecutar el backend. Para hacerlo, si se cuenta con algún plugin de **Spring** instalado en el **IDE** (o se está trabajando desde **Spring Tools Suite**) simplemente será necesario darle al botón de *play* y el proyecto se ejecutará. Por otro lado, también será posible arrancar el *backend* desde la consola lanzando el siguiente comando:

```bash
.\mvnw.cmd spring-boot:run "-Dspring-boot.run"
```

Una vez hecho esto, el *backend* debería estar corriendo sin problemas en el equipo. Si se quiere acceder al panel de administración será necesario entrar a través de la URL **<http://localhost:8080>** y con las siguientes credenciales:
- **Usuario**: admin@gmail.com
- **Contraseña**: admin123

## Cómo correr el frontend de **DCine**

Teniendo en cuenta que se debería tener instalado **Node.js** en el equipo, lo primero que se debe hacer es, dentro del directorio donde se aloja el código del *frontend* lanzar el siguiente comando:

```bash
npm install
```

El comando anterior habrá descargado todo lo necesario para ejecutar el front, por lo que lanzando el siguiente comando ya podríamos visualizar la web en un navegador a través de la URL **<http://localhost:3000>**

```bash
npm start
```

## Autor

Este proyecto ha sido creado y desarrollado por **Diego García Miño**.
