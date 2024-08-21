CREATE TABLE if not exists Product(
    productId INT PRIMARY KEY AUTO_INCREMENT,
    productName TEXT,
    price FLOAT
    
);

CREATE TABLE if not exists  Review(
    reviewId INT PRIMARY KEY AUTO_INCREMENT,
    reviewContent TEXT,
    rating   INT ,
    productId INT,
    FOREIGN KEY (productId) REFERENCES product(productId)
); 