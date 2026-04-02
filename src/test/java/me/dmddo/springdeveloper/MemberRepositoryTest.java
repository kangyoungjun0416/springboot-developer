package me.dmddo.springdeveloper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //레파지토리 애너테이션, @Transactional 이게 들어가있대 자동롤백
public class MemberRepositoryTest {
    @Autowired //의존성 주입받을 때 하는 거래
    MemberRepository memberRepository;

    @Test
    @Sql("/insert-members.sql")
    void getAllMembers() {
        //given(준비)
        //when(실행)
        List<Member> member = memberRepository.findAll();//select * from member;
        //then(검증)
        assertThat(member.size()).isEqualTo(3);
    }

    @Test
    @Sql("/insert-members.sql")
    void getMemberById() {
        //given(준비)

        //when(실행)
        Member members = memberRepository.findById(2L).get();
        //then(검증)
        assertThat(members.getName()).isEqualTo("B");
    }

    @Test
    @Sql("/insert-members.sql")
    void getMemberByName() {
        //given(준비)

        //when(실행)
        Member member = memberRepository.findByName("C").get();

        //then(검증)
        assertThat(member.getId()).isEqualTo(3);

    }

    @DisplayName("레코드 삽입 테스트")
    @Test
    void saveMember() {
        // given: ID를 넣지 않고 이름만 설정해서 객체 생성 (생성자 확인 필요)
        Member m = new Member("dmddo");

        // when: 저장하면 DB가 자동으로 ID(예: 4번 혹은 1번)를 부여함
        Member savedMember = memberRepository.save(m);


        // then: 저장된 객체의 ID로 다시 조회해서 확인
        assertThat(savedMember.getId()).isNotNull();
        Long id = savedMember.getId();
        Optional<Member> result = memberRepository.findById(id);
        Member member = result.get();
        String name = member.getName();
        assertThat(name).isEqualTo("dmddo");

        //assertThat(memberRepository.findById(savedMember.getId()).get().getName()).isEqualTo("dmddo");
    }

    @DisplayName("2개의 레코드를 한 번에 삽입하는 테스트")
    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(new Member("asdqweqasaklajsd"), new Member("anjfqhk"));

        // when
        memberRepository.saveAll(members);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);

    }

    @Sql("/insert-members.sql")
    @DisplayName("레코드 삭제 테스트")
    @Test
     void deleteAll() {
        //given
        //when
        memberRepository.deleteAll();

        //then
        assertThat(memberRepository.findAll().size()).isZero();
    }

    @Sql("/insert-members.sql")
    @DisplayName("Update Test")
    @Test
    void update() {
        //given
        Member member = memberRepository.findById(2L).get();
        //when
        member.changeName("홍영전");
        //then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("홍영전");
    }
}