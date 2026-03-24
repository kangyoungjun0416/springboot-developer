package me.dmddo.springdeveloper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// ✅ 필수: 아래 static import들이 정확히 있어야 함
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest2 {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get /test2 요청 시 Hello World 반환")
    void getTestAPI() throws Exception {
        // When & Then
        mockMvc.perform(get("/test2"))
                .andExpect(status().isOk()) // ✅ status().OK()를 isOk()로 수정함
                .andExpect(content().string("Hello World"))
                .andDo(print());
    }
}