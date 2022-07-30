package app.marathon.model;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Competitor {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Duration resultTime;
}
