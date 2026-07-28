const table = document.getElementById("userTable");

const modal = document.getElementById("modal");

const openModal = document.getElementById("openModal");

const createBtn = document.getElementById("createBtn");

const closeModal = document.getElementById("closeModal");

const cancelBtn = document.getElementById("cancelBtn");

const searchInput = document.getElementById("searchInput");

const userCount = document.getElementById("userCount");

let editingUserId = null;

let users = [];

openModal.onclick = () => {
    editingUserId = null;

    document.getElementById("modalTitle").innerText = "Create User";

    document.getElementById("name").value = "";

    document.getElementById("email").value = "";

    modal.style.display = "flex";
};

closeModal.onclick = () => {

    modal.style.display = "none";

};

cancelBtn.onclick = () => {

    modal.style.display = "none";

};

window.onclick = (e) => {

    if (e.target === modal) {

        modal.style.display = "none";

    }

};

async function loadUsers() {

    const response = await fetch("/api/users");

    users = await response.json();

    userCount.innerText = users.length;

    renderUsers(users);

}

function renderUsers(data) {

    table.innerHTML = "";

    data.forEach(user => {

        table.innerHTML += `
        <tr>

            <td>${user.id}</td>

            <td>${user.name}</td>

            <td>${user.email}</td>

            <td class="actions">

                <button
                    class="edit-btn"
                    onclick="editUser(${user.id},'${user.name}','${user.email}')">

                    Edit

                </button>

                <button
                    class="delete-btn"
                    onclick="deleteUser(${user.id})">

                    Delete

                </button>

            </td>

        </tr>
        `;

    });

}

searchInput.addEventListener("input", () => {

    const text = searchInput.value.toLowerCase().trim();

    const filtered = users.filter(user =>

        user.name.toLowerCase().includes(text) ||

        user.email.toLowerCase().includes(text)

    );

    renderUsers(filtered);

});

createBtn.onclick = async () => {

    const name = document.getElementById("name").value.trim();

    const email = document.getElementById("email").value.trim();

    if (!name || !email) {

        alert("Please fill all fields.");

        return;

    }

    const url = editingUserId === null

        ? "/api/users"

        : `/api/users/${editingUserId}`;

    const method = editingUserId === null

        ? "POST"

        : "PUT";

    await fetch(url, {

        method,

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify({

            name,

            email

        })

    });

    editingUserId = null;

    modal.style.display = "none";

    document.getElementById("modalTitle").innerText = "Create User";

    document.getElementById("name").value = "";

    document.getElementById("email").value = "";

    loadUsers();

};

async function deleteUser(id) {

    const confirmDelete = confirm("Delete this user?");

    if (!confirmDelete) return;

    await fetch(`/api/users/${id}`, {

        method: "DELETE"

    });

    loadUsers();

}

function editUser(id, name, email) {

    editingUserId = id;

    document.getElementById("modalTitle").innerText = "Edit User";

    document.getElementById("name").value = name;

    document.getElementById("email").value = email;

    modal.style.display = "flex";

}

loadUsers();