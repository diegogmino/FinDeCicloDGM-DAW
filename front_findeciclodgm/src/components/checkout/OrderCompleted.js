import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import AppBar from "@mui/material/AppBar";
import Container from "@mui/material/Container";
import Toolbar from "@mui/material/Toolbar";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useNavigate } from "react-router-dom";

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

export default function OrderCompleted(props) {
  const { orderID } = props;

  const navigate = useNavigate();

  const returnHome = () => {
    navigate("/index");
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
          <Typography variant="h6" color="inherit" noWrap>
            DCine - Tienda de cultura fílmica
          </Typography>
        </Toolbar>
      </AppBar>
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <React.Fragment>
            <Typography variant="h5" gutterBottom>
              Gracias por confiar en nosotros
            </Typography>
            <Typography variant="subtitle1">
              Tu número de pedido es #{orderID}. Muy pronto tendrás noticias
              nuestras.
            </Typography>
            <Button onClick={returnHome} variant="contained" sx={{ mt: 3 }}>
              Volver a la tienda
            </Button>
          </React.Fragment>
        </Paper>
        <Copyright />
      </Container>
    </ThemeProvider>
  );
}
