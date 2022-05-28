import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Toolbar from "@mui/material/Toolbar";
import Paper from "@mui/material/Paper";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import AddressForm from "./AddressForm";
import PaymentForm from "./PaymentForm";
import Review from "./Review";
import { useNavigate } from "react-router-dom";
import UsersApi from "../../api/usersApi";

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

const steps = ["Dirección de envío", "Método de pago", "Revisión del pedido"];

function getStepContent(
  step,
  adressData,
  setAdressData,
  paymentData,
  setPaymentData,
  cart,
  totalCart
) {
  switch (step) {
    case 0:
      return (
        <AddressForm adressData={adressData} setAdressData={setAdressData} />
      );
    case 1:
      return (
        <PaymentForm
          paymentData={paymentData}
          setPaymentData={setPaymentData}
        />
      );
    case 2:
      return <Review cart={cart} totalCart={totalCart} />;
    default:
      throw new Error("Unknown step");
  }
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
  const [activeStep, setActiveStep] = React.useState(0);
  const { cart, setCart, totalCart, setTotalItemsCart, setTotalCart } = props;

  // Adress data
  const initialAdressDataState = {
    customerName: "",
    customerLastName: "",
    address1: "",
    phone: "",
    city: "",
    state: "",
    zip: "",
    country: "",
  };

  const [adressData, setAdressData] = React.useState(initialAdressDataState);

  // Payment data
  const initialPaymentDataState = {
    cardName: "",
    cardNumber: "",
    expDate: "",
    cvv: "",
  };

  const [paymentData, setPaymentData] = React.useState(initialPaymentDataState);
  const [orderData, setOrderData] = React.useState({});


  const handleNext = () => {
    let number = activeStep + 1;

    if(number === 3) {
      console.log(adressData);
      if(adressData.customerName == '' || adressData.customerLastName == '' || adressData.address1 == '' || adressData.phone == '' || adressData.city == '' || adressData.state == '' || adressData.zip == '' || adressData.country == '') {
        setActiveStep(0);
      } else {
        if(paymentData.cardName == '' || paymentData.cardNumber == '' || paymentData.expDate == '' || paymentData.cvv == '') {
          setActiveStep(1);
        } else {
          let user = JSON.parse(localStorage.getItem("user"));
          UsersApi.postOrder(cart, user.id).then((res) => {
            if(res !== null) {
              setActiveStep(activeStep + 1);
              console.log(res);
              setOrderData(res);
              setCart([]);
              setTotalItemsCart(0);
              setTotalCart(0);
            }
          });
        }
      }
    } else {
      setActiveStep(activeStep + 1);
    }

    
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
  };

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
          <Typography component="h1" variant="h4" align="center">
            Pasarela de pago
          </Typography>
          <Stepper activeStep={activeStep} sx={{ pt: 3, pb: 5 }}>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          <React.Fragment>
            {activeStep === steps.length ? (
              <React.Fragment>
                <Typography variant="h5" gutterBottom>
                  Gracias por confiar en nosotros
                </Typography>
                <Typography variant="subtitle1">
                  Tu número de pedido es #{orderData.id}. Muy pronto tendrás noticias
                  nuestras.
                </Typography>
                <Link to="/index">
                  <Button
                    onClick={returnHome}
                    variant="contained"
                    sx={{ mt: 3 }}
                  >
                    Volver a la tienda
                  </Button>
                </Link>
              </React.Fragment>
            ) : (
              <React.Fragment>
                {getStepContent(
                  activeStep,
                  adressData,
                  setAdressData,
                  paymentData,
                  setPaymentData,
                  cart,
                  totalCart
                )}
                <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
                  {activeStep !== 0 && (
                    <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                      Atrás
                    </Button>
                  )}

                  <Button
                    variant="contained"
                    onClick={handleNext}
                    sx={{ mt: 3, ml: 1 }}
                  >
                    {activeStep === steps.length - 1
                      ? "Confirmar pedido"
                      : "Siguiente"}
                  </Button>
                </Box>
              </React.Fragment>
            )}
          </React.Fragment>
        </Paper>
        <Copyright />
      </Container>
    </ThemeProvider>
  );
}
