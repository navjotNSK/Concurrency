CREATE TABLE salary (
    id UUID PRIMARY KEY,
    sal DOUBLE,
    mon VARCHAR(255),
    emp_id INT,
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);