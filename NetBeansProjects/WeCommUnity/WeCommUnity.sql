# -----------------------------------------
# SQL script to create the tables for the 
# WeCommUnity Database
# Created by Eman Abdelrahman for WeCommUnity app
# -----------------------------------------

/*
Tables to be dropped must be listed in a logical order based on dependency.
UserFile depend on User. Therefore, they must be dropped before User. Same for Commmunity
*/
DROP TABLE IF EXISTS UserContent, AccessedFile, UserCommunity, UserInterest, PublicFile, Message, User ;
DROP TABLE IF EXISTS UserContent, UserCommunity, CommunityInterest,CommunityContent, Community;
/* The User table contains attributes of interest of a User. */
CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,          /* To store Salted and Hashed Password Parts */
    first_name VARCHAR(32) NOT NULL,
    last_name VARCHAR(32) NOT NULL,
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    email VARCHAR(128) NOT NULL,      
    biography MEDIUMTEXT NOT NULL,
    PRIMARY KEY (id)
);

/* The UserPhoto table contains attributes of interest of a user's photo. */
CREATE TABLE UserPhoto
(
       id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
       extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
       user_id INT UNSIGNED,
       FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE Community
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    communityname VARCHAR(32) NOT NULL,
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    description MEDIUMTEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Interest 
(
    id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    name VARCHAR(32) NOT NULL,

    PRIMARY KEY (id)
);

/*Junction Table*/
CREATE TABLE UserCommunity
(
    user_id INT UNSIGNED NOT NULL,
    community_id INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (community_id) REFERENCES Community(id) ON DELETE CASCADE
);

/*Junction Table*/
CREATE TABLE UserInterest
(
    user_id INT UNSIGNED ,
    interest_id INT UNSIGNED,
    
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/*Junction Table*/
CREATE TABLE CommunityInterest
(
    community_id INT UNSIGNED NOT NULL,
    interest_id INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (community_id) REFERENCES Community(id) ON DELETE CASCADE
);

/* The FileContent table contains attributes of interest of a user's uploaded file. */
CREATE TABLE UserContent
(
       id INT UNSIGNED AUTO_INCREMENT NOT NULL AUTO_INCREMENT,
       contentname VARCHAR(256) NOT NULL,
       date_entered DATE NOT NULL,    /* YYYY-MM-DD */
       creator_id INT UNSIGNED NOT NULL,
       category VARCHAR (32) NOT NULL,
       type VARCHAR(256), /*video, audio, text document*/
       location VARCHAR(256),/*for metadata purposes- Dublin core metadata */

       PRIMARY KEY(id),
       FOREIGN KEY (creator_id) REFERENCES User(id) ON DELETE CASCADE
);

/*Junction Table: Users who have access to this file*/
CREATE TABLE AccessedContent
(
    user_id INT UNSIGNED,
    content_id INT UNSIGNED,
    
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (content_id) REFERENCES UserContent(id) ON DELETE CASCADE
);

CREATE TABLE CommunityContent
(
       id INT UNSIGNED AUTO_INCREMENT NOT NULL,
       contentname VARCHAR(256) NOT NULL,
       date_entered DATE NOT NULL,    /* YYYY-MM-DD */
       community_id INT UNSIGNED,
       category VARCHAR (32) NOT NULL,
       
       PRIMARY KEY (id),
       FOREIGN KEY (community_id) REFERENCES Community(id) ON DELETE CASCADE
);

CREATE TABLE PublicContent
(
       id INT UNSIGNED AUTO_INCREMENT NOT NULL,
       publicContentame VARCHAR(256) NOT NULL,
       date_entered DATE NOT NULL,    /* YYYY-MM-DD */
       category VARCHAR (32) NOT NULL,

       PRIMARY KEY (id)
);

CREATE TABLE Message /*messages are 1:1 */
(
    id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    subject VARCHAR (256) NOT NULL,
    body MEDIUMTEXT NOT NULL, /* Max 16,777,215 characters for storing survey in JSON */
    sender_id INT UNSIGNED,
    recipient_id INT UNSIGNED,
    timestamp DATE NOT NULL,    /* YYYY-MM-DD */
    
    PRIMARY KEY (id),
    FOREIGN KEY (sender_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (recipient_id) REFERENCES User(id) ON DELETE CASCADE
    

);