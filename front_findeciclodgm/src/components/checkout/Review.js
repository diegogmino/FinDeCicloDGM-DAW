import * as React from "react";
import Typography from "@mui/material/Typography";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import Grid from "@mui/material/Grid";




export default function Review(props) {
  const { cart, totalCart } = props;
  const [addresses, setAdresses] = React.useState([]);
  const [userData, setUserData] = React.useState({});

  React.useEffect(() => {
    let user = JSON.parse(localStorage.getItem("user"));
    setUserData({
      name: user.nombre,
      lastname: user.apellido,
      mail: user.email
    })
    setAdresses([user.direccion, user.pais]);
    console.log(user);
  }, []);

  return (
    <React.Fragment>
      <h1 className="text-center text-2xl mb-4">Resumen del pedido</h1>
      <List disablePadding>
        {cart.map((film) => (
          <ListItem key={film.id} sx={{ py: 1, px: 0 }}>
            <ListItemText
              primary={
                <Typography type="body2" style={{ color: "#000000" }}>
                  {film.titulo} x {film.qty}
                </Typography>
              }
            />
            <Typography variant="body2">{film.precio * film.qty} €</Typography>
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
                {Math.round(((totalCart + 3.99) + Number.EPSILON) * 100) / 100} €
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
      <Grid container spacing={2}>
        <Grid item xs={12} sm={12}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }}>
            Envío a
          </Typography>
          <p className="font-bold">{userData.name} {userData.lastname}</p>
          <p className="font-bold">{addresses.join(", ")}</p>
          <Typography gutterBottom>{userData.mail}</Typography>
        </Grid>
      </Grid>
    </React.Fragment>
  );
}
