USE labsoft;

INSERT INTO duenos (id, nombre, telefono, correo, direccion) VALUES
(1, 'Carlos Andrés Ramírez', '3158901234', 'carlos.ramirez@gmail.com', 'Carrera 25 # 14-30, Tuluá'),
(2, 'María Fernanda Gómez', '3104567890', 'maria.gomez@hotmail.com', 'Calle 10 # 5-20, Tuluá'),
(3, 'Juan Pablo Morales', '3207891234', 'juan.morales@univalle.edu.co', 'Avenida Las Palmas # 12-45, Tuluá'),
(4, 'Diana Marcela Castro', '3006549871', 'diana.castro@gmail.com', 'Calle 28 # 19-15, Tuluá'),
(5, 'Andrés Felipe Restrepo', '3189998877', 'andres.restrepo@outlook.com', 'Carrera 40 # 22-10, Tuluá')
ON DUPLICATE KEY UPDATE nombre=VALUES(nombre);

INSERT INTO mascotas (id, nombre, especie, raza, edad, dueno_id) VALUES
(1, 'Rocky', 'Perro', 'Golden Retriever', 4, 1),
(2, 'Luna', 'Gato', 'Siamés', 2, 2),
(3, 'Max', 'Perro', 'Bulldog Francés', 3, 1),
(4, 'Pelusa', 'Conejo', 'Cabeza de León', 1, 3),
(5, 'Thor', 'Perro', 'Pastor Alemán', 5, 4),
(6, 'Misi', 'Gato', 'Persa', 6, 5)
ON DUPLICATE KEY UPDATE nombre=VALUES(nombre);

INSERT INTO citas (id, fecha, motivo, veterinario, mascota_id) VALUES
(1, '2026-11-15', 'Vacunación antirrábica y desparasitación anual', 'Dra. Laura Hernández', 1),
(2, '2026-11-18', 'Control posoperatorio y retiro de puntos', 'Dr. Juan Pablo Pinillos', 2),
(3, '2026-11-20', 'Chequeo dermatológico por irritación en la piel', 'Dr. Juan Pablo Pinillos', 3),
(4, '2026-11-22', 'Revisión preventiva de crecimiento de dientes', 'Dra. Laura Hernández', 4),
(5, '2026-11-25', 'Dolor en pata delantera izquierda tras paseo', 'Dr. Camilo Torres', 5),
(6, '2026-11-28', 'Limpieza dental profunda', 'Dra. Laura Hernández', 6)
ON DUPLICATE KEY UPDATE motivo=VALUES(motivo);

INSERT INTO tratamientos (id, descripcion, dosis, cita_id) VALUES
(1, 'Vacuna Rabisin + Antiparasitario NexGard Spectra', 'Dosis única oral y subcutánea', 1),
(2, 'Limpieza antiséptica con Clorhexidina al 2% y pomada cicatrizante', 'Aplicar cada 12 horas por 5 días', 2),
(3, 'Cefalexina 500mg (Antibiótico)', '1 tableta cada 12 horas durante 10 días', 3),
(4, 'Champú hipoalergénico medicado de avena y aloe', 'Baño cada 4 días durante 3 semanas', 3),
(5, 'Corte de incisivos y suplementación con heno fresco timothy', 'Procedimiento único en consultorio', 4),
(6, 'Meloxicam suspensión oral 0.5% (Antiinflamatorio/Analgésico)', '6 gotas cada 24 horas por 4 días con comida', 5),
(7, 'Gel antiséptico bucal con clorhexidina', 'Aplicar en encías 1 vez al día tras limpieza', 6)
ON DUPLICATE KEY UPDATE descripcion=VALUES(descripcion);
