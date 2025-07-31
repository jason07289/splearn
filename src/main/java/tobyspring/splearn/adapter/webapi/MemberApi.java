package tobyspring.splearn.adapter.webapi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tobyspring.splearn.adapter.webapi.dto.MemberRegisterResponse;
import tobyspring.splearn.application.member.provided.MemberRegister;
import tobyspring.splearn.domain.member.Member;
import tobyspring.splearn.domain.member.MemberRegisterRequest;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberRegister memberRegister;

    @PostMapping("/api/members")
    public MemberRegisterResponse register(@RequestBody @Valid MemberRegisterRequest request) {
        Member member = memberRegister.register(request);
        //MemberRegisterResponse를 만드는 것은 Controller의 역할이다. 어플리케이션 계층이 이것을 담당하는 것은 좋지 않다.
        //but, 어플리케이션에서 좀 복잡한 로직을 통해 리포트성 조회결과가 나오는 경우엔 dto를 리턴해야한다.
        return MemberRegisterResponse.of(member);
    }
}
