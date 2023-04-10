BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC1', '1', 'series1', '2011-01-01', 'URL1');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC2', '2', 'series2', '2011-01-02', 'URL2');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC3', '3', 'series3', '2011-01-03', 'URL3');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC4', '4', 'series4', '2011-01-04', 'URL4');



COMMIT TRANSACTION;
