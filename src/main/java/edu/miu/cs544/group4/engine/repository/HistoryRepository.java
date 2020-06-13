package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.History;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends BaseRepository<History, Integer> {
}
