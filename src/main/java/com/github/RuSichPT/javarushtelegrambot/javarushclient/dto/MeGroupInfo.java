package com.github.RuSichPT.javarushtelegrambot.javarushclient.dto;

import lombok.Data;

/**
 * Group information related to authorized user. If there is no user - will be null.
 */
@Data
public class MeGroupInfo {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
