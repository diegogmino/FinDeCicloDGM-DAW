import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import { Link } from 'react-router-dom';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import ReturnButtonLogo from '../../components/returnButtonLogo/ReturnButtonLogo';
import UsersApi from "../../api/usersApi";
import { useNavigate } from "react-router-dom";

import padrino from '../../img/iniciar-sesion.webp';
import AlertComponent from './../../components/alert/AlertComponent';

const theme = createTheme();

export default function Login(props) {

  const [open, setOpen] = React.useState(false);
  const [type, setType] = React.useState('');
  const [message, setMessage] = React.useState('');

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    UsersApi.login(data.get('email'), data.get('password')).then((res) => {
      localStorage.setItem('user', JSON.stringify(res));
      let wishlist = JSON.parse(localStorage.getItem("wishlist"));
      if(wishlist == null) {
        localStorage.setItem('wishlist', JSON.stringify(''));
      }
      navigate('/index');
    }).catch(function (error) {
        setType("error");
        setMessage('¡Ooops, usuario o contraseña incorrectos!');
        setOpen(true);
      });;
  };

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${padrino})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >

            <ReturnButtonLogo/>

            <Typography component="h1" variant="h5" sx={{ mt: 3 }}>
              Iniciar sesión
            </Typography>
            
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                sx={{
                    "& .MuiInputLabel-root.Mui-focused": {color: '#820933'},
                    "& .MuiOutlinedInput-root.Mui-focused": {
                    "& > fieldset": {
                    borderColor: "#820933"
                            }
                        }
                    }}
                margin="normal"
                required
                fullWidth
                id="email"
                label="Correo electrónico"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <TextField
                sx={{
                    "& .MuiInputLabel-root.Mui-focused": {color: '#820933'},//styles the label
                    "& .MuiOutlinedInput-root.Mui-focused": {
                    "& > fieldset": {
                    borderColor: "#820933"
                            }
                        }
                    }}
                margin="normal"
                required
                fullWidth
                name="password"
                label="Contraseña"
                type="password"
                id="password"
                autoComplete="current-password"
              />
                <button
                  type="submit"
                  className=" mt-2 mb-2 w-full inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-principal hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
                >
                  Iniciar sesión
                </button>
              <Grid container>
                <Grid item>
                  <Link to="/signup" className="text-sm font-medium text-gray-700 hover:text-principal">¿No tienes una cuenta? Crea una y únete a la familia</Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
      <AlertComponent type={type} open={open} setOpen={setOpen} message={message}/>
    </ThemeProvider>
  );
}