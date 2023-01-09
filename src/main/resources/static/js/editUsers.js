

const id_ed = document.getElementById('id_ed');
const name_ed = document.getElementById('name_ed');
const lastname_ed = document.getElementById('lastname_ed');
const age_ed = document.getElementById('age_ed');
const username_ed = document.getElementById('username_ed');
const password_ed = document.getElementById('password_ed');
const roles_ed = document.getElementById('rolesForEditing');

async function editAdminModalData(id) {
    $('#editModal').modal('show');
    const  urlDataEd = 'api/users/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(user => {
                id_ed.value = `${user.id}`;
                name_ed.value = `${user.name}`;
                lastname_ed.value = `${user.lastname}`;
                age_ed.value = `${user.age}`;
                username_ed.value = `${user.username}`;
                password_ed.value = `${user.password}`;
            })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}


// ещё не доделал
async function editUser() {
    let usersPageEd = await fetch('/api/users/' + id_ed.value);
    let userData =
        await usersPageEd.json();
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: id_ed.value,
            name: name_ed.value,
            age: age_ed.value,
            lastname: lastname_ed.value,
            username: username_ed.value,
            roles: roles_ed.value,
            password: password_ed.value,
        })

    }

    await fetch(usersPageEd, method).then(() => {
        $('#editCloseBtn').click();
        getAllUsers();
    })
}
