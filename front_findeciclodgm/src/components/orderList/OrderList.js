import * as React from "react";
import PropTypes from "prop-types";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import { DateTime } from "luxon";

function createData(name, calories, fat, carbs, protein, price) {
  return {
    name,
    calories,
    fat,
    carbs,
    protein,
    price,
    history: [
      {
        date: "2020-01-05",
        customerId: "11091700",
        amount: 3,
      },
      {
        date: "2020-01-02",
        customerId: "Anonymous",
        amount: 1,
      },
    ],
  };
}

function Row(props) {
  const { order } = props;
  const [open, setOpen] = React.useState(false);

  return (
    <React.Fragment>
      <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowDownIcon /> : <KeyboardArrowRightIcon />}
          </IconButton>
        </TableCell>
        <TableCell align="left" component="th" scope="row">
          {order.id}
        </TableCell>
        <TableCell align="left">{order.direccionEnvio}</TableCell>
        <TableCell align="left">
          {order.entregado == true && "Sí"}
          {order.entregado == false && "No"}
        </TableCell>
        <TableCell align="left">{order.precioTotal} €</TableCell>
        <TableCell align="left">
          {DateTime.fromMillis(order.fechaPedido).toLocaleString()}
        </TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                Peliculas
              </Typography>
              <Table size="small" aria-label="purchases">
                <TableHead>
                  <TableRow>
                    <TableCell align="left">Código de barras</TableCell>
                    <TableCell align="left">Título</TableCell>
                    <TableCell align="left">Precio</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {order.peliculas.map((films) => (
                    <TableRow key={films.codigoBarras}>
                      <TableCell component="th" scope="row" align="left">
                        {films.codigoBarras}
                      </TableCell>
                      <TableCell align="left">{films.titulo}</TableCell>
                      <TableCell align="left">{films.precio} €</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}

Row.propTypes = {
  row: PropTypes.shape({
    calories: PropTypes.number.isRequired,
    carbs: PropTypes.number.isRequired,
    fat: PropTypes.number.isRequired,
    history: PropTypes.arrayOf(
      PropTypes.shape({
        amount: PropTypes.number.isRequired,
        customerId: PropTypes.string.isRequired,
        date: PropTypes.string.isRequired,
      })
    ).isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    protein: PropTypes.number.isRequired,
  }).isRequired,
};

const rows = [
  createData("Frozen yoghurt", 159, 6.0, 24, 4.0, 3.99),
  createData("Ice cream sandwich", 237, 9.0, 37, 4.3, 4.99),
  createData("Eclair", 262, 16.0, 24, 6.0, 3.79),
  createData("Cupcake", 305, 3.7, 67, 4.3, 2.5),
  createData("Gingerbread", 356, 16.0, 49, 3.9, 1.5),
];

export default function OrderList(props) {
  const { orders } = props;

  return (
    <div className="hidden md:flex flex-col mb-10">
      <div className="flex justify-center">
        <div aria-label="collapsible table" className="table w-3/4">
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell align="left">#</TableCell>
              <TableCell align="left">Dirección de envío</TableCell>
              <TableCell align="left">Entregado</TableCell>
              <TableCell align="left">Precio total</TableCell>
              <TableCell align="left">Fecha pedido</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {orders.map((order) => (
              <Row key={order.id} order={order} />
            ))}
          </TableBody>
        </div>
      </div>
    </div>
  );
}
