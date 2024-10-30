package com.project2.Project22.controller;

import com.project2.Project22.model.Student;
import com.project2.Project22.service.StudentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class myController {
    @Autowired
    private StudentServ StudentServ;

    @GetMapping("/students/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return StudentServ.findStudName(name);
    }
}
