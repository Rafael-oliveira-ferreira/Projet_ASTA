let defenseId = null; // to be set when loading defense data
let missionId = null; // to be set when loading mission data
let reportId = null;  // to be set when loading report data
let visitId = null;   // to be set when loading visit data

function readCsrf() {
    const token = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
    const header = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content') || 'X-CSRF-TOKEN';
    return {token, header};
}

function startEdit() {
    const spanLastName = document.getElementById('span_last_name');
    const spanFirstName = document.getElementById('span_first_name');
    const spanEmail = document.getElementById('span_email');
    const spanPhone = document.getElementById('span_phone');
    const spanProgram = document.getElementById('span_program');
    const spanMajor = document.getElementById('span_major');
    const spanAcademicYear = document.getElementById('span_academic_year');
    const spanCompanyName = document.getElementById('span_company_name');
    const spanCompanyAddress = document.getElementById('span_company_address');
    const spanCompanyInfo = document.getElementById('span_company_info');
    const missionDetails = document.getElementById('mission-details');
    const missionPlaceholder = document.getElementById('mission-placeholder');
    const spanMissionKeywords = document.getElementById('span_mission_keywords');
    const spanMissionTarget = document.getElementById('span_mission_target');
    const spanMissionComments = document.getElementById('span_mission_comments');
    const visitDetails = document.getElementById('visit-details');
    const visitPlaceholder = document.getElementById('visit-placeholder');
    const visitFormat = document.getElementById('visit-format');
    const visitDate = document.getElementById('visit-date');
    const visitComments = document.getElementById('visit-comments');
    const defenseDetails = document.getElementById('defense-details');
    const defensePlaceholder = document.getElementById('defense-placeholder');
    const defenseDate = document.getElementById('denfense-date');
    const defenseGrade = document.getElementById('defense-grade');
    const defenseComments = document.getElementById('defense-comments');
    const reportDetails = document.getElementById('report-details');
    const reportPlaceholder = document.getElementById('report-placeholder');
    const reportSubject = document.getElementById('report-subject');
    const reportGrade = document.getElementById('report-grade');
    const reportComments = document.getElementById('report-comments');
    document.querySelector('.btn-edit').classList.add('hidden');
    document.querySelector('.btn-save').classList.remove('hidden');

    spanLastName.innerHTML = `<input type="text" id="input_last_name" value="${spanLastName.textContent}">`;
    spanFirstName.innerHTML = `<input type="text" id="input_first_name" value="${spanFirstName.textContent}">`;
    spanEmail.innerHTML = `<input type="text" id="input_email" value="${spanEmail.textContent}">`;
    spanPhone.innerHTML = `<input type="text" id="input_phone" value="${spanPhone.textContent}">`;
    if (spanProgram) {
        spanProgram.innerHTML = `<input type="text" id="input_program" value="${spanProgram.textContent}">`;
    }
    if (spanMajor) {
        spanMajor.innerHTML = `<input type="text" id="input_major" value="${spanMajor.textContent}">`;
    }
    if (spanAcademicYear) {
        spanAcademicYear.innerHTML = `<input type="text" id="input_academic_year" value="${spanAcademicYear.textContent}">`;
    }
    if (spanCompanyName) {
        spanCompanyName.innerHTML = `<input type="text" id="input_company_name" value="${spanCompanyName.textContent}">`;
    }
    if (spanCompanyAddress) {
        spanCompanyAddress.innerHTML = `<input type="text" id="input_company_address" value="${spanCompanyAddress.textContent}">`;
    }
    if (spanCompanyInfo) {
        spanCompanyInfo.innerHTML = `<input type="text" id="input_company_info" value="${spanCompanyInfo.textContent}">`;
    }
    if (missionPlaceholder) {
        missionPlaceholder.classList.add('hidden');
        missionDetails.classList.remove('hidden');
        spanMissionKeywords.innerHTML = `<input type="text" id="input_mission_keywords" value="">`;
        spanMissionTarget.innerHTML = `<input type="text" id="input_mission_target" value="">`;
        spanMissionComments.innerHTML = `<input type="text" id="input_mission_comments" value="">`;
    } else {
        spanMissionKeywords.innerHTML = `<input type="text" id="input_mission_keywords" value="${spanMissionKeywords.textContent}">`;
        spanMissionTarget.innerHTML = `<input type="text" id="input_mission_target" value="${spanMissionTarget.textContent}">`;
        spanMissionComments.innerHTML = `<input type="text" id="input_mission_comments" value="${spanMissionComments.textContent}">`;
    }
    if (visitPlaceholder) {
        visitPlaceholder.classList.add('hidden');
        visitDetails.classList.remove('hidden');
        visitFormat.innerHTML = `<input type="text" id="input_visit_format" value="">`;
        visitDate.innerHTML = `<input type="date" id="input_visit_date" value="">`;
        visitComments.innerHTML = `<input type="text" id="input_visit_comments" value="">`;
    } else {
        visitFormat.innerHTML = `<input type="text" id="input_visit_format" value="${visitFormat.textContent}">`;
        visitDate.innerHTML = `<input type="date" id="input_visit_date" value="${visitDate.textContent}">`;
        visitComments.innerHTML = `<input type="text" id="input_visit_comments" value="${visitComments.textContent}">`;
    }
    if (defensePlaceholder) {
        defensePlaceholder.classList.add('hidden');
        defenseDetails.classList.remove('hidden');
        defenseDate.innerHTML = `<input type="date" id="input_defense_date" value="">`;
        defenseGrade.innerHTML = `<input type="text" id="input_defense_grade" value="">`;
        defenseComments.innerHTML = `<input type="text" id="input_defense_comments" value="">`;
    } else {
        defenseDate.innerHTML = `<input type="date" id="input_defense_date" value="${defenseDate.textContent}">`;
        defenseGrade.innerHTML = `<input type="text" id="input_defense_grade" value="${defenseGrade.textContent}">`;
        defenseComments.innerHTML = `<input type="text" id="input_defense_comments" value="${defenseComments.textContent}">`;
    }
    if (reportPlaceholder) {
        reportPlaceholder.classList.add('hidden');
        reportDetails.classList.remove('hidden');
        reportSubject.innerHTML = `<input type="text" id="input_report_subject" value="">`;
        reportGrade.innerHTML = `<input type="text" id="input_report_grade" value="">`;
        reportComments.innerHTML = `<input type="text" id="input_report_comments" value="">`;
    } else {
        reportSubject.innerHTML = `<input type="text" id="input_report_subject" value="${reportSubject.textContent}">`;
        reportGrade.innerHTML = `<input type="text" id="input_report_grade" value="${reportGrade.textContent}">`;
        reportComments.innerHTML = `<input type="text" id="input_report_comments" value="${reportComments.textContent}">`;
    }
}

function saveEdit(apprenticeId, companyId) {
    const companyNameVal = (document.getElementById('input_company_name')?.value || '').trim();
    const companyAddressVal = (document.getElementById('input_company_address')?.value || '').trim();
    const companyAccessInfoVal = (document.getElementById('input_company_info')?.value || '').trim();

    let companyData = null;
    if (companyNameVal || companyAddressVal || companyAccessInfoVal) {
        companyData = {
            companyName: companyNameVal || null,
            address: companyAddressVal || null,
            accessInfo: companyAccessInfoVal || null
        };
    }

    const missionKeywordsVal = (document.getElementById('input_mission_keywords')?.value || '').trim();
    const missionTargetVal = (document.getElementById('input_mission_target')?.value || '').trim();
    const missionCommentsVal = (document.getElementById('input_mission_comments')?.value || '').trim();

    let missionData = null;
    if (missionKeywordsVal || missionTargetVal || missionCommentsVal) {
        missionData = {
            apprentice: { id: apprenticeId },
            keywords: missionKeywordsVal || null,
            targetJob: missionTargetVal || null,
            comments: missionCommentsVal || null
        };
    }

    const visitFormatVal = (document.getElementById('input_visit_format')?.value || '').trim();
    const visitDateVal = (document.getElementById('input_visit_date')?.value || '').trim();
    const visitCommentsVal = (document.getElementById('input_visit_comments')?.value || '').trim();

    let visitData = null;
    if (visitFormatVal || visitDateVal || visitCommentsVal) {
        visitData = {
            apprentice: { id: apprenticeId },
            format: visitFormatVal || null,
            visitDate: visitDateVal || null,
            comments: visitCommentsVal || null
        };
    }

    const defenseDateVal = (document.getElementById('input_defense_date')?.value || '').trim();
    const defenseGradeVal = (document.getElementById('input_defense_grade')?.value || '').trim();
    const defenseCommentsVal = (document.getElementById('input_defense_comments')?.value || '').trim();

    let defenseData = null;
    if (defenseDateVal || defenseGradeVal || defenseCommentsVal) {
        defenseData = {
            apprentice: { id: apprenticeId },
            defenseDate: defenseDateVal || null,
            grade: defenseGradeVal || null,
            comments: defenseCommentsVal || null
        };
    }

    const reportSubjectVal = (document.getElementById('input_report_subject')?.value || '').trim();
    const reportGradeVal = (document.getElementById('input_report_grade')?.value || '').trim();
    const reportCommentsVal = (document.getElementById('input_report_comments')?.value || '').trim();

    let reportData = null;
    if (reportSubjectVal || reportGradeVal || reportCommentsVal) {
        reportData = {
            apprentice: { id: apprenticeId },
            subject: reportSubjectVal || null,
            grade: reportGradeVal || null,
            comments: reportCommentsVal || null
        };
    }

    if (companyData) {
        updateField('Company', companyId, companyData);
    }

    if (missionData) {
        updateField('Mission', missionId, missionData);
    }

    if (visitData) {
        updateField('Visit', visitId, visitData);
    }

    if (defenseData) {
        updateField('Defense', defenseId, defenseData);
    }

    if (reportData) {
        updateField('Report', reportId, reportData);
    }
    document.querySelector('.btn-edit').classList.add('hidden');
    document.querySelector('.btn-save').classList.remove('hidden');

    revertFields();
}

function revertFields() {
    const get = id => document.getElementById(id);

    const setSpanFromInput = (spanId, inputId, formatter) => {
        const span = get(spanId);
        if (!span) return;
        const input = get(inputId);
        if (!input) return;
        const val = (input.value || '').trim();
        span.textContent = formatter ? formatter(val) : val;
    };

    setSpanFromInput('span_last_name', 'input_last_name');
    setSpanFromInput('span_first_name', 'input_first_name');
    setSpanFromInput('span_email', 'input_email');
    setSpanFromInput('span_phone', 'input_phone');

    setSpanFromInput('span_program', 'input_program');
    setSpanFromInput('span_major', 'input_major');
    setSpanFromInput('span_academic_year', 'input_academic_year');

    setSpanFromInput('span_company_name', 'input_company_name');
    setSpanFromInput('span_company_address', 'input_company_address');
    setSpanFromInput('span_company_info', 'input_company_info');

    setSpanFromInput('span_mission_keywords', 'input_mission_keywords');
    setSpanFromInput('span_mission_target', 'input_mission_target');
    setSpanFromInput('span_mission_comments', 'input_mission_comments');

    setSpanFromInput('visit-format', 'input_visit_format');
    setSpanFromInput('visit-date', 'input_visit_date', v => v ? (new Date(v).toLocaleDateString('fr-FR')) : '');
    setSpanFromInput('visit-comments', 'input_visit_comments');

    setSpanFromInput('denfense-date', 'input_defense_date', v => v ? (new Date(v).toLocaleDateString('fr-FR')) : '');
    setSpanFromInput('defense-grade', 'input_defense_grade');
    setSpanFromInput('defense-comments', 'input_defense_comments');

    setSpanFromInput('report-subject', 'input_report_subject');
    setSpanFromInput('report-grade', 'input_report_grade');
    setSpanFromInput('report-comments', 'input_report_comments');

    const btnEdit = document.querySelector('.btn-edit');
    const btnSave = document.querySelector('.btn-save');
    if (btnEdit) btnEdit.classList.remove('hidden');
    if (btnSave) btnSave.classList.add('hidden');
}

function addField(name, data) {
    const url = '/create' + name + '/';
    const payload = JSON.stringify(data);
    console.log('Creating', name);

    const {token, header} = readCsrf();
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    };
    if (token) headers[header] = token;

    return fetch(url, {
        method: 'POST',
        headers: headers,
        body: payload,
        credentials: 'same-origin'
    })
        .then(function (response) {
            if (!response.ok) return response.text().then(t => {
                throw new Error('Network response was not ok: ' + response.status + ' ' + t);
            });
            return response.json().catch(() => null);
        })
        .then(function () {
            console.log('Successfully added');
        })
        .catch(function (err) {
            console.error('Failed to add field:', err);
            throw err;
        });
}

function updateField(name, id, data) {
    if (id == null) return addField(name, data);
    if (data == null) return Promise.resolve(null);

    const url = '/update' + name + '/' + id;
    const payload = JSON.stringify(data);

    const {token, header} = readCsrf();
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    };
    if (token) headers[header] = token;

    return fetch(url, {
        method: 'PUT',
        headers: headers,
        body: payload,
        credentials: 'same-origin'
    })
        .then(function (response) {
            if (!response.ok) return response.text().then(text => {
                throw new Error('Network response was not ok: ' + response.status + ' ' + text);
            });
            return response.json().catch(() => null);
        })
        .then(function (result) {
            console.log('Successfully updated');
            return result;
        })
        .catch(function (err) {
            console.error('Failed to update field:', err);
            throw err;
        });
}

document.addEventListener('DOMContentLoaded', function () {
    const apprenticeIdRaw = document.body.dataset.apprenticeId;
    const apprenticeId = (apprenticeIdRaw && apprenticeIdRaw !== '0') ? Number(apprenticeIdRaw) : null;

    if (apprenticeId === null) {
        console.log('No apprentice id available');
        return;
    }

    Promise.all([
        fetch('/missionByApprenticeId/' + apprenticeId, {credentials: 'same-origin'})
            .then(r => r.ok ? r.json() : null).catch(err => {
            console.error('mission fetch failed', err);
            return null;
        }),
        fetch('/visitByApprenticeId/' + apprenticeId, {credentials: 'same-origin'})
            .then(r => r.ok ? r.json() : null).catch(err => {
            console.error('visit fetch failed', err);
            return null;
        }),
        fetch('/reportByApprenticeId/' + apprenticeId, {credentials: 'same-origin'})
            .then(r => r.ok ? r.json() : null).catch(err => {
            console.error('report fetch failed', err);
            return null;
        }),
        fetch('/defenseByApprenticeId/' + apprenticeId, {credentials: 'same-origin'})
            .then(r => r.ok ? r.json() : null).catch(err => {
            console.error('defense fetch failed', err);
            return null;
        })
    ])
        .then(function ([mission, visit, report, defense]) {
            const firstMission = mission && mission.length > 0 ? mission[0] : null;
            const firstVisit = visit && visit.length > 0 ? visit[0] : null
            const firstReport = report && report.length > 0 ? report[0] : null;
            const firstDefense = defense && defense.length > 0 ? defense[0] : null

            missionId = firstMission ? firstMission.id : null;
            visitId = firstVisit ? firstVisit.id : null;
            reportId = firstReport ? firstReport.id : null;
            defenseId = firstDefense ? firstDefense.id : null;

            if (firstMission) {
                document.getElementById('span_mission_keywords').textContent = firstMission.keywords || '';
                document.getElementById('span_mission_target').textContent = firstMission.targetJob || '';
                document.getElementById('span_mission_comments').textContent = firstMission.comments || '';
            }

            if (firstVisit) {
                document.getElementById('visit-format').textContent = firstVisit.format || '';
                document.getElementById('visit-date').textContent = firstVisit.visitDate ? new Date(firstVisit.visitDate).toLocaleDateString('fr-FR') : '';
                document.getElementById('visit-comments').textContent = firstVisit.comments || '';
            }

            if (firstReport) {
                document.getElementById('report-subject').textContent = firstReport.subject || '';
                document.getElementById('report-grade').textContent = firstReport.grade || '';
                document.getElementById('report-comments').textContent = firstReport.comments || '';
            }

            if (firstDefense) {
                document.getElementById('denfense-date').textContent = firstDefense.defenseDate ? new Date(firstDefense.defenseDate).toLocaleDateString('fr-FR') : '';
                document.getElementById('defense-grade').textContent = firstDefense.grade || '';
                document.getElementById('defense-comments').textContent = firstDefense.comments || '';
            }
        })
        .catch(function (err) {
            console.error('Failed to load related entities:', err);
        });
});