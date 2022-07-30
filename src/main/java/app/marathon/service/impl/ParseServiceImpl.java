package app.marathon.service.impl;

import app.marathon.model.Competitor;
import app.marathon.service.ParseService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class ParseServiceImpl implements ParseService {
    private static final String TAG_START_PATH = "src/main/resources/logs/tag_read_start.log";
    private static final String TAG_FINISH_PATH = "src/main/resources/logs/tag_reads_finish.log";
    private static final String DATE_TIME_PATTERN = "yyMMddHHmmss";
    private static final String UTC3 = "+3";
    private static final String SPLITERATOR = "-";

    @Override
    public List<Competitor> buildDataSet() {
        Competitor competitor;
        List<Competitor> competitors = new ArrayList<>();
        List<String> tags = parseTags();
        List<String> tagsWithStartTime = parseTagsWithTime(TAG_START_PATH);
        List<String> tagsWithFinishTime = parseTagsWithTime(TAG_FINISH_PATH);
        Collections.reverse(tagsWithFinishTime);

        for (String tag : tags) {
            competitor = new Competitor();
            competitor.setId(tag);
            LocalDateTime startTime = parseToLocalDateTime(tagsWithStartTime, competitor.getId());
            competitor.setStartTime(startTime);
            LocalDateTime finishTime = parseToLocalDateTime(tagsWithFinishTime, competitor.getId())
                    .atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneOffset.of(UTC3))
                    .toLocalDateTime();
            competitor.setFinishTime(finishTime);
            Duration duration = Duration.between(startTime, finishTime);
            competitor.setResultTime(duration);
            competitors.add(competitor);
        }
        return competitors;
    }

    private List<String> parseTags() {
        try (Stream<String> tags = Files.lines(Paths.get(TAG_START_PATH))) {
            return tags
                    .map(l -> l.substring(4, 16))
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Wrong path or input data", e);
        }
    }

    private List<String> parseTagsWithTime(String path) {
        try (Stream<String> tagsAndTime = Files.lines(Paths.get(path))) {
            return tagsAndTime
                    .map(l -> l.substring(4, 16) + SPLITERATOR + l.substring(20, 32))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Wrong path or input data", e);
        }
    }

    private LocalDateTime parseToLocalDateTime(List<String> input, String id) {
        String rawLocalDateTime = input.stream()
                .filter(e -> e.contains(id))
                .map(e -> e.substring(e.indexOf(SPLITERATOR) + 1))
                .findFirst()
                .orElse(null);
        return rawLocalDateTime != null
                ? LocalDateTime.parse(rawLocalDateTime,
                DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))
                : LocalDateTime.now();
    }
}
