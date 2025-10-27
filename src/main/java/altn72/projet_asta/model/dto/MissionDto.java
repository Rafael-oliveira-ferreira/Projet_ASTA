package altn72.projet_asta.model.dto;

public record MissionDto(
        Integer id,
        Integer apprenticeId,
        String keywords,
        String targetJob,
        String comments
) {}
