package net.lunalabs.luna_gitaccctrl.domain;

import java.util.List;

public interface LunaGitOauthCustomRepository {

    List<LunaGitOauth> findLunaGitOauthsProcYNIsN();

    Long updateLunaGitOauthsProcYNIsY(Long id);

}
