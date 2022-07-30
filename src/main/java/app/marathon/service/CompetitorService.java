package app.marathon.service;

import app.marathon.model.Competitor;
import java.util.List;

public interface CompetitorService {
    List<Competitor> getTopTen();
}
