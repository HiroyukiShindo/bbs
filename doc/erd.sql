SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS t_comment;
DROP TABLE IF EXISTS t_article;
DROP TABLE IF EXISTS t_user;




/* Create Tables */

CREATE TABLE t_article
(
	article_id int unsigned zerofill NOT NULL AUTO_INCREMENT,
	user_id int unsigned zerofill NOT NULL,
	text_header varchar(100) NOT NULL,
	text_body varchar(2000) NOT NULL,
	regist_date datetime NOT NULL,
	update_date datetime NOT NULL,
	PRIMARY KEY (article_id)
);


CREATE TABLE t_comment
(
	comment_id int unsigned zerofill NOT NULL AUTO_INCREMENT,
	user_id int unsigned zerofill NOT NULL,
	article_id int unsigned zerofill NOT NULL,
	comment_body varchar(2000) NOT NULL,
	regist_date datetime NOT NULL,
	update_date datetime NOT NULL,
	PRIMARY KEY (comment_id)
);


CREATE TABLE t_user
(
	user_id int unsigned zerofill NOT NULL AUTO_INCREMENT,
	login_id varchar(20) NOT NULL,
	login_password varchar(20) NOT NULL,
	login_flg int(1) DEFAULT 0 NOT NULL,
	administrator_flg int(1) DEFAULT 0 NOT NULL,
	first_name varchar(10) NOT NULL,
	last_name varchar(10) NOT NULL,
	regist_date datetime NOT NULL,
	update_date datetime NOT NULL,
	PRIMARY KEY (user_id)
);



/* Create Foreign Keys */

ALTER TABLE t_comment
	ADD FOREIGN KEY (article_id)
	REFERENCES t_article (article_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_article
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



