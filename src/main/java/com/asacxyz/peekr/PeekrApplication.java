package com.asacxyz.peekr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PeekrApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(PeekrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = """
				DROP TABLE IF EXISTS tasks;
				DROP TABLE IF EXISTS task_statuses;
				DROP TABLE IF EXISTS people;

				CREATE TABLE people (
				  id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
				  name VARCHAR(100) NOT NULL
				);

				CREATE TABLE task_statuses (
				  id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
				  name VARCHAR(25) NOT NULL,
				  CONSTRAINT check_status_unique_name UNIQUE (name)
				);

				INSERT INTO
				  task_statuses (name)
				VALUES
				  ('not_initialized'),
				  ('in_progress'),
				  ('completed');

				CREATE TABLE tasks (
				  id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
				  name VARCHAR(100) NOT NULL,
				  owner_id INTEGER NOT NULL REFERENCES people (id),
				  status_id INTEGER NOT NULL REFERENCES task_statuses (id),
				  created_at DATE NOT NULL,
				  completed_at DATE
				);

				INSERT INTO
				  people (name)
				VALUES
				  ('ownername');

				INSERT INTO
				  tasks (
					name,
					owner_id,
					status_id,
					created_at,
					completed_at
				  )
				VALUES
				  ('t1', 1, 1, now(), NULL),
				  ('t2', 1, 2, now() - INTERVAL '1 day', NULL),
				  ('t3', 1, 3, now() - INTERVAL '2 day', now() - INTERVAL '1 day');

				CREATE INDEX idx_tasks_owner_id ON tasks(owner_id);
				CREATE INDEX idx_tasks_status_id ON tasks(status_id);
				""";
		this.jdbcTemplate.execute(sql);
	}
}
