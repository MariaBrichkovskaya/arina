package com.course.project.arina.enums;

import lombok.Getter;

@Getter
public enum Status {

    PENDING(1, "Обрабатывается"),
    APPROVED(2, "Одобрен"),
    DENIED(3, "Отклонен");

    private final int number;
    private final String valueStr;
    Status(int number, String valueStr) {
        this.number = number;
        this.valueStr = valueStr;
    }
}
