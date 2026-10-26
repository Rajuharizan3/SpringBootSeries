package com.onlyrj.crud_demo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {

    @Size(min = 2, max = 55, message = "Student name must be 2 to 55 character long")
    @NotBlank(message = "Name cannot be null/empty or blank")
    private String name;

    @NotNull(message = "Student email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 15, message = "Age must be at least 15 years old")
    private Integer age;

    @NotNull(message = "RollNo is required")
    private Integer rollNo;

    @NotBlank(message = "Subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
