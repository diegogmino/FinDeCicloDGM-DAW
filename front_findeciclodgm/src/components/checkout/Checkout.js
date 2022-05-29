import CssBaseline from "@mui/material/CssBaseline";
import AppBar from "@mui/material/AppBar";
import Container from "@mui/material/Container";
import Toolbar from "@mui/material/Toolbar";
import Paper from "@mui/material/Paper";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Review from "./Review";
import { useNavigate } from "react-router-dom";
import UsersApi from "../../api/usersApi";

import React from "react";
import ReactDOM from "react-dom";
const PayPalButton = window.paypal.Buttons.driver("react", { React, ReactDOM });

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {"Copyright © "}
      <Link color="inherit" href="https://mui.com/">
        DCine
      </Link>{" "}
      {new Date().getFullYear()}
      {"."} Todos los derechos reservados.
    </Typography>
  );
}

const theme = createTheme({
  palette: {
    primary: {
      light: "#820933",
      main: "#820933",
      dark: "#686963",
      contrastText: "#fff",
      focus: "#fff",
    },
    secondary: {
      light: "#820933",
      main: "#820933",
      dark: "#ba000d",
      contrastText: "#000",
    },
  },
});

export default function Checkout(props) {
  const {
    cart,
    setCart,
    totalCart,
    setTotalItemsCart,
    setTotalCart,
    setOrderID,
  } = props;

  const navigate = useNavigate();

  const finish = () => {
    navigate("/checkout/completed");
  };

  const createOrder = (data, actions) => {
    let totalOrder = 0;

    if (totalCart < 40) {
      totalOrder = Math.round((totalCart + 3.99 + Number.EPSILON) * 100) / 100;
    } else {
      totalOrder = totalCart;
    }

    return actions.order.create({
      purchase_units: [
        {
          amount: {
            value: totalOrder,
          },
        },
      ],
    });
  };

  function saveOrder() {
    let user = JSON.parse(localStorage.getItem("user"));
    UsersApi.postOrder(cart, user.id).then((res) => {
      if (res !== null) {
        console.log(res);
        setCart([]);
        setTotalItemsCart(0);
        setTotalCart(0);
        setOrderID(res.id);
        finish();
      }
    });
  }

  const onApprove = (data, actions) => {
    return actions.order.capture(saveOrder());
  };

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <AppBar
        position="absolute"
        color="default"
        elevation={0}
        sx={{
          position: "relative",
          borderBottom: (t) => `1px solid ${t.palette.divider}`,
        }}
      >
        <Toolbar>
          <Typography variant="h5" color="inherit" noWrap>
            DCine - Tienda de cultura fílmica
          </Typography>
        </Toolbar>
      </AppBar>
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <Review cart={cart} totalCart={totalCart} />
          <Typography variant="h6" gutterBottom sx={{ mt: 2, mb: 2 }}>
            Método de pago
          </Typography>
          <PayPalButton
            createOrder={(data, actions) => createOrder(data, actions)}
            onApprove={(data, actions) => onApprove(data, actions)}
          />
        </Paper>
        <Copyright />
      </Container>
    </ThemeProvider>
  );
}
