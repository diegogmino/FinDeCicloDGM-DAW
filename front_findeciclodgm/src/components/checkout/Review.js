import * as React from "react";
import Typography from "@mui/material/Typography";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import Grid from "@mui/material/Grid";

const products = [
  {
    name: "El cuervo",
    price: "9.99€",
  },
  {
    name: "El padrino",
    price: "9.99€",
  },
  {
    name: "Blade Runner",
    price: "15.99€",
  },
  { name: "Envío", price: "3.99€" },
];

const addresses = ["1 MUI Drive", "Reactville", "Anytown", "99999", "USA"];
const payments = [
  { name: "Card type", detail: "Visa" },
  { name: "Card holder", detail: "Mr John Smith" },
  { name: "Card number", detail: "xxxx-xxxx-xxxx-1234" },
  { name: "Expiry date", detail: "04/2024" },
];

export default function Review(props) {
  const { cart, totalCart } = props;

  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom>
        Resumen del pedido
      </Typography>
      <List disablePadding>
        {cart.map((film) => (
          <ListItem key={film.id} sx={{ py: 1, px: 0 }}>
            <ListItemText
              primary={
                <Typography type="body2" style={{ color: "#000000" }}>
                  {film.titulo} x{film.qty}
                </Typography>
              }
            />
            <Typography variant="body2">{film.precio} €</Typography>
          </ListItem>
        ))}

        {totalCart < 40 && (
          <div>
            <ListItem sx={{ py: 1, px: 0 }}>
              <ListItemText primary="Envío" />
              <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                3.99 €
              </Typography>
            </ListItem>

            <ListItem sx={{ py: 1, px: 0 }}>
              <ListItemText primary="Total" />
              <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                {totalCart + 3.99} €
              </Typography>
            </ListItem>
          </div>
        )}

        {totalCart > 40 && (
          <div>
            <ListItem sx={{ py: 1, px: 0 }}>
              <ListItemText primary="Envío" />
              <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                0.00 €
              </Typography>
            </ListItem>

            <ListItem sx={{ py: 1, px: 0 }}>
              <ListItemText primary="Total" />
              <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                {totalCart} €
              </Typography>
            </ListItem>
          </div>
        )}
      </List>
    </React.Fragment>
  );
}
