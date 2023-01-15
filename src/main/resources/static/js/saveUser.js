const form_new = document.getElementById('formForNewUser');




    async function addNewUser() {
        const urlNew = '/api/newUser';
        let method = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: form_new.name.value,
                lastname: form_new.lastname.value,
                age: form_new.age.value,
                username: form_new.username.value,
                password: form_new.password.value,
                roles: form_new.roles.value
            })
        }
        await fetch(urlNew,method).then(() => {
            form_new.reset()
            getAllUsers();
        });
    }
