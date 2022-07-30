package app.marathon.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitorResponseDto {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String resultTime;
}
