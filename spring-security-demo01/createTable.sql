CREATE DATABASE `security` CHARACTER SET utf8;

CREATE TABLE persistent_logins (
username VARCHAR (64) NOT NULL,
series VARCHAR (64) PRIMARY KEY,
token VARCHAR (64) NOT NULL,
last_used TIMESTAMP NOT NULL
);

SELECT * FROM persistent_logins;

CREATE TABLE t_admin
(
	id INT NOT NULL AUTO_INCREMENT,
	loginacct VARCHAR(255) NOT NULL,
	userpswd CHAR(100) NOT NULL,
	username VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	createtime CHAR(19),
	PRIMARY KEY (id)
);


SELECT id, loginacct "loginAcct", userPswd "loginPswd", username, email FROM t_admin WHERE loginacct = 'tom';
INSERT INTO t_admin(loginacct, username, userpswd, email)
VALUES('tom', '汤姆', '$2a$10$BXgjWvXB70uf45cUruiaCOidjv/mqSW2n2jTsrYcZ5rxzSwLgKFiK', 'tom123@qq.com');
