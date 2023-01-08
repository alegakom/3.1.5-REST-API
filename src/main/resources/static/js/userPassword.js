

const form_ed = document.getElementById('changePassword');
const password_ed = document.getElementById('password_ed');
const userUrl = '/api/getUser';
const changePassword = '/api/changePassword';

async function editModalData() {
    $('#userModal').modal('show');
    let usersPageEd = await fetch(userUrl);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(user => {
                password_ed.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }                  // ИСПОЛЬЗОВАТЬ ДЛЯ ЗАПОЛНЕНИЕ МОДАЛЬНЫЪ ОКОН
}

async function editUser() {
    let usersPageEd = await fetch(userUrl);
    let userData =
        await usersPageEd.json();
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: userData.id,
            name: userData.name,
            age: userData.age,
            lastname: userData.lastname,
            username: userData.username,
            roles: userData.roles,
            password: password_ed.value,

        })

    }

    await fetch(changePassword,method).then(() => {
        $('#editCloseBtn').click();
        getUser();
    })
}
