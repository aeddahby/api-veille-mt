package ma.iam.wissal.veille.service.impl;

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

    public List<Statistic> getStatisticsOrderByCategory() {
        return ticketRepository.getStatisticsOrderByCategory();
    }

    @Override
    public List<Statistic> getTicketsByDirection() {
        return ticketRepository.getTicketsByDirection();
    }

    @Override
    public List<Statistic> getTicketsByContributor() {
        return ticketRepository.getTicketsByContributor();
    }

    @Override
    public List<Statistic> getTicketsVolumeByDirection() {
        return ticketRepository.getTicketsVolumeByDirection();
    }

    @Override
    public List<Statistic> getTicketsVolumeByCategory() {
        return ticketRepository.getTicketsVolumeByCategory();
    }
}
