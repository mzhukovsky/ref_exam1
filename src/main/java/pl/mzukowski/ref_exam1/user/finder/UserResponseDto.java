package pl.mzukowski.ref_exam1.user.finder;

import lombok.Value;

@Value
class UserResponseDto {
    String id;
    String login;
    String name;
}
