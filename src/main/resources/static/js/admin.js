

const url = '/api/getAllUsers';


async function getAllUsers() {
    let page = await fetch(url);

    if(page.ok) {
        let users = await page.json();
        getListOfUsers(users);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function  getListOfUsers(users) {
    let table = document.getElementById("tbody");
    let dataHtml = '';

    for (let user of users) {
        let editRole = JSON.stringify(user.rolesForWeb).replaceAll("ROLE_", "");
        let roles = JSON.parse(editRole);

        dataHtml +=
            `<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.lastname}</td>
    <td>${user.age}</td>
    <td>${user.username}</td>
    <td>${roles}</td>   
<!--    EDIT-->
    <td>
        <p><button type="button" class="btn btn-primary" 
        data-toggle="modal" data-target="#editModal" onclick="editAdminModalData(${user.id})">Edit</button></p>
    </td>
<!--    DELETE-->
    <td>
        <form method="POST">
            <p><button class="btn btn-danger" th:formaction="${'/api/admin/delete/' + user.id}">Delete</button></p>
        </form>
    </td>
</tr>`

        table.innerHTML = dataHtml;
    }
}
getAllUsers();
