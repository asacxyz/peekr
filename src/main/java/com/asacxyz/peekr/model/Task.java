package com.asacxyz.peekr.model;

public record Task(String id, String name, User owner, TaskStatus status, String createdAt, String completedAt) {
}
