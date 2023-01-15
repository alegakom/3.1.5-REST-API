

const id_ed1 = document.getElementById('id_ed');
const name_ed1 = document.getElementById('name_ed');
const lastname_ed1 = document.getElementById('lastname_ed');
const age_ed1 = document.getElementById('age_ed');
const username_ed1 = document.getElementById('username_ed');
const password_ed1 = document.getElementById('password_ed');




async function editAdminModalData(id) {
    const urlDataEd = '/api/users/' + id;
    $('#editModal').modal('show');
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        let userData =
            await usersPageEd.json().then(user => {
                id_ed1.value = `${user.id}`;
                name_ed1.value = `${user.name}`;
                lastname_ed1.value = `${user.lastname}`;
                age_ed1.value = `${user.age}`;
                username_ed1.value = `${user.username}`;
            })
    } else {
        alert(`Error here, ${usersPageEd.status}`)
    }
}


// ещё не доделал
async function editUsers() {
    let usersPageEd = await fetch('/api/users/' + id_ed1.value);
    let userData =
        await usersPageEd.json();
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: id_ed1.value,
            name: name_ed1.value,
            age: age_ed1.value,
            lastname: lastname_ed1.value,
            username: username_ed1.value,
            password: password_ed1.value,
        })

    }
    const change = '/api/users/' + id_ed1.value
    await fetch(change, method).then(() => {
        $('#editCloseBtn').click();
        getAllUsers();
    })
}
