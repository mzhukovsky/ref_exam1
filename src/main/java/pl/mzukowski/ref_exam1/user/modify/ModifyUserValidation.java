package pl.mzukowski.ref_exam1.user.modify;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
class ModifyUserValidation {

    void validateModifyUser(ModifyUserDto modifyUserDto){
        if(modifyUserDto.getName().isEmpty() ||
                modifyUserDto.getLastName().isEmpty() ||
                getId(modifyUserDto.getId()) < 1) {
            throw new ResponseStatusException(BAD_REQUEST, "Błąd danych modyfikacji użytkownika");
        }


    }

    private Long getId(String id){
        try {
            return Long.valueOf(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(BAD_REQUEST, "Uzytkownik posiada błędne id", ex);
        }
    }
}
