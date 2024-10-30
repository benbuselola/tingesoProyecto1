import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import MoreTimeIcon from "@mui/icons-material/MoreTime";
import loantypeService from "../services/loantype.service";

const LoanTypeList = () => {
  const [loantypes, setLoanTypes] = useState([]);
  const navigate = useNavigate();

  const init = () => {
    loantypeService
      .getAll()
      .then((response) => {
        console.log("Tipos de préstamos obtenidos", response.data);
        setLoanTypes(response.data);
      })
      .catch((error) => {
        console.log("Error al intentar mostrar los tipos de préstamos", error);
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleDelete = (id) => {
    const confirmDelete = window.confirm("¿Está seguro que desea borrar este tipo de préstamo?");
    if (confirmDelete) {
      loantypeService
        .deleteLoanTypeById(id)
        .then((response) => {
          console.log("Tipo de préstamo eliminado", response.data);
          init();
        })
        .catch((error) => {
          console.log("Error al intentar eliminar el tipo de préstamo", error);
        });
    }
  };

  const handleEdit = (id) => {
    navigate(`/extraHours/edit/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/extraHours/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<MoreTimeIcon />}
        >
          Ingresar Nuevo Tipo de Préstamo
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>ID</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Tipo de prestamo</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Rut del cliente</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Interés (%)</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Financiamiento(%)</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Pago mensual</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Pago Total</TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>Plazo</TableCell>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>Acciones</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {loantypes.map((loanType) => (
            <TableRow key={loanType.id}>
              <TableCell align="left">{loanType.id}</TableCell>
              <TableCell align="left">{loanType.type}</TableCell>
              <TableCell align="left">{loanType.rutClient}</TableCell>
              <TableCell align="left">{loanType.interestRate}%</TableCell>
              <TableCell align="left">{loanType.financeRate}%</TableCell>
              <TableCell align="left">${loanType.monthlyPayment}</TableCell>
              <TableCell align="left">${loanType.totalCost}</TableCell>

              <TableCell align="left">{loanType.years} años</TableCell>
              <TableCell align="center">
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(loanType.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Editar
                </Button>
                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(loanType.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Eliminar
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default LoanTypeList;
