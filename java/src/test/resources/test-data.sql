BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC1', '1', 'series1', '2011-01-01', 'URL1');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC2', '2', 'series2', '2011-01-02', 'URL2');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC3', '3', 'series3', '2011-01-03', 'URL3');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC5', '4', 'series4', '2011-01-04', 'URL4');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC6', '5', 'series5', '2011-01-05', 'URL5');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC7', '6', 'series6', '2011-01-06', 'URL6');
INSERT INTO comic_data (upc,issue_number,series,publish_date,image_url) VALUES ('UPC8', '7', 'series7', '2011-01-07', 'URL7');

INSERT INTO collection (public, user_id, collection_name) VALUES (true, 1, 'collection1');
INSERT INTO collection (public, user_id, collection_name) VALUES (false, 1, 'collection2');
INSERT INTO collection (public, user_id, collection_name) VALUES (true, 1, 'collection3');
INSERT INTO collection (public, user_id, collection_name) VALUES (false, 1, 'collection4');
INSERT INTO collection (public, user_id, collection_name) VALUES (true, 2, 'collection5');
INSERT INTO collection (public, user_id, collection_name) VALUES (false, 2, 'collection6');
INSERT INTO collection (public, user_id, collection_name) VALUES (true, 3, 'collection7');

INSERT INTO character_table (character_id_marvel_api, alias, real_name) VALUES (1, 'alias1', 'name1');
INSERT INTO character_table (character_id_marvel_api, alias, real_name) VALUES (2, 'alias2', 'name2');
INSERT INTO character_table (character_id_marvel_api, alias, real_name) VALUES (3, 'alias3', 'name3');
INSERT INTO character_table (character_id_marvel_api, alias, real_name) VALUES (4, 'alias4', 'name4');
INSERT INTO character_table (character_id_marvel_api, alias, real_name) VALUES (5, 'alias5', 'name5');

INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (1, 1);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (3, 1);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (1, 2);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (1, 3);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (2, 1);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (3, 4);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (4, 5);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (5, 6);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (6, 7);
INSERT INTO comic_collection (collection_id, comic_data_id) VALUES (7, 7);

INSERT INTO character_comic (character_id, comic_data_id) VALUES (1, 1);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (1, 2);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (1, 3);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (1, 4);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (1, 5);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (2, 1);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (2, 5);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (3, 3);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (3, 4);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (4, 5);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (4, 6);
INSERT INTO character_comic (character_id, comic_data_id) VALUES (5, 7);


COMMIT TRANSACTION;
