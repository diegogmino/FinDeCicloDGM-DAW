import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import ReturnButtonLogo from "../../components/returnButtonLogo/ReturnButtonLogo";
import AlertComponent from "../../components/alert/AlertComponent";
import UsersApi from "../../api/usersApi";
import { useNavigate } from "react-router-dom";

import señorDeLosAnillos from "../../img/crear-cuenta.jpg";

const theme = createTheme();

export default function Signup() {
  const [open, setOpen] = React.useState(false);
  const [type, setType] = React.useState("");
  const [message, setMessage] = React.useState('');

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    let user = {
      nombre: data.get("name"),
      apellido: data.get("surname"),
      email: data.get("email"),
      contrasena: data.get("password"),
      direccion: data.get("direction"),
      pais: data.get("country"),
      telefono: data.get("phone"),
    };

    console.log("hola");

    UsersApi.signUp(user)
      .then(() => {
        setType("success");
        setMessage('¡Registro completado! En un momento te redirigimos al inicio de sesión.');
        setOpen(true);
        setTimeout(() => {
          navigate("/login");
        }, 4000);
      })
      .catch(function (error) {
        setType("error");
        setMessage('¡Ooops, ha ocurrido un error!');
        setOpen(true);
      });
  };

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: "100vh" }}>
        <CssBaseline />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <ReturnButtonLogo />

            <Typography component="h1" variant="h5" sx={{ mt: 3 }}>
              Crear cuenta
            </Typography>

            <Box
              component="form"
              onSubmit={handleSubmit}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    autoComplete="given-name"
                    name="name"
                    required
                    fullWidth
                    id="nombre"
                    label="Nombre"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    autoComplete="given-name"
                    name="surname"
                    required
                    fullWidth
                    id="apellido"
                    label="Apellido/s"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    required
                    fullWidth
                    id="email"
                    label="Correo electrónico"
                    name="email"
                    autoComplete="email"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    required
                    fullWidth
                    name="password"
                    label="Contraseña"
                    type="password"
                    id="contrasena"
                    autoComplete="new-password"
                  />
                </Grid>
                <Grid item xs={12} sm={12}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    autoComplete="given-name"
                    name="direction"
                    required
                    fullWidth
                    id="direccion"
                    label="Dirección"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    autoComplete="given-name"
                    name="country"
                    required
                    fullWidth
                    id="pais"
                    label="Pais"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    sx={{
                      "& .MuiInputLabel-root.Mui-focused": { color: "#820933" }, //styles the label
                      "& .MuiOutlinedInput-root.Mui-focused": {
                        "& > fieldset": {
                          borderColor: "#820933",
                        },
                      },
                    }}
                    autoComplete="given-name"
                    name="phone"
                    required
                    fullWidth
                    id="telefono"
                    label="Teléfono"
                    autoFocus
                  />
                </Grid>
                <Grid item xs={12} sm={12}>
                  <button
                    type="submit"
                    className=" mt-2 mb-2 w-full inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-principal hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
                  >
                    Crea tu cuenta
                  </button>
                  <AlertComponent />
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${señorDeLosAnillos})`,
            backgroundRepeat: "no-repeat",
            backgroundColor: (t) =>
              t.palette.mode === "light"
                ? t.palette.grey[50]
                : t.palette.grey[900],
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}
        />
      </Grid>
      <AlertComponent type={type} open={open} setOpen={setOpen} message={message}/>
    </ThemeProvider>
  );
}
