package com.project2.Project22.service;



import com.project2.Project22.model.Course;
import com.project2.Project22.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServ {
    private final String url = "https://hccs-advancejava.s3.amazonaws.com/student_course.json";
    private List<Student> students;

    public StudentServ() throws IOException {
       listStudents();
    }

    private void listStudents() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        students = objectMapper.readValue(json, new TypeReference<List<Student>>() {});
    }

    public List<Student> getStudents() {
        return students;
    }

    public double calculateGPA(Student student) {
        double stuPoints = 0;
        int stuCreds= 0;

        for (Course course : student.getCourses()) {
            int points = convertGradeToPoints(course.getGrade());
            stuPoints += points * course.getCreditHours();
            stuCreds += course.getCreditHours();
        }
        return stuCreds > 0 ? stuPoints / stuCreds : 0;
    }

    private int convertGradeToPoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A": return 4;
            case "B": return 3;
            case "C": return 2;
            case "D": return 1;
            case "F": return 0;
            default: return 0;
        }
    }

	public List<Student> findStudName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
