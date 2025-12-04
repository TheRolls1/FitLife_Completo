function registrarUsuario() {
  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const role = document.getElementById("role").value;

  fetch("http://localhost:8080/api/usuarios/crear", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, email, password, role })
  })
    .then(res => res.json())
    .then(data => {
      alert("Usuario creado exitosamente");
      window.location.href = "login.html";
    })
    .catch(err => console.error("Error al registrar:", err));
}

function cargarUsuarios() {
  const token = localStorage.getItem("jwt");
  if (!token) return logout();

  fetch("http://localhost:8080/api/usuarios", {
    headers: { "Authorization": "Bearer " + token }
  })
    .then(res => {
      if (res.status === 401) throw new Error("No autorizado");
      return res.json();
    })
    .then(data => {
      const tabla = document.getElementById("tabla-usuarios");
      tabla.innerHTML = data.map(u => `
        <tr>
          <td class="p-2">${u.name}</td>
          <td class="p-2">${u.email}</td>
          <td class="p-2">${u.role}</td>
        </tr>
      `).join("");
    })
    .catch(err => {
      console.error(err);
      logout();
    });
}
