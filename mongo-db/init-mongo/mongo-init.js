db = db.getSiblingDB("usuarios_db"); // Cambia al DB deseado

db.createUser({
    user: "admin",
    pwd: "admin123",
    roles: [{ role: "readWrite", db: "usuarios_db" }]
});

db.usuarios.insertOne({
    nombre: "Admin",
    correo: "admin@correo.com",
    username: "admin",
    // Contraseña cifrada: "admin123"
    password: "$2a$10$fzTCcAoAfYO8QqXBgs97ROZFLzNaVKz3ieCvFyY5UMolEoyxO0cUG"
});
