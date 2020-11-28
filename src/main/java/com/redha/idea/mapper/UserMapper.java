package com.redha.idea.mapper;

import com.redha.idea.model.Idea;
import com.redha.idea.model.User;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User>{

}
