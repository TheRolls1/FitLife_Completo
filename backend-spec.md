FitLife SPA - Backend specification (Node.js / Express)

Stack sugerido: Node.js + Express + PostgreSQL (o Mongo) + bcrypt + jsonwebtoken + multer

Endpoints:
POST /api/auth/register
  body: { nombre, email, password }
  response: { token, user: { id, nombre, email } }

POST /api/auth/login
  body: { email, password }
  response: { token, user }

GET /api/plans?type=&level=&nutritionLevel=
  response: [ { id, titulo, descripcion, nivel, tipo, duracionMinutos, precioCLP } ]

Protected endpoints require Authorization: Bearer <token> header

Notes:
- For local Android emulator use http://10.0.2.2:3000
- Use HTTPS in production
