package me.dmddo.springdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    // http://localhost/student?firstName=SungChul&lastName=Park
    @GetMapping("/student")
    public Student getStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return new Student(firstName, lastName);
    }


    @GetMapping("/student/{fristName}/{lastName}")
    public Student getMapping(@PathVariable("fristName") String firstName, @PathVariable("lastName") String lastName) {
        return new Student(firstName, lastName);
    }
}