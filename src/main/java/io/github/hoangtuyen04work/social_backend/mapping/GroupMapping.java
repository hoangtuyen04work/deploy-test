package io.github.hoangtuyen04work.social_backend.mapping;

import io.github.hoangtuyen04work.social_backend.dto.response.GroupResponse;
import io.github.hoangtuyen04work.social_backend.entities.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapping {
    @Autowired
    private UserMapping userMapping;

    public GroupResponse toGroupResponse(GroupEntity group) {
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getName())
                .admin(userMapping.toUserSummaryResponse(group.getAdmin()))
                .users(userMapping.toUserSummaryResponses(group.getUsers()))
                .build();
    }
}
