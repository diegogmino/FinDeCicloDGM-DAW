import * as React from 'react';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';

export default function PaymentForm(props) {

  const {paymentData, setPaymentData} = props;

  return (
    <React.Fragment>
      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          <TextField
            required
            id="cardName"
            label="Nombre de la tarjeta"
            fullWidth
            autoComplete="cc-name"
            variant="standard"
            onChange={(e) => {
              setPaymentData({...paymentData, cardName: e.target.value});
            }}
            value={paymentData.cardName}
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField
            required
            id="cardNumber"
            label="Número de la tarjeta"
            fullWidth
            autoComplete="cc-number"
            variant="standard"
            onChange={(e) => {
              setPaymentData({...paymentData, cardNumber: e.target.value});
            }}
            value={paymentData.cardNumber}
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField
            required
            id="expDate"
            label="Fecha de expiración"
            fullWidth
            autoComplete="cc-exp"
            variant="standard"
            onChange={(e) => {
              setPaymentData({...paymentData, expDate: e.target.value});
            }}
            value={paymentData.expDate}
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField
            required
            id="cvv"
            label="CVV"
            fullWidth
            autoComplete="cc-csc"
            variant="standard"
            onChange={(e) => {
              setPaymentData({...paymentData, cvv: e.target.value});
            }}
            value={paymentData.cvv}
          />
        </Grid>
      </Grid>
    </React.Fragment>
  );
}