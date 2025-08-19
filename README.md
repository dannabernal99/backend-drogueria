Título:
Sistema de gestión MediSys para la administración eficiente de una droguería pequeña.

Descripción breve del problema:

Las droguerías pequeñas en chiquinquira enfrentan múltiples dificultades en la gestión de medicamentos, clientes y procesos internos, lo que afecta la eficiencia y el servicio al cliente. Algunos de los problemas más comunes incluyen:

Control manual o poco eficiente del inventario y fechas de vencimiento.

Dificultad para generar reportes de ventas y balances.

Procesos de facturación y pedidos lentos o desorganizados.

Falta de seguimiento a proveedores farmacéuticos y clientes frecuentes.

Riesgo de errores en la dispensación de medicamentos por falta de registros claros.

Este proyecto busca desarrollar un sistema que solucione estos problemas mediante una plataforma digital centralizada, optimizada y segura.

Integrante del equipo:
Dana Valentina Bernal León

Módulos:

Autenticación y gestión de usuarios → Control de accesos según rol.

Gestión de inventario de medicamentos → Registro de medicamentos, control de stock, fechas de vencimiento y alertas de caducidad.

Procesamiento de pedidos y facturación → Manejo de órdenes de clientes, generación de facturas y registro automático de ventas.

Gestión de clientes y proveedores → clientes frecuentes y proveedores farmacéuticos, con historial de compras y suministros.

Reportes y análisis de datos → Informes de ventas, productos más vendidos, control de vencimientos y balances generales.

Interfaz de usuario → Panel amigable y adaptado a la operación diaria de la droguería.

Tecnología:

Lenguaje de programación: JavaScript con Node.js (Backend).

Framework Backend: Express.js.

ORM: Sequelize (para facilitar el manejo de la base de datos sin escribir SQL directo).

Control de versiones: GitHub.

Framework de interfaz: React.js.

Seguridad: Autenticación con JWT, cifrado de contraseñas con bcrypt.

Base de Datos:

Node.js con Express.js y MySQL.

ORM: Sequelize (para gestión de datos simplificada).

Flujo de Sistema:

Inicio de sesión → Usuario accede con credenciales.

Gestión de inventario (Administrador) → Registro de medicamentos, actualización de stock y alertas de vencimiento.

Registro de pedidos (Vendedor) → Se ingresan compras de clientes.

Gestión de clientes y proveedores → Actualización de datos y seguimiento de historial de compras o suministros.

Consulta de reportes (Administrador) → Informes de ventas, inventario y balances.

Historial de compras (Administrador) → el Administrador puede consultar las compras previas.

Cierre de sesión → Finalización segura de la actividad en el sistema.

Roles de usuario y sus posibles interacciones:

Administrador:

Gestiona medicamentos, stock, pedidos, clientes y proveedores.

Genera reportes de ventas, vencimientos y análisis de datos.

Administra la seguridad y permisos del sistema.

Vendedor:

Registra pedidos y ventas.

Consulta disponibilidad de medicamentos en inventario.

Actualiza datos básicos de clientes.

No puede modificar inventario ni generar reportes.

Seguridad y Control de Accesos:

Autenticación: JWT para accesos seguros.

Autorización: Permisos según rol (mínimos necesarios para sus funciones).

Restricciones de acceso:

Administrador: acceso completo al sistema.

Vendedor: acceso limitado a registro de pedidos, ventas y gestión básica de clientes.

Protección de datos sensibles: Cifrado de contraseñas con bcrypt y políticas de acceso a la base de datos con roles diferenciados.

Alertas de seguridad: Registro de actividad de usuarios.
