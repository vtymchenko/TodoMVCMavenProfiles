package com.herokuapp.todomvc4tasj.helpers;

/**
 * Created by Valentyn on 05.04.2016.
 */
public enum TaskType {
    ACTIVE("false"),COMPLETED ("true") ;

    private String status;

    TaskType(String status) {
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}


