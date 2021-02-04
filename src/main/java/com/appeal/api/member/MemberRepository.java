package com.appeal.api.member;

import java.util.List;

public interface MemberRepository {
    List<Member> findByEmail(String email);
}
