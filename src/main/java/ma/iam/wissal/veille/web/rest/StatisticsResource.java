package ma.iam.wissal.veille.web.rest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import ma.iam.wissal.veille.service.StatisticService;
import ma.iam.wissal.veille.service.dto.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsResource {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/pertinenceByCategory/{startDate}/{finishDate}")
    public List<Statistic> getTicketsByCategory(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finishDate
    ) {
        return statisticService.getStatisticsOrderByCategory(startDate, finishDate);
    }

    @GetMapping("/pertinenceByDirection")
    public List<Statistic> getTicketsByDirection(@PathVariable Instant startDate, @PathVariable Instant finishDate) {
        return statisticService.getTicketsByDirection(startDate, finishDate);
    }

    @GetMapping("/pertinenceByContributor")
    public List<Statistic> getTicketsByContributor(@PathVariable Instant startDate, @PathVariable Instant finishDate) {
        return statisticService.getTicketsByContributor(startDate, finishDate);
    }

    @GetMapping("/volumeByDirection")
    public List<Statistic> getTicketsVolumeByDirection(@PathVariable Instant startDate, @PathVariable Instant finishDate) {
        return statisticService.getTicketsVolumeByDirection(startDate, finishDate);
    }

    @GetMapping("/volumeByCategory")
    public List<Statistic> getTicketsVolumeByCategory(@PathVariable Instant startDate, @PathVariable Instant finishDate) {
        return statisticService.getTicketsVolumeByCategory(startDate, finishDate);
    }
}
