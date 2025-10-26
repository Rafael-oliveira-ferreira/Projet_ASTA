package altn72.projet_asta.model.dto;

public record ApprenticeDto(
        Integer id,
        String program,
        String academicYear,
        String major,
        String firstName,
        String lastName,
        String email,
        String phone,
        Integer companyId,
        Integer mentorId,
        Integer defenseId,
        Integer missionId,
        Integer reportId,
        Integer visitId
) {}