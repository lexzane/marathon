package app.marathon.service.mapper;

import app.marathon.dto.CompetitorResponseDto;
import app.marathon.model.Competitor;
import org.springframework.stereotype.Component;

@Component
public class CompetitorResponseDtoMapper implements
        ResponseDtoMapper<CompetitorResponseDto, Competitor> {
    @Override
    public CompetitorResponseDto mapToDto(Competitor competitor) {
        CompetitorResponseDto responseDto = new CompetitorResponseDto();
        responseDto.setId(competitor.getId());
        responseDto.setStartTime(competitor.getStartTime());
        responseDto.setFinishTime(competitor.getFinishTime());
        responseDto.setResultTime(competitor.getResultTime().toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase());
        return responseDto;
    }
}
