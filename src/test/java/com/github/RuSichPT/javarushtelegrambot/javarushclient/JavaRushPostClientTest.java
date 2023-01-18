package com.github.RuSichPT.javarushtelegrambot.javarushclient;

import com.github.RuSichPT.javarushtelegrambot.javarushclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Integration-level testing for JavaRushPostClient")
class JavaRushPostClientTest {

    private final JavaRushPostClient postClient = new JavaRushPostClientImpl(JavaRushGroupClientTest.JAVARUSH_API_PATH);

    @Test
    public void shouldProperlyGetNew15Posts() {
        //when
        List<PostInfo> newPosts = postClient.findNewPosts(30, 2935);

        //then
        Assertions.assertEquals(15, newPosts.size());
    }
}