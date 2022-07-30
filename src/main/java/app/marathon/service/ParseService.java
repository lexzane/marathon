package app.marathon.service;

import app.marathon.model.Competitor;
import java.util.List;

public interface ParseService {
    List<Competitor> buildDataSet();
}
