package tobyspring.splearn.application.member.required;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import tobyspring.splearn.domain.member.Member;
import tobyspring.splearn.domain.member.Profile;
import tobyspring.splearn.domain.shared.Email;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회한다
 */
public interface MemberRepository extends Repository<Member, Long> {//TODO 도메인 어플리케이션이 특정 기술에 의존.
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);

    @Query("select m from Member m where m.detail.profile = :profile")
    Optional<Member> findByProfile(Profile profile);
}
