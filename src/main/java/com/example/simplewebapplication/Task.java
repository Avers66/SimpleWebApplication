package com.example.simplewebapplication;

import lombok.Data;

/**
 * Task
 *
 * @Author Tretyakov Alexandr
 */

@Data
public class Task {

    private Long id;
    private String title;
    private String Description;
    private int priority;
}
