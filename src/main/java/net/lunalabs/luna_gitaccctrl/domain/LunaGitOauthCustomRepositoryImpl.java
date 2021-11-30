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
    QLunaGitOauth lunaGitOauth = QLunaGitOauth.lunaGitOauth;//직접 만들기 싫다면 static 으로 import 하셔도 됩니다.
    @Override
    public List<LunaGitOauth> findLunaGitOauthsProcYNIsN() {



        return jpaQueryFactory
                .selectFrom(lunaGitOauth)
                .where(lunaGitOauth.procYn.eq(YNEnum.N.name()))
                .where(lunaGitOauth.useYn.eq(YNEnum.Y.name()))
                .fetch();
    }

    @Override
    public Long updateLunaGitOauthsProcYNIsY(Long id) {

        return jpaQueryFactory
                .update(lunaGitOauth)
                .set(lunaGitOauth.procYn, YNEnum.Y.name())
                .where(lunaGitOauth.procYn.eq(YNEnum.N.name()))
                .where(lunaGitOauth.id.eq(id))
                .execute();
    }
}
