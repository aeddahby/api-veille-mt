package ma.iam.wissal.veille.service;

import java.util.List;
import ma.iam.wissal.veille.service.dto.Statistic;

public interface StatisticService {
    List<Statistic> getStatisticsOrderByCategory();

    List<Statistic> getTicketsByDirection();

    List<Statistic> getTicketsByContributor();

    List<Statistic> getTicketsVolumeByDirection();

    List<Statistic> getTicketsVolumeByCategory();
}
