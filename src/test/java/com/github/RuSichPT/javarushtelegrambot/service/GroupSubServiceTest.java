package com.github.RuSichPT.javarushtelegrambot.service;

import com.github.RuSichPT.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.RuSichPT.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.RuSichPT.javarushtelegrambot.repository.GroupSubRepository;
import com.github.RuSichPT.javarushtelegrambot.repository.entity.GroupSub;
import com.github.RuSichPT.javarushtelegrambot.repository.entity.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

@DisplayName("Unit-level testing for GroupSubService")
class GroupSubServiceTest {

    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegramUser telegramUser;

    private final static String CHAT_ID = "1";
    private final static Integer GROUP_ID = 1123;
    private final static Integer LAST_POST_ID = 310;

    @BeforeEach
    void init() {
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService, javaRushGroupClient);

        telegramUser = new TelegramUser();
        telegramUser.setChatId(CHAT_ID);
        telegramUser.setActive(true);

        Mockito.when(telegramUserService.findByChatId(CHAT_ID)).thenReturn(Optional.of(telegramUser));

        Mockito.when(javaRushGroupClient.findLastPostId(GROUP_ID)).thenReturn(LAST_POST_ID);
    }

    @Test
    public void shouldProperlySaveGroup() {
        // given
        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(GROUP_ID);
        groupDiscussionInfo.setTitle("g1");

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.setLastArticleId(LAST_POST_ID);
        expectedGroupSub.addUser(telegramUser);

        // when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        // then
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

    @Test
    public void shouldProperlyAddUserToExistingGroup() {
        // given
        TelegramUser oldTelegramUser = new TelegramUser();
        oldTelegramUser.setChatId("2");
        oldTelegramUser.setActive(true);

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(GROUP_ID);
        groupDiscussionInfo.setTitle("g1");

        GroupSub groupFromDB = new GroupSub();
        groupFromDB.setId(groupDiscussionInfo.getId());
        groupFromDB.setTitle(groupDiscussionInfo.getTitle());
        groupFromDB.addUser(oldTelegramUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId()))
                .thenReturn(Optional.of(groupFromDB));

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldTelegramUser);
        expectedGroupSub.addUser(telegramUser);

        // when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        // then
        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }
}
