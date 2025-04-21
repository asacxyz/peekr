package com.asacxyz.peekr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.asacxyz.peekr.repository.filter.FilterOperatorStrategy;
import com.asacxyz.peekr.repository.filter.FilterOperatorStrategyFactory;
import com.asacxyz.peekr.model.Filter;
import com.asacxyz.peekr.model.Task;
import com.asacxyz.peekr.model.TaskStatus;
import com.asacxyz.peekr.model.User;

@Repository
public class TaskRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Task> tasksByFilters(List<Filter> filters) {
		StringBuilder sql = new StringBuilder();
		ArrayList<Object> parameters = new ArrayList<>();
		ArrayList<Task> tasks = new ArrayList<>();

		sql.append("""
				SELECT
				  tasks.id,
				  tasks.name,
				  tasks.owner_id,
				  people.name owner_name,
				  task_statuses.name status_name,
				  tasks.created_at,
				  tasks.completed_at
				FROM
				  tasks
				  LEFT JOIN people ON tasks.owner_id = people.id
				  LEFT JOIN task_statuses ON tasks.status_id = task_statuses.id
				WHERE 1=1
				        """);

		sql.append(filters.stream()
				.map(f -> {
					FilterOperatorStrategy strategy = FilterOperatorStrategyFactory.getInstance(f.operator());
					parameters.addAll(strategy.getParameters(f));
					return strategy.getSQL(f);
				})
				.collect(Collectors.joining(" AND ")));

		this.jdbcTemplate.query(sql.toString(), (rs, rowNum) -> this.addTask(tasks, rs), parameters.toArray());

		return tasks;
	}

	private boolean addTask(ArrayList<Task> tasks, ResultSet rs) throws SQLException {
		String id = rs.getString("id");
		String name = rs.getString("name");
		String ownerId = rs.getString("owner_id");
		String ownerName = rs.getString("owner_name");
		String statusName = rs.getString("status_name");
		String createdAt = rs.getString("created_at");
		String completedAt = rs.getString("completed_at");

		User owner = new User(ownerId, ownerName);
		TaskStatus status = TaskStatus.getElement(statusName);
		Task task = new Task(id, name, owner, status, createdAt, completedAt);

		return tasks.add(task);
	}
}
