package net.lunalabs.luna_gitaccctrl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


import java.util.List;

@RequiredArgsConstructor
@Repository  //QueryDsl 용도
public class LunaGitOauthCustomRepositoryImpl implements LunaGitOauthCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LunaGitOauth> findLunaGitOauthsProcYNIsN() {

        //QLunaGitOauth lunaGitOauth = new QLunaGitOauth("m"); //직접 만들기 싫다면 static 으로 import 하셔도 됩니다.

        return jpaQueryFactory
                .selectFrom(QLunaGitOauth.lunaGitOauth)
                .where(QLunaGitOauth.lunaGitOauth.procYn.eq("N"))
                .fetch();
    }

    @Override
    public Long updateLunaGitOauthsProcYNIsY(Long id) {

        return jpaQueryFactory
                .update(QLunaGitOauth.lunaGitOauth)
                .set(QLunaGitOauth.lunaGitOauth.procYn, YNEnum.Y.name())
                .where(QLunaGitOauth.lunaGitOauth.procYn.eq("N"))
                .where(QLunaGitOauth.lunaGitOauth.id.eq(id))
                .execute();
    }
}
