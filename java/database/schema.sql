BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, collection, user_comic, comic_collection, character_comic, comic_data, character_table CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE collection (
    collection_id SERIAL,
    public boolean,
    user_id int REFERENCES users(user_id),
    collection_name varchar(200),
    CONSTRAINT PK_collection PRIMARY KEY (collection_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE comic_data(
    comic_data_id SERIAL,
    upc varchar(200),
    issue_number int,
    series varchar(500),
    publish_date DATE,
    image_url varchar(500),
    CONSTRAINT PK_comic_data_id PRIMARY KEY (comic_data_id)
);

CREATE TABLE character_table(
    character_id SERIAL,
    character_id_marvel_api int,
    alias varchar(200),
    real_name varchar(200),
    CONSTRAINT PK_character_id PRIMARY KEY (character_id)
);


CREATE TABLE comic_collection(
    collection_id int REFERENCES collection(collection_id),
    comic_id int REFERENCES user_comic(comic_id),
    CONSTRAINT FK_collection_id FOREIGN KEY (collection_id) REFERENCES collection(collection_id),
    CONSTRAINT FK_comic_id FOREIGN KEY (comic_id) REFERENCES user_comic(comic_id)
);

CREATE TABLE character_comic (
    character_id int REFERENCES character_table(character_id),
    comic_data_id int REFERENCES comic_data(comic_data_id),
    CONSTRAINT FK_character_id FOREIGN KEY (character_id) REFERENCES character_table(character_id),
    CONSTRAINT FK_comic_data_id FOREIGN KEY (comic_data_id) REFERENCES comic_data(comic_data_id)
);


COMMIT TRANSACTION;
