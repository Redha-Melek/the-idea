package com.redha.idea.mapper;

import com.redha.idea.model.Idea;
import com.redha.idea.model.dto.IdeaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface IdeaMapper extends EntityMapper<IdeaDTO, Idea>{

}
