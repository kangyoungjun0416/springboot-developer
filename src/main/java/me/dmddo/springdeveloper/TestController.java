package me.dmddo.springdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

//@Controller //기본적으로 메서드 리턴하는 것이  HTML 파일이름이다.
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test")
//    @ResponseBody
    public List<Member> getAllMembers() {
        return testService.getAllMembers();
    }
}
