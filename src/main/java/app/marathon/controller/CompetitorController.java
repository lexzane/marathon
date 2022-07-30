package app.marathon.controller;

import app.marathon.dto.CompetitorResponseDto;
import app.marathon.model.Competitor;
import app.marathon.service.CompetitorService;
import app.marathon.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/competitors")
public class CompetitorController {
    private final CompetitorService competitorService;
    private final ResponseDtoMapper<CompetitorResponseDto, Competitor> responseDtoMapper;

    public CompetitorController(CompetitorService competitorService,
                                ResponseDtoMapper<CompetitorResponseDto,
                                        Competitor> responseDtoMapper) {
        this.competitorService = competitorService;
        this.responseDtoMapper = responseDtoMapper;
    }

    @GetMapping("/top-ten")
    public List<CompetitorResponseDto> getTopTen() {
        return competitorService.getTopTen()
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
