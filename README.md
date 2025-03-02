EJERC006 -Nunchy Version 1.0

Proyecto de base de datos en java, con spring:
**Enunciado:**
Tenemos 4 entidades: Edicificio, dentro de edificio tenemos Aula, dentro de aula tenemos Mesa y luego tenemos Silla.
Edificio tiene aulas (no tenemos foreing pero tenemos un campo que es edificioId).
En mesa hay un campo que es aula_id, y en silla tenemos uno que es mesa_id. 
Inventar al menos 4 campos por entidad que tengan lógica.
De las logicas como mejora hay que exponer un método (que no sea el mismo de actualizar) que permita mover una mesa a un nuevo aula, 
y a ver qué hacemos con las sillas correspondientes; que pueda mover una silla de mesa. Hay que exponer los rest,
pero tiene que haber un método que sin terle que pasar la mesa entera o la silla entera (que le diga el id de mesa o silla) y que lo asigne.

######
 

### Estructura del Proyecto

Es una aplicación de gestión de edificios, aulas, mesas y sillas para una institución universitaria. 
En esta primera versión me he centrado en la lógica de mover/ eliminar sillas y mesas.

La estructura del proyecto incluye las siguientes partes principales:

 

### Modelos

#### Edificio

Representa un edificio. Tiene los siguientes atributos:

-  id : Identificador único del edificio.
-  nombre : Nombre del edificio.
-  direccion : Dirección del edificio.
-  numPlantas : Número de plantas del edificio.
-  capacidadMaxima : Capacidad máxima del edificio.
-  tipoEdificio: Tipo de edificio (INTERFACULTATIVO, MEDICINA, CIENCIAS, INGENIERIA, DERECHO, 
    ARTES;).

#### Aula

Representa un aula en un edificio. Tiene los siguientes atributos:

-  id : Identificador único del aula.
-  nombre : Nombre del aula.
-  capacidadMaximaMesas : Capacidad máxima de mesas en el aula.
-  numeroMesas : Número actual de mesas en el aula.
-  tipoAula : Tipo de aula (TEORICA, LABORATORIO, INFORMATICA, CONFERENCIA, BIBLIOTECA).
-  proyector : Indica si el aula tiene un proyector.
- edificioId: Identificador del edificio al que pertenece el aula.

Incluye validaciones para asegurarse de que la capacidad máxima de mesas no sea menor que el número actual de mesas y que no se puedan agregar más mesas si se ha alcanzado la capacidad máxima.

#### Mesa

Representa una mesa en un aula. Tiene los siguientes atributos:

-  id : Identificador único de la mesa.
-  nombre : Nombre de la mesa. Lo coge a través del id del aula a la que pertenece y su id propio.
-  capacidadMaximaSillas : el número de máximo de sillas que puede tener.
-  numeroSillas: el numero de sillas que tiene actualmente.
-  aulaId : Identificador del aula al que pertenece la mesa.

Incluye validaciones para asegurarse de que la capacidad máxima de sillas no sea menor que el número actual y que no se puedan agregar más sillas si se ha alcanzado la capacidad máxima.

#### Silla

Representa una silla en una mesa. Tiene los siguientes atributos:

- id: Identificador único de la silla.
- disponible: Indica si la silla está disponible.
- mesaId: Identificador de la mesa a la que pertenece la silla.
- nombre : Nombre de la silla. Lo coge a través del id de la mesa a la que pertenece y su id propio.
  
 



### Resumen del Funcionamiento

1. **Creación de Entidades**: Los servicios proporcionan métodos para crear nuevas instancias de  Edificio, Aula, Mesa, y Silla. Estos métodos validan los datos y utilizan los repositorios para guardar las entidades en la base de datos.

2. **Lectura de Entidades**: Los servicios proporcionan métodos para leer entidades por su ID y listar todas las entidades. Estos métodos utilizan los repositorios para recuperar los datos de la base de datos.

3. **Actualización de Entidades**: Los servicios proporcionan métodos para actualizar entidades existentes. Estos métodos validan que las entidades tengan un ID antes de actualizarlas y utilizan los repositorios para guardar los cambios en la base de datos.

4. **Eliminación de Entidades**: Los servicios proporcionan métodos para eliminar entidades por su ID. Estos métodos utilizan los repositorios para eliminar las entidades de la base de datos.

5. **Lógica Adicional** 
    *  La clase `MesaService` incluye:
         `moveToNewAula` que permite mover una mesa de un aula a otro. Este método verifica que el aula de destino tenga espacio disponible antes de mover la mesa y actualiza el número de mesas en ambos aulas.
         Si la mesa tiene sillas, continúan asociadas a la mesa, lo que significa que también sean movidas. 

         `delete` permite eliminar una mesa solo si no tiene sillas asociadas, en cuyo caso no permite eliminar. Actualiza el numero de mesas en el aula. 

    *  La clase `SillaService` incluye:
        `moveSilla` que permitePermite mover una silla de una mesa a otra  si la mesa de destino tiene capacidad disponible

          `delete` permite eliminar una silla y actualiza el numero de sillas en la mesa. 

    *  La clase `AulaService` incluye:
          `delete` permite eliminar un aula solo si no tiene mesas asociadas, en cuyo caso no permite eliminar.  

 



