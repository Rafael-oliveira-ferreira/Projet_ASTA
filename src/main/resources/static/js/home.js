let apprenticesList = [];

(function () {
    function readCsrf() {
        const token = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
        const header = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content') || 'X-CSRF-TOKEN';
        return {token, header};
    }

    function renderApprenticesTable(apprentices) {
        let tbody = document.getElementById('apprentices-tbody');
        let noMsg = document.getElementById('no-apprentices');
        tbody.innerHTML = '';

        if (!Array.isArray(apprentices) || apprentices.length === 0) {
            noMsg.style.display = '';
            return;
        }
        noMsg.style.display = 'none';

        apprentices.forEach(function (a) {
            let tr = document.createElement('tr');

            let tdId = document.createElement('td');
            tdId.textContent = a.id != null ? a.id : '';
            tr.appendChild(tdId);

            let tdFirst = document.createElement('td');
            tdFirst.textContent = a.firstName != null ? a.firstName : '';
            tr.appendChild(tdFirst);

            let tdLast = document.createElement('td');
            tdLast.textContent = a.lastName != null ? a.lastName : '';
            tr.appendChild(tdLast);

            let tdActions = document.createElement('td');

            let btnEdit = document.createElement('button');
            btnEdit.type = 'button';
            btnEdit.textContent = 'Détails';
            btnEdit.className = 'btn-edit';
            btnEdit.setAttribute('data-id', a.id);
            btnEdit.addEventListener('click', function () {
                window.location.href = '/apprentice/' + a.id;
            });
            tdActions.appendChild(btnEdit);

            let spacer = document.createTextNode(' ');
            tdActions.appendChild(spacer);

            let btnDelete = document.createElement('button');
            btnDelete.type = 'button';
            btnDelete.textContent = 'Supprimer';
            btnDelete.className = 'btn-delete';
            btnDelete.setAttribute('data-id', a.id);
            btnDelete.addEventListener('click', function () {
                if (confirm('Êtes-vous sûr de vouloir supprimer cet apprenti ?')) {
                    deleteApprentice(a.id);
                }
            });
            tdActions.appendChild(btnDelete);

            tr.appendChild(tdActions);

            tbody.appendChild(tr);
        });
    }

    document.addEventListener('DOMContentLoaded', function () {
        let mentorIdRaw = document.body.dataset.mentorId;
        let mentorId = (mentorIdRaw && mentorIdRaw !== '0') ? Number(mentorIdRaw) : null;
        const form = document.getElementById('search-form');
        const searchInput = document.querySelector('.search__input');
        let resetBtn = document.getElementById('reset-search');
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const query = searchInput.value.trim();
            if (!query)
                return;
            searchApprentices(query);
        });

        if (resetBtn){
            resetBtn.addEventListener('click', function () {
                searchInput.value = '';
                hideError();
                searchApprentices('');
            });
        }

        if (mentorId === null) {
            console.log('No mentor id available');
            return;
        }

        loadApprentices(mentorId);
    });

    function loadApprentices(mentorId) {
        fetch('/apprenticesByMentorId/' + mentorId)
            .then(function (response) {
                if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
                return response.json();
            })
            .then(function (apprentices) {
                apprenticesList = apprentices;
                renderApprenticesTable(apprentices);
            })
            .catch(function (err) {
                console.error('Failed to load apprentices:', err);
            });
    }

    function loadCompanies() {
        fetch('/companies')
            .then(function (response) {
                if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
                return response.json();
            })
            .then(function (companies) {
                let select = document.getElementById('apprentice-company');
                companies.forEach(function (c) {
                    let option = document.createElement('option');
                    option.value = c.id;
                    option.textContent = c.companyName;
                    select.appendChild(option);
                });
            })
            .catch(function (err) {
                console.error('Failed to load companies:', err);
            });
    }

    function searchApprentices(query) {
        let mentorIdRaw = document.body.dataset.mentorId;
        let mentorId = (mentorIdRaw && mentorIdRaw !== '0') ? Number(mentorIdRaw) : null;

        if (mentorId === null) {
            console.log('No mentor id available');
            return;
        }
        hideError()
        fetch('/recherche/' + mentorId + '?query=' + encodeURIComponent(query || ''))
            .then(function (response) {
                if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
                return response.json();
            })
            .then(function (apprentices) {
                renderApprenticesTable(apprentices);
            })
            .catch(function (err) {
                console.error('Failed to load apprentices:', err);
                showError('La recherche a échoué.');
            });
    }
    function showError(message) {
        let err = document.getElementById('search-error');
        if (!err) return;
        err.textContent = message || 'La recherche a échoué.';
        err.style.display = '';
    }

    function hideError() {
        let err = document.getElementById('search-error');
        if (!err) return;
        err.style.display = 'none';
    }

    function displayApprenticeForm() {
        let formContainer = document.getElementById('apprentice-form-container');
        let overlay = document.getElementById('drawer-overlay');
        formContainer.classList.remove('hidden');
        overlay?.classList.remove('hidden');

        void formContainer.offsetWidth;

        formContainer.classList.add('is-open');
        overlay?.classList.add('is-open');
        document.documentElement.classList.add('no-scroll');
        loadCompanies();
    }

    function hideApprenticeForm() {
        let formContainer = document.getElementById('apprentice-form-container');
        let overlay = document.getElementById('drawer-overlay');
        formContainer.classList.remove('is-open');
        overlay?.classList.remove('is-open');

        document.documentElement.classList.remove('no-scroll');

        const onEnd = (e) => {
            if (e.target !== formContainer) return;
            formContainer.classList.add('hidden');
            overlay?.classList.add('hidden');
            formContainer.removeEventListener('transitionend', onEnd);
        };
        formContainer.addEventListener('transitionend', onEnd);
    }

    function addApprentice() {
        let mentorIdRaw = document.body.dataset.mentorId;
        let mentorId = (mentorIdRaw && mentorIdRaw !== '0') ? Number(mentorIdRaw) : null;
        if (mentorId === null) {
            console.log('No mentor id available');
            return;
        }

        let lastName = document.getElementById('apprentice-last-name').value.trim();
        if (!lastName) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'Le nom est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let firstName = document.getElementById('apprentice-first-name').value.trim();
        if (!firstName) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'Le prénom est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let email = document.getElementById('apprentice-email').value.trim();
        if (!email) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'L\'email est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        if (!/\S+@\S+\.\S+/.test(email)) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'L\'email n\'est pas valide.';
            errorMsg.style.display = '';
            return;
        }
        let phone = document.getElementById('apprentice-phone').value.trim();
        if (!phone) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'Le téléphone est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let phonePattern = /^\+?[0-9\s\-()]{7,15}$/
        if (!phonePattern.test(phone)) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'Le numéro de téléphone n\'est pas valide.';
            errorMsg.style.display = '';
            return;
        }
        let program = document.getElementById('apprentice-program').value.trim();
        if (!program) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'Le programme est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let academicYear = document.getElementById('apprentice-academic-year').value.trim();
        if (!academicYear) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'L\'année académique est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let major = document.getElementById('apprentice-major').value.trim();
        if (!major) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'La majeure est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let companySelect = document.getElementById('apprentice-company');
        if (!companySelect.value) {
            let errorMsg = document.getElementById('apprentice-error-msg');
            errorMsg.textContent = 'L\'entreprise est obligatoire.';
            errorMsg.style.display = '';
            return;
        }
        let companyId = companySelect.value ? Number(companySelect.value) : null;
        let errorMsg = document.getElementById('apprentice-error-msg');
        errorMsg.style.display = 'none';

        let apprenticeData = {
            program: program,
            academicYear: academicYear,
            major: major,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phone: phone,
            company: { id: companyId },
            apprenticeshipMentor: { id: mentorId }
        };

        const {token, header} = readCsrf();
        const headers = {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        };
        if (token) headers[header] = token;

        fetch('/createApprentice/', {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(apprenticeData),
            credentials: 'same-origin'
        })
            .then(function (response) {
                if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
                return response.json().catch(() => null);
            })
            .then(function () {
                hideApprenticeForm();
                document.getElementById('apprentice-last-name').value = '';
                document.getElementById('apprentice-first-name').value = '';
                document.getElementById('apprentice-email').value = '';
                document.getElementById('apprentice-phone').value = '';
                document.getElementById('apprentice-program').value = '';
                document.getElementById('apprentice-academic-year').value = '';
                document.getElementById('apprentice-major').value = '';

                loadApprentices(mentorId);
            })
    }

    function deleteApprentice(apprenticeId) {
        const {token, header} = readCsrf();
        const headers = {
            'Accept': 'application/json'
        };
        if (token) headers[header] = token;

        fetch('/deleteApprentice/' + apprenticeId + '/', {
            method: 'DELETE',
            headers: headers,
            credentials: 'same-origin'
        })
            .then(function (response) {
                if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
                let mentorIdRaw = document.body.dataset.mentorId;
                let mentorId = (mentorIdRaw && mentorIdRaw !== '0') ? Number(mentorIdRaw) : null;
                if (mentorId !== null) {
                    loadApprentices(mentorId);
                }
            })
            .catch(function (err) {
                console.error('Failed to delete apprentice:', err);
            });
    }

    function getNextAcademicYear(currentYear) {
        let parts = currentYear.split('-');
        if (parts.length !== 2) return currentYear;
        let startYear = parseInt(parts[0], 10);
        let endYear = parseInt(parts[1], 10);
        if (isNaN(startYear) || isNaN(endYear)) return currentYear;
        return (startYear + 1) + '-' + (endYear + 1);
    }

    function getNextProgram(currentProgram) {
        const programMap = {
            'ING1': 'ING2',
            'ING2': 'ING3',
            'ING3': 'Gradué',
            'Gradué': 'Gradué'
        };
        return programMap[currentProgram] || currentProgram;
    }

    function generateNewYear() {
        for (let apprentice of apprenticesList) {
            const {token, header} = readCsrf();
            const headers = {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            };
            if (token) headers[header] = token;

            let apprenticeData = {
                program: getNextProgram(apprentice.program),
                academicYear: getNextAcademicYear(apprentice.academicYear),
                major: apprentice.major,
                firstName: apprentice.firstName,
                lastName: apprentice.lastName,
                email: apprentice.email,
                phone: apprentice.phone,
                company: { id: apprentice.companyId },
                apprenticeshipMentor: { id: apprentice.mentorId }
            };

            fetch('updateApprentice/' + apprentice.id + '/', {
                method: 'PUT',
                headers: headers,
                body: JSON.stringify(apprenticeData),
                credentials: 'same-origin'
            }).then(function (response) {
                if (!response.ok) throw new Error('Network response was not ok: ' + response.status);
                return response.json().catch(() => null);
            })
                .then(function () {
                    let mentorIdRaw = document.body.dataset.mentorId;
                    let mentorId = (mentorIdRaw && mentorIdRaw !== '0') ? Number(mentorIdRaw) : null;
                    if (mentorId === null) return;
                    console.log("Updated apprentice id %d for new year", apprentice.id);
                    loadApprentices(mentorId);
                })
        }
    }

    window.displayApprenticeForm = displayApprenticeForm;
    window.hideApprenticeForm = hideApprenticeForm;
    window.addApprentice = addApprentice;
    window.generateNewYear = generateNewYear;
})();