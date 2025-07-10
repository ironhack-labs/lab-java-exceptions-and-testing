INSERT INTO employee (employee_id, department, name, status) VALUES
(356712, 'cardiology', 'Alonso Flores', 'ON_CALL'),
(564134, 'immunology', 'Sam Ortega', 'ON'),
(761527, 'cardiology', 'German Ruiz', 'OFF'),
(166552, 'pulmonary', 'Maria Lin', 'ON'),
(156545, 'orthopaedic', 'Paolo Rodriguez', 'ON_CALL'),
(172456, 'psychiatric', 'John Paul Armes', 'OFF');

INSERT INTO patient (patient_id, name, date_of_birth, admitted_by_employee_id) VALUES
(1, 'Jaime Jordan', '1984-03-02', 564134),
(2, 'Marian Garcia', '1972-01-12', 564134),
(3, 'Julia Dusterdieck', '1954-06-11', 356712),
(4, 'Steve McDuck', '1931-11-10', 761527),
(5, 'Marian Garcia', '1999-02-15', 172456);
