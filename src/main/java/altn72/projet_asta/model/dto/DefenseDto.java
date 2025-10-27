package altn72.projet_asta.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DefenseDto(
        Integer id,
        Integer apprenticeId,
        LocalDate defenseDate,
        BigDecimal grade,
        String comments
) {}