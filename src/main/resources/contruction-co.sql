create database ConstructionCo;

CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Contractors` (
  `contractor_id` INT NOT NULL,
  `contractor_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`contractor_id`)
);

CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Purchase_Order` (
  `purchase_order_id` INT NOT NULL,
  `purchaseorder_name` VARCHAR(50) NULL,
  `budget` INT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`purchase_order_id`));

CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Customers` (
  `customer_id` INT NOT NULL,
  `customer_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`));
  
  CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Employee_Tasks` (
  `task_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `due_date` DATE NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`task_id`, `employee_id`),
    FOREIGN KEY (`task_id`)
    REFERENCES `ConstructionCo`.`Tasks` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`employee_id`)
    REFERENCES `ConstructionCo`.`Employees` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Employees` (
  `employee_id` INT NOT NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(10) NULL,
  `hire_date` VARCHAR(45) NULL,
  `position` VARCHAR(45) NULL,
  PRIMARY KEY (`employee_id`));
    
    CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Equipment` (
  `equipment_id` INT NOT NULL,
  `equipment_name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`equipment_id`));
  
  CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Invoices` (
  `invoice_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `contractor_id` INT NOT NULL,
  `due_date` DATE NULL,
  `total_due` INT NULL,
  PRIMARY KEY (`invoice_id`),
    FOREIGN KEY (`invoice_id`)
    REFERENCES `ConstructionCo`.`Purchase_Order` (`purchase_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`customer_id`)
    REFERENCES `ConstructionCo`.`Customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`contractor_id`)
    REFERENCES `ConstructionCo`.`Contractors` (`contractor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
    CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Materials` (
  `material_id` INT NOT NULL,
  `material_name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `price_per_unit` VARCHAR(45) NULL,
  PRIMARY KEY (`material_id`),
    FOREIGN KEY (`material_id`)
    REFERENCES `ConstructionCo`.`Suppliers` (`supplier_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Project_Equipment` (
  `purchase_order_id` INT NOT NULL,
  `equipment_id` INT NOT NULL,
  PRIMARY KEY (`purchase_order_id`, `equipment_id`),
  INDEX `equipment_id_idx` (`equipment_id` ASC) VISIBLE,
    FOREIGN KEY (`purchase_order_id`)
    REFERENCES `ConstructionCo`.`Purchase_Order` (`purchase_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`equipment_id`)
    REFERENCES `ConstructionCo`.`Equipment` (`equipment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Purchase_Order_Materials` (
  `purchase_order_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  PRIMARY KEY (`purchase_order_id`, `material_id`),
  INDEX `material_id_idx` (`material_id` ASC) VISIBLE,
    FOREIGN KEY (`purchase_order_id`)
    REFERENCES `ConstructionCo`.`Purchase_Order` (`purchase_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`material_id`)
    REFERENCES `ConstructionCo`.`Materials` (`material_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Suppliers` (
  `supplier_id` INT NOT NULL,
  `supplier_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`supplier_id`));
    
    
CREATE TABLE IF NOT EXISTS `ConstructionCo`.`Tasks` (
  `task_id` INT NOT NULL,
  `task_name` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `due_date` DATE NULL,
  PRIMARY KEY (`task_id`),
    FOREIGN KEY (`task_id`)
    REFERENCES `ConstructionCo`.`Purchase_Order` (`purchase_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
------------------------------------------------------------------------------------------------------------------------
    
INSERT INTO ConstructionCo.Contractors VALUES (1, 'John Smith', 'johnsmith@gmail.com', '123456789', 'Address 1');

INSERT INTO ConstructionCo.Purchase_Order VALUES (1, 'John Smtih Order', 10000, 'Pending');

INSERT INTO ConstructionCo.Customers VALUES (1, 'Emma Johnson', 'emmajohnson@gmail.com', '987654321', 'Address 1');

INSERT INTO ConstructionCo.Employees VALUES (1, 'John', 'Doe', 'johndoe@gmail.com', '1234567890', '2023-01-01', 'Manager');

INSERT INTO ConstructionCo.Equipment VALUES (1, 'Jack Hammer', 'Description 1', 1);

INSERT INTO ConstructionCo.Invoices VALUES (1, 1, 1, '2023-05-30', 5000);

INSERT INTO ConstructionCo.Materials VALUES (1, 'Ply Wood', 'Description 1', '10.00');

INSERT INTO ConstructionCo.Project_Equipment VALUES (1, 1);

INSERT INTO ConstructionCo.Purchase_Order_Materials VALUES (1, 1);

INSERT INTO ConstructionCo.Suppliers VALUES (1, 'ABC Supplier', 'supplierABC@gmail.com');
------------------------------------------------------------------------------------------------------------------------

UPDATE ConstructionCo.Contractors SET contractor_name = 'John Johnson' WHERE contractor_id = 1;

UPDATE ConstructionCo.Purchase_Order SET purchaseorder_name = 'John Johnson Order' WHERE purchase_order_id = 1;

UPDATE ConstructionCo.Customers SET customer_name = 'Emma Smith' WHERE customer_id = 1;

UPDATE ConstructionCo.Employees SET first_name = 'Jane' WHERE employee_id = 1;

UPDATE ConstructionCo.Equipment SET equipment_name = 'Drill Machine' WHERE equipment_id = 1;

UPDATE ConstructionCo.Invoices SET total_due = 6000 WHERE invoice_id = 1;

UPDATE ConstructionCo.Materials SET material_name = 'Steel Bars' WHERE material_id = 1;

UPDATE ConstructionCo.Project_Equipment SET equipment_id = 2 WHERE purchase_order_id = 1;

UPDATE ConstructionCo.Purchase_Order_Materials SET material_id = 2 WHERE purchase_order_id = 1;

UPDATE ConstructionCo.Suppliers SET supplier_name = 'XYZ Supplier' WHERE supplier_id = 1;
------------------------------------------------------------------------------------------------------------------------

DELETE FROM ConstructionCo.Contractors
    WHERE contractor_id = 1;

DELETE FROM ConstructionCo.Purchase_Order
    WHERE purchase_order_id = 1;

DELETE FROM ConstructionCo.Customers
    WHERE customer_id = 1;

DELETE FROM ConstructionCo.Employees
    WHERE employee_id = 1;

DELETE FROM ConstructionCo.Equipment
    WHERE equipment_id = 1;

DELETE FROM ConstructionCo.Invoices
    WHERE invoice_id = 1;

DELETE FROM ConstructionCo.Materials
    WHERE material_id = 1;

DELETE FROM ConstructionCo.Project_Equipment
    WHERE purchase_order_id = 1;

DELETE FROM ConstructionCo.Purchase_Order_Materials
    WHERE purchase_order_id = 1;

DELETE FROM ConstructionCo.Suppliers
    WHERE supplier_id = 1;
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE ConstructionCo.Invoices
    ADD COLUMN payment_status VARCHAR(20) NULL;

ALTER TABLE ConstructionCo.Materials
    CHANGE COLUMN price_per_unit price_per_unit DECIMAL(10,2) NULL;

ALTER TABLE ConstructionCo.Contractors
    ADD COLUMN rating INT NULL;

ALTER TABLE ConstructionCo.Customers
    MODIFY COLUMN phone_number VARCHAR(20);

ALTER TABLE ConstructionCo.Employees
    DROP COLUMN hire_date;
------------------------------------------------------------------------------------------------------------------------
    
SELECT * FROM ConstructionCo.Contractors
    JOIN ConstructionCo.Purchase_Order ON Contractors.contractor_id = Purchase_Order.contractor_id
    JOIN ConstructionCo.Customers ON Customers.customer_id = Purchase_Order.customer_id
    JOIN ConstructionCo.Invoices ON Invoices.invoice_id = Purchase_Order.purchase_order_id
    JOIN ConstructionCo.Employees ON Employees.employee_id = Invoices.contractor_id
    JOIN ConstructionCo.Employee_Tasks ON Employee_Tasks.employee_id = Employees.employee_id
    JOIN ConstructionCo.Tasks ON Tasks.task_id = Employee_Tasks.task_id
    JOIN ConstructionCo.Equipment ON Equipment.equipment_id = Purchase_Order.purchase_order_id
    JOIN ConstructionCo.Project_Equipment ON Project_Equipment.purchase_order_id = Purchase_Order.purchase_order_id
    JOIN ConstructionCo.Materials ON Materials.material_id = Project_Equipment.material_id
    JOIN ConstructionCo.Purchase_Order_Materials ON Purchase_Order_Materials.purchase_order_id = Purchase_Order.purchase_order_id
    JOIN ConstructionCo.Suppliers ON Suppliers.supplier_id = Materials.material_id;
------------------------------------------------------------------------------------------------------------------------

SELECT * FROM Customers
    LEFT JOIN Invoices ON Customers.customer_id = Invoices.customer_id;

SELECT *FROM Contractors
    LEFT JOIN Purchase_Order ON Contractors.contractor_id = Purchase_Order.contractor_id;

SELECT * FROM Employees
    LEFT JOIN Employee_Tasks ON Employees.employee_id = Employee_Tasks.employee_id;

SELECT * FROM Purchase_Order
    LEFT JOIN Project_Equipment ON Purchase_Order.purchase_order_id = Project_Equipment.purchase_order_id;

SELECT * FROM Materials
    LEFT JOIN Purchase_Order_Materials ON Materials.material_id = Purchase_Order_Materials.material_id;
------------------------------------------------------------------------------------------------------------------------

SELECT * FROM Customers
    RIGHT JOIN Invoices ON Customers.customer_id = Invoices.customer_id;

SELECT * FROM Contractors
    RIGHT JOIN Purchase_Order ON Contractors.contractor_id = Purchase_Order.contractor_id;

SELECT * FROM Employees
    RIGHT JOIN Employee_Tasks ON Employees.employee_id = Employee_Tasks.employee_id;

SELECT * FROM Purchase_Order
    RIGHT JOIN Project_Equipment ON Purchase_Order.purchase_order_id = Project_Equipment.purchase_order_id;

SELECT * FROM Materials
    RIGHT JOIN Purchase_Order_Materials ON Materials.material_id = Purchase_Order_Materials.material_id;

------------------------------------------------------------------------------------------------------------------------

SELECT contractor_id, Contractors.contractor_name, Purchase_Order.purchaseorder_name FROM Contractors
    INNER JOIN Purchase_Order ON contractor_id = Purchase_Order.contractor_id;

SELECT customer_id, customer_name, Invoices.due_date FROM Customers
    INNER JOIN Invoices ON customer_id = Invoices.customer_id;

SELECT employee_id, first_name, Employee_Tasks.due_date FROM Employees
    INNER JOIN Employee_Tasks ON Employees.employee_id = Employee_Tasks.employee_id;

SELECT Purchase_Order.purchase_order_id, Project_Equipment.equipment_id FROM Purchase_Order
    INNER JOIN Project_Equipment ON Purchase_Order.purchase_order_id = Project_Equipment.purchase_order_id;

SELECT material_id, purchase_order_id FROM Materials
    INNER JOIN Purchase_Order_Materials ON Materials.material_id = Purchase_Order_Materials.material_id;

------------------------------------------------------------------------------------------------------------------------
SELECT customer_id, Invoices.invoice_id FROM Customers
    LEFT JOIN Invoices ON Customers.customer_id = Invoices.customer_id;

SELECT equipment_id, Project_Equipment.purchase_order_id FROM Equipment
    RIGHT JOIN Project_Equipment ON Equipment.equipment_id = Project_Equipment.equipment_id;

SELECT C.contractor_id, PO.purchase_order_id FROM Contractors C
    LEFT JOIN Purchase_Order PO ON C.contractor_id = PO.contractor_id;

SELECT C.contractor_id, PO.purchase_order_id FROM Contractors C
    RIGHT JOIN Purchase_Order PO ON C.contractor_id = PO.contractor_id;

SELECT C.contractor_name, PO.purchaseorder_name FROM Contractors C
    LEFT JOIN Purchase_Order PO ON C.contractor_id = PO.contractor_id
    -- Joins both select statements --
    UNION
    SELECT C.contractor_name, PO.purchaseorder_name FROM Contractors C
    RIGHT JOIN Purchase_Order PO ON C.contractor_id = PO.contractor_id
    WHERE C.contractor_id IS NULL;
-------------------------------------------------------------------------------------------------------

SELECT COUNT(*) FROM Contractors
       GROUP BY contractor_id;

SELECT SUM(budget) FROM Purchase_Order
       GROUP BY status;

SELECT AVG(total_due) FROM Invoices
       GROUP BY contractor_id;

SELECT MIN(budget) FROM Purchase_Order
       GROUP BY status;

SELECT MAX(budget) FROM Purchase_Order
       GROUP BY status;

SELECT GROUP_CONCAT(contractor_name) FROM Contractors
       GROUP BY email;

SELECT STD(price_per_unit) FROM Materials
       GROUP BY material_id;
---------------------------------------------------------------

SELECT COUNT(*) FROM Contractors
       GROUP BY contractor_id HAVING COUNT(*) > 5;

SELECT SUM(budget) FROM Purchase_Order
       GROUP BY status HAVING SUM(budget) > 10000;

SELECT AVG(total_due) FROM Invoices
       GROUP BY contractor_id HAVING AVG(total_due) > 5000;

SELECT MIN(budget) FROM Purchase_Order
       GROUP BY status HAVING MIN(budget) > 5000;

SELECT MAX(budget) FROM Purchase_Order
       GROUP BY status HAVING MAX(budget) < 10000;

SELECT GROUP_CONCAT(contractor_name) FROM Contractors
       GROUP BY email HAVING COUNT(*) > 1;

SELECT STD(price_per_unit) FROM Materials
       GROUP BY material_id HAVING STD(price_per_unit) > 10;
