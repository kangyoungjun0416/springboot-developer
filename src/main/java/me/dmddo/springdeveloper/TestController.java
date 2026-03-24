package me.dmddo.springdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//@Controller //기본적으로 메서드 리턴하는 것이  HTML 파일이름이다.
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test")
//    @ResponseBody
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(testService.getAllMembers());
    }

    @PostMapping("/test")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.ok(testService.saveMember(member));
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test() {
        return new ResponseEntity("Hello World", HttpStatus.OK);
    }
}