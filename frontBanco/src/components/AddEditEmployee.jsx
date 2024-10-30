import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import clientService from "../services/client.service";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

const AddEditClient = () => {
  const [client, setClient] = useState({
    rut: "",
    name: "",
    lastName: "",
    age: "",
    email: "",
    phone: "",
    address: "",
  });

  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      clientService.getClient(id)
        .then((response) => setClient(response.data))
        .catch((error) => console.error("Error al cargar los datos del cliente", error));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setClient({ ...client, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const clientData = id ? clientService.updateClient(client) : clientService.saveClient(client);
    
    clientData
      .then(() => navigate("/employee/list"))
      .catch((error) => console.error("Error al guardar el cliente", error));
  };

  return (
    <Paper style={{ padding: "1rem", maxWidth: "500px", margin: "2rem auto" }}>
      <Typography variant="h5" component="h2" gutterBottom>
        {id ? "Editar Cliente" : "Añadir Nuevo Cliente"}
      </Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          label="Rut"
          name="rut"
          value={client.rut}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Nombre"
          name="name"
          value={client.name}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Apellido"
          name="lastName"
          value={client.lastName}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Edad"
          name="age"
          value={client.age}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Email"
          name="email"
          value={client.email}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Teléfono"
          name="phone"
          value={client.phone}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Dirección"
          name="address"
          value={client.address}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <Button
          variant="contained"
          color="primary"
          type="submit"
          style={{ marginTop: "1rem" }}
        >
          {id ? "Actualizar Cliente" : "Añadir Cliente"}
        </Button>
      </form>
    </Paper>
  );
};

export default AddEditClient;
