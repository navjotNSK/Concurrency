CREATE TABLE orders (
    id UUID PRIMARY KEY,
    product_id INT,
    name VARCHAR(255),
    product_type VARCHAR(100),
    qty INT,
    price DECIMAL(10,2),
    tracking_id VARCHAR(255)
);