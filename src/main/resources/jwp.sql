DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS ( 
	userId          varchar(12)		NOT NULL, 
	password		varchar(12)		NOT NULL,
	name			varchar(20)		NOT NULL,
	email			varchar(50),	
  	
	PRIMARY KEY               (userId)
);

INSERT INTO USERS VALUES('test', 'test123', '임은지', 'dmswldla91@naver.com');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
	questionId 			bigint				auto_increment,
	writer				varchar(30)			NOT NULL,
	title				varchar(50)			NOT NULL,
	contents			varchar(5000)		NOT NULL,
	createdDate			timestamp			NOT NULL,
	countOfAnswer int,
	PRIMARY KEY               (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES
('testid',
'testtitle', 
'testcontents',
CURRENT_TIMESTAMP());


CREATE TABLE ANSWERS (
	answerId 			bigint				auto_increment,
	writer				varchar(30)			NOT NULL,
	contents			varchar(5000)		NOT NULL,
	createdDate			timestamp			NOT NULL,
	questionId			bigint				NOT NULL,				
	PRIMARY KEY         (answerId)
);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('testid', 
'testcontents',
CURRENT_TIMESTAMP(),
1);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
('testid', 
'testcontents',
CURRENT_TIMESTAMP(),
1);
