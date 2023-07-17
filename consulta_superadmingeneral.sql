SELECT e.*, r.nombre AS nombreRol FROM empleado e, rol r
WHERE e.idRol = r.idRol
AND (e.usuario = 'diego' OR e.email = 'diego')
AND e.password = '123'
AND e.idSucursal IS NULL;


SELECT e.*, r.nombre AS nombreRol FROM empleado e, rol r
WHERE e.idRol = r.idRol
AND (e.usuario = 'diego.paniagua.kanon89@gmail.com' OR e.email = 'diego.paniagua.kanon89@gmail.com')
AND e.password = '123'
AND e.idSucursal IS NULL;