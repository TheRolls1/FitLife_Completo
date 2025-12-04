function login() {
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  fetch("http://localhost:8080/auth/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  })
    .then(res => {
      if (!res.ok) throw new Error("Login fallido");
      return res.text();
    })
    .then(token => {
      localStorage.setItem("jwt", token);
      window.location.href = "index.html";
    })
    .catch(err => alert("Credenciales incorrectas"));
}

function logout() {
  localStorage.removeItem("jwt");
  window.location.href = "login.html";
}
