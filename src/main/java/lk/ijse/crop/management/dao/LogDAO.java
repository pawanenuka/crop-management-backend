package lk.ijse.crop.management.dao;

import lk.ijse.crop.management.entity.impl.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDAO extends JpaRepository<LogEntity, String> {
}
