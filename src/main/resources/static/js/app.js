const table = document.getElementById("userTable");

const modal = document.getElementById("modal");

const openModal = document.getElementById("openModal");

const createBtn = document.getElementById("createBtn");

openModal.onclick = () => {

    modal.style.display = "flex";

};

window.onclick = (e) => {

    if (e.target === modal)
        modal.style.display = "none";

};

async function loadUsers() {

    const response = await fetch("/api/users");

    const users = await response.json();

    table.innerHTML = "";

    users.forEach(user => {

        table.innerHTML += `

        <tr>

        <td>${user.id}</td>

        <td>${user.name}</td>

        <td>${user.email}</td>

        <td>

        <button onclick="deleteUser(${user.id})">

        Delete

        </button>

        </td>

        </tr>

        `;

    });

}

createBtn.onclick = async () => {

    const name = document.getElementById("name").value;

    const email = document.getElementById("email").value;

    await fetch("/api/users", {

        method: "POST",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify({

            name,

            email

        })

    });

    modal.style.display = "none";

    loadUsers();

};

async function deleteUser(id) {

    await fetch(`/api/users/${id}`, {

        method: "DELETE"

    });

    loadUsers();

}

loadUsers();