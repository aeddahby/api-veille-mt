package ma.iam.wissal.veille.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import ma.iam.wissal.veille.service.dto.Statistic;

public interface StatisticService {
    List<Statistic> getStatisticsOrderByCategory(LocalDate startDate, LocalDate finishDate);

    List<Statistic> getTicketsByDirection(Instant startDate, Instant finishDate);

    List<Statistic> getTicketsByContributor(Instant startDate, Instant finishDate);

    List<Statistic> getTicketsVolumeByDirection(Instant startDate, Instant finishDate);

    List<Statistic> getTicketsVolumeByCategory(Instant startDate, Instant finishDate);
}
