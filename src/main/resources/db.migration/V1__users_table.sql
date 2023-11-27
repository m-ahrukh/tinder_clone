CREATE TABLE users (
    id SERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) ,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    age_pref int,
    gender_pref VARCHAR(6),
    distance_pref FLOAT,
    bio VARCHAR(255),
    looking_for varchar(100)
);

CREATE TABLE IF NOT EXISTS interests (
    id SERIAL NOT NULL PRIMARY KEY,
    interest VARCHAR(20) NOT NULL,

    user_id int,
    CONSTRAINT fk_user_id
        FOREIGN KEY(user_id)
        REFERENCES Users(id)
)
