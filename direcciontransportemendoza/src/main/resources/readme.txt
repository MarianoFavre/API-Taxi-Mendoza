API permisionarios taxis.

Consiste en 4 entidades relacionadas: PermitHolder, Aditamento, Car y Delegation.

Realizar Crud completo y 2 endpoint:

Patente del taxi que registró un determinado permisionarios (idPermisionario).
@GetMapping("/getCarPatentPermitHolderById/{id}")

Cantidad de taxi por cada una de las delegaciones.
@GetMapping("/getNumberTaxiByDelegation/{name}")

Tuve inconveniente para hacer este segundo endpoint. Consideré Delegation como entidad y probablemente lo tendría
que haber definido como atributo de la entidad Aditamento (eso no lo realicé desde un comienzo porque
quize aplicar normalización de datos).