
const url = '/api/getUser';

async function getUser() {
    let page = await fetch(url);

    if(page.ok) {
        let user = await page.json();

        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function  getInformationAboutUser(user) {
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
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#userModal" onclick="editModalData()">Change password</button>
                                                            
    </td>                        
</tr>`


    document.getElementById(`tbody`).append(tr);
}
getUser();
