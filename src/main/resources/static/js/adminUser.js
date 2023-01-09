
const password_ed = document.getElementById('passwordEdit');
const changeAdminPassword = '/api/adminChangePassword';
const adminUrl = '/api/getAdminUser';

async function getAdmin() {
    let page = await fetch(adminUrl);

    if(page.ok) {
        let user = await page.json();

        getInformationAboutAdmin(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function  getInformationAboutAdmin(user) {
    let tr = document.createElement("tr");
    let editRole = JSON.stringify(user.rolesForWeb).replaceAll("ROLE_", "");
    let roles = JSON.parse(editRole);


    tr.innerHTML =
        `<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.lastname}</td>
    <td>${user.age}</td>
    <td>${user.username}</td>
    <td>${roles}</td>
    <td>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#passwordModal" onclick="editAdmin()">Change password</button>
                                                            
    </td>                        
</tr>`


    document.getElementById(`adminTbody`).append(tr);
}
getAdmin();

async function editAdmin() {
    $('#passwordModal').modal('show');
    let usersPageEd = await fetch(adminUrl);
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

    await fetch(changeAdminPassword,method).then(() => {
        $('#CloseBtn').click();
    })
}
