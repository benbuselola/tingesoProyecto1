import { useState } from "react";
import { useNavigate } from "react-router-dom";
import creditService from "../services/credit.service"; 
import CalculateIcon from "@mui/icons-material/Calculate";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import Typography from "@mui/material/Typography"; 

const CreditCalculate = () => {
  const [amount, setAmount] = useState("");
  const [years, setYears] = useState("");
  const [interestRate, setInterestRate] = useState("");
  const [type, setType] = useState("");
  const [monthlyPayment, setMonthlyPayment] = useState(null);
  const [error, setError] = useState(null); 
  const navigate = useNavigate();

  const calculateCredit = (e) => {
    e.preventDefault();
    console.log("Solicitar calcular crédito.", amount, years, interestRate, type);

    creditService
      .simulateCredit(amount, years, interestRate, type) 
      .then((response) => {
        console.log("Cálculo realizado:", response.data);
        setMonthlyPayment(response.data); 
        setError(null); 
      })
      .catch((error) => {
        console.log("Ha ocurrido un error al intentar calcular el crédito.", error);
        
        setError(error.response?.data || "Error desconocido"); 
      });
    console.log("Fin cálculo de crédito.");
  };

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> Simular crédito </h3>
      <hr />
      <FormControl fullWidth>
        <TextField
          id="amount"
          label="Monto del credito"
          value={amount}
          variant="standard"
          onChange={(e) => setAmount(e.target.value)}
          type="number"
        />
      </FormControl>
      <FormControl fullWidth>
        <TextField
          id="years"
          label="Años"
          value={years}
          variant="standard"
          onChange={(e) => setYears(e.target.value)}
          type="number"
        />
      </FormControl>
      <FormControl fullWidth>
        <TextField
          id="interestRate"
          label="Tasa de Interés (%)"
          value={interestRate}
          variant="standard"
          onChange={(e) => setInterestRate(e.target.value)}
          type="number"
        />
      </FormControl>
      <FormControl fullWidth>
        <TextField
          id="type"
          select
          label="Tipo de Crédito"
          value={type}
          variant="standard"
          onChange={(e) => setType(e.target.value)}
        >
          <MenuItem value="primera vivienda">Primera vivienda</MenuItem>
          <MenuItem value="segunda vivienda">Segunda vivienda</MenuItem>
          <MenuItem value="propiedad comercial">Propiedad comercial</MenuItem>
          <MenuItem value="remodelación">Remodelación</MenuItem>
        </TextField>
      </FormControl>
      <FormControl>
        <br />
        <Button
          variant="contained"
          color="info"
          onClick={(e) => calculateCredit(e)}
          style={{ marginLeft: "0.5rem" }}
          startIcon={<CalculateIcon />}
        >
          Simular credito (Chile)
        </Button>
      </FormControl>

      {monthlyPayment !== null && (
        <Typography variant="h6" color="success.main" style={{ marginTop: '1rem' }}>
          Cuota Mensual: ${monthlyPayment.toFixed(2)}
        </Typography>
      )}
      
      {error && (
        <Typography variant="h6" color="error.main" style={{ marginTop: '1rem' }}>
          Error: {error}
        </Typography>
      )}
    </Box>
  );
};

export default CreditCalculate;
