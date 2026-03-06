Caso 6. Gestor de tareas personales
Patrones obligatorios: Command + Iterator + Singleton
Contexto
Una app de consola debe permitir crear, completar, eliminar y listar tareas. Además, se debe poder recorrer las tareas pendientes y mantener un administrador central del sistema.
Requerimiento funcional
Crear tareas
Completar tareas
Eliminar tareas
Listar tareas
Recorrer tareas pendientes o completadas
Patrones a evidenciar
Command: para acciones sobre tareas
Iterator: para recorrer listas de tareas según estado
Singleton: para un TaskManager
Qué debe mostrar en consola
Menú CRUD básico de tareas
Historial de acciones
Recorrido de tareas por filtros

Evidencia esperada
Acciones encapsuladas en comandos
Recorridos por iteradores concretos
Existencia de un único administrador de tareas
