import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import loantypeService from "../services/loantype.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import Snackbar from "@mui/material/Snackbar"; // Para mostrar mensajes de error

const AddEditExtraHours = () => {
  const [loanType, setLoanType] = useState({
    rutClient: "",
    type: "",
    propertyValue: "",
    financeRate: "",
    interestRate: "",
    years: "",
    monthlyPayment: 0,
    totalCost: 0,
  });

  const [errorMessage, setErrorMessage] = useState(""); // Estado para el mensaje de error
  const [openSnackbar, setOpenSnackbar] = useState(false); // Estado para controlar la Snackbar

  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      loantypeService
        .getLoanById(id)
        .then((response) => {
          setLoanType(response.data);
        })
        .catch((error) => {
          console.error("Error al cargar los datos del tipo de préstamo", error);
        });
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoanType((prev) => ({ ...prev, [name]: value }));
  };

  const calculateValues = () => {
    const financedAmount = loanType.propertyValue * (loanType.financeRate / 100);
    const interestMonthly = (loanType.interestRate / 100) / 12;
    const numberOfPayments = loanType.years * 12;

    const monthlyPayment =
      financedAmount *
      ((interestMonthly * Math.pow(1 + interestMonthly, numberOfPayments)) /
        (Math.pow(1 + interestMonthly, numberOfPayments) - 1));

    const insurance = financedAmount * 0.0003;
    const fireInsurance = 20000;
    const totalMonthlyPayment = monthlyPayment + fireInsurance + insurance;
    const totalCost = totalMonthlyPayment * numberOfPayments + financedAmount * 0.01;

    setLoanType((prev) => ({
      ...prev,
      monthlyPayment: Math.round(totalMonthlyPayment),
      totalCost: Math.round(totalCost),
    }));
  };

  useEffect(() => {
    if (
      loanType.propertyValue &&
      loanType.financeRate &&
      loanType.interestRate &&
      loanType.years
    ) {
      calculateValues();
    }
  }, [loanType.propertyValue, loanType.financeRate, loanType.interestRate, loanType.years]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (id) {
      loantypeService
        .updateLoanType(loanType)
        .then((response) => {
          console.log("Tipo de préstamo actualizado exitosamente", response.data);
          navigate("/extraHours/list");
        })
        .catch((error) => {
          setErrorMessage(error.response.data); // Captura el mensaje de error del backend
          setOpenSnackbar(true); // Muestra la Snackbar
          console.error("Error al actualizar el tipo de préstamo", error);
        });
    } else {
      loantypeService
        .saveLoanType(loanType)
        .then((response) => {
          console.log("Tipo de préstamo añadido exitosamente", response.data);
          navigate("/extraHours/list");
        })
        .catch((error) => {
          setErrorMessage(error.response.data); // Captura el mensaje de error del backend
          setOpenSnackbar(true); // Muestra la Snackbar
          console.error("Error al añadir el tipo de préstamo", error);
        });
    }
  };

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  return (
    <Box
      component="form"
      onSubmit={handleSubmit}
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
    >
      <h3>{id ? "Editar" : "Agregar"} tipo de préstamo</h3>
      <hr />

      <FormControl fullWidth>
        <TextField
          label="RUT del Cliente"
          name="rutClient"
          value={loanType.rutClient}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          name="type"
          select
          label="Tipo de préstamo"
          value={loanType.type}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        >
          <MenuItem value="primera vivienda">Primera vivienda</MenuItem>
          <MenuItem value="segunda vivienda">Segunda vivienda</MenuItem>
          <MenuItem value="propiedad comercial">Propiedad comercial</MenuItem>
          <MenuItem value="remodelación">Remodelación</MenuItem>
        </TextField>
      </FormControl>

      <FormControl fullWidth>
        <TextField
          label="Valor de la propiedad"
          name="propertyValue"
          value={loanType.propertyValue}
          onChange={handleChange}
          fullWidth
          margin="normal"
          type="number"
          required
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          label="Financiamiento (%)"
          name="financeRate"
          value={loanType.financeRate}
          onChange={handleChange}
          fullWidth
          margin="normal"
          type="number"
          required
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          label="Tasa de Interés (%)"
          name="interestRate"
          value={loanType.interestRate}
          onChange={handleChange}
          fullWidth
          margin="normal"
          type="number"
          required
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          label="Años"
          name="years"
          value={loanType.years}
          onChange={handleChange}
          fullWidth
          margin="normal"
          type="number"
          required
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          label="Cuota mensual"
          value={loanType.monthlyPayment}
          fullWidth
          margin="normal"
          InputProps={{ readOnly: true }}
        />
      </FormControl>
      <FormControl fullWidth>
        <TextField
          label="Costo total"
          value={loanType.totalCost}
          fullWidth
          margin="normal"
          InputProps={{ readOnly: true }}
        />
      </FormControl>

      <Button
        variant="contained"
        color="primary"
        type="submit"
        startIcon={<SaveIcon />}
        style={{ marginTop: "1rem" }}
      >
        {id ? "Editar" : "Agregar"}
      </Button>

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        message={errorMessage}
      />
    </Box>
  );
};

export default AddEditExtraHours;
