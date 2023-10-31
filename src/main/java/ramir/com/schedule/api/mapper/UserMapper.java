package ramir.com.schedule.api.mapper;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ramir.com.schedule.api.dto.UserRequest;
import ramir.com.schedule.api.dto.UserResponse;
import ramir.com.schedule.domain.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toUser(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserResponse toUserResponse (User user) {
        return mapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toUserResponseList (List<User> users) {
        return users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }
}
