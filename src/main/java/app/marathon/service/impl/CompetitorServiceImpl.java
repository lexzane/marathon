package app.marathon.service.impl;

import app.marathon.model.Competitor;
import app.marathon.service.CompetitorService;
import app.marathon.service.ParseService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CompetitorServiceImpl implements CompetitorService {
    private final ParseService parseService;

    public CompetitorServiceImpl(ParseService parseService) {
        this.parseService = parseService;
    }

    @Override
    public List<Competitor> getTopTen() {
        return parseService.buildDataSet().stream()
                .sorted(Comparator.comparing(Competitor::getResultTime))
                .limit(10)
                .collect(Collectors.toList());
    }
}
