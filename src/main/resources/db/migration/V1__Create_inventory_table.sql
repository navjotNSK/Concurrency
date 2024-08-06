CREATE TABLE inventory (
    id UUID PRIMARY KEY,
    product_id INT UNIQUE,
    product_name VARCHAR(255),
    product_type VARCHAR(100),
    stock_qty INT,
    price_per_unit DECIMAL(10,2),
    location VARCHAR(255)
);