package altn72.projet_asta.model.dto;

import java.time.LocalDate;

public record VisitDto(
        Integer id,
        Integer apprenticeId,
        LocalDate visitDate,
        String format,
        String comments
) {}