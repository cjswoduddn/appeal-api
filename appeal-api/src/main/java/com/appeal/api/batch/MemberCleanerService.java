package com.appeal.api.batch;

import com.appeal.api.common.Authority;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static java.time.LocalDateTime.*;

@RequiredArgsConstructor
@EnableScheduling
@Service
public class MemberCleanerService {

    private final MemberRepository memberRepository;

    /**
     * cron : 매일 새벽 3시에 정확히 1번 발동
     * 가입한 지 12시간이 경과되면 레디스 인증코드 데이터 만료
     * 이후 배치 처리를 통해 12시간이 지난 인증되지 않은 데이터 영구 삭제
     */
    @Transactional
    @Scheduled(cron = "0 0 3 * * ?")
    public void notValidMemberCleaner() {
        memberRepository
                .findAll()
                .stream()
                .filter(member -> member.getAuthority() == Authority.ROLE_PRE)
                .filter(member -> validTime(member))
                .forEach(member -> memberRepository.delete(member));

    }

    private boolean validTime(Member member) {
        return
                member
                        .getCreatedDate()
                        .isAfter(now().minusHours(12))
                ;
    }

}
