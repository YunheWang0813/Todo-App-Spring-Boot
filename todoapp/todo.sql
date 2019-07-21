USE mydb;

DROP TABLE if exists task;

CREATE TABLE if not exists task(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(32) NOT NULL,
	due_date datetime,
	create_date datetime DEFAULT CURRENT_TIMESTAMP,
	is_completed BOOLEAN default 0
);

INSERT INTO task (name, due_date) VALUES 
('タスク1', '2020-08-08'),
('タスク2', '2020-09-08'),
('タスク3', '2021-01-08'),
('タスク4', '2022-04-08'),
('タスク5', '2023-09-08');

SELECT * FROM task;
