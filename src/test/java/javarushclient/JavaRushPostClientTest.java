package javarushclient;

import com.github.RuSichPT.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.RuSichPT.javarushtelegrambot.javarushclient.JavaRushPostClient;
import com.github.RuSichPT.javarushtelegrambot.javarushclient.JavaRushPostClientImpl;
import com.github.RuSichPT.javarushtelegrambot.javarushclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static javarushclient.JavaRushGroupClientTest.JAVARUSH_API_PATH;

@DisplayName("Integration-level testing for JavaRushPostClient")
class JavaRushPostClientTest {

    private final JavaRushPostClient postClient = new JavaRushPostClientImpl(JAVARUSH_API_PATH);

    @Test
    public void shouldProperlyGetNew15Posts() {
        //when
        List<PostInfo> newPosts = postClient.findNewPosts(30, 2935);

        //then
        Assertions.assertEquals(15, newPosts.size());
    }
}