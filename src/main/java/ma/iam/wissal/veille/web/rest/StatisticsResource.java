package ma.iam.wissal.veille.web.rest;

import java.util.List;
import ma.iam.wissal.veille.service.StatisticService;
import ma.iam.wissal.veille.service.dto.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsResource {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/pertinenceByCategory")
    public List<Statistic> getTicketsByCategory() {
        return statisticService.getStatisticsOrderByCategory();
    }

    @GetMapping("/pertinenceByDirection")
    public List<Statistic> getTicketsByDirection() {
        return statisticService.getTicketsByDirection();
    }

    @GetMapping("/pertinenceByContributor")
    public List<Statistic> getTicketsByContributor() {
        return statisticService.getTicketsByContributor();
    }

    @GetMapping("/volumeByDirection")
    public List<Statistic> getTicketsVolumeByDirection() {
        return statisticService.getTicketsVolumeByDirection();
    }

    @GetMapping("/volumeByCategory")
    public List<Statistic> getTicketsVolumeByCategory() {
        return statisticService.getTicketsVolumeByCategory();
    }
}
