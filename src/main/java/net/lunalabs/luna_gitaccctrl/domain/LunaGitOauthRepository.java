package net.lunalabs.luna_gitaccctrl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LunaGitOauthRepository extends JpaRepository<LunaGitOauth, Long>, LunaGitOauthCustomRepository {

    //queryDsl 사용하기 싫으시면 native Quey 사용하세요.
    @Query(value = "select * from LunaGitOauth where procYn= 'N'", nativeQuery=true)
    List<LunaGitOauth> mFindByProcyYnIsN();



}
