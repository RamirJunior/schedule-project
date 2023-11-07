package ramir.com.schedule.api.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ramir.com.schedule.api.dto.auth.UserAuthRequestDto;
import ramir.com.schedule.api.dto.auth.UserAuthResponseDto;
import ramir.com.schedule.domain.entity.UserAuth;

@Component
@RequiredArgsConstructor
public class UserAuthMapper {

    private final ModelMapper mapper;

    public UserAuth toUserAuth(UserAuthRequestDto userAuthRequestDto) {
        return mapper.map(userAuthRequestDto, UserAuth.class);
    }

    public UserAuthResponseDto toUserAuthResponseDto(UserAuth userAuth) {
        return mapper.map(userAuth, UserAuthResponseDto.class);
    }
}
