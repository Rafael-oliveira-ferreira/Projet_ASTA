package altn72.projet_asta.model.dto;

import java.math.BigDecimal;

public record ReportDto(
        Integer id,
        Integer apprenticeId,
        String subject,
        BigDecimal grade,
        String comments
) {}
