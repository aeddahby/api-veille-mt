package ma.iam.wissal.veille.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import ma.iam.wissal.veille.repository.TicketRepository;
import ma.iam.wissal.veille.service.StatisticService;
import ma.iam.wissal.veille.service.dto.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Statistic> getStatisticsOrderByCategory(LocalDate startDate, LocalDate finishDate) {
        return ticketRepository.getStatisticsOrderByCategory(startDate, finishDate);
    }

    @Override
    public List<Statistic> getTicketsByDirection(Instant startDate, Instant finishDate) {
        return ticketRepository.getTicketsByDirection(startDate, finishDate);
    }

    @Override
    public List<Statistic> getTicketsByContributor(Instant startDate, Instant finishDate) {
        return ticketRepository.getTicketsByContributor(startDate, finishDate);
    }

    @Override
    public List<Statistic> getTicketsVolumeByDirection(Instant startDate, Instant finishDate) {
        return ticketRepository.getTicketsVolumeByDirection(startDate, finishDate);
    }

    @Override
    public List<Statistic> getTicketsVolumeByCategory(Instant startDate, Instant finishDate) {
        return ticketRepository.getTicketsVolumeByCategory(startDate, finishDate);
    }
}
