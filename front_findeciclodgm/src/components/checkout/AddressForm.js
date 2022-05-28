import * as React from "react";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";

export default function AddressForm(props) {
  const { adressData, setAdressData } = props;

  React.useEffect(() => {
    let user = JSON.parse(localStorage.getItem("user"));
    setAdressData({
      customerName: user.nombre,
      customerLastName: user.apellido,
      address1: user.direccion,
      phone: user.telefono,
      country: user.pais,
      city: "",
      state: "",
      zip: "",
    });
  }, []);

  return (
    <React.Fragment>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6}>
          <TextField
            required
            id="firstName"
            name="firstName"
            label="Nombre"
            fullWidth
            autoComplete="given-name"
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, customerName: e.target.value });
            }}
            value={adressData.customerName}
            disabled={true}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            required
            id="lastName"
            name="lastName"
            label="Apellido/s"
            fullWidth
            autoComplete="family-name"
            variant="standard"
            onChange={(e) => {
              setAdressData({
                ...adressData,
                customerLastName: e.target.value,
              });
            }}
            value={adressData.customerLastName}
            disabled={true}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            required
            id="address1"
            name="address1"
            label="Dirección de envío 1"
            fullWidth
            autoComplete="shipping address-line1"
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, address1: e.target.value });
            }}
            value={adressData.address1}
            disabled={true}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            required
            id="phone"
            name="phone"
            label="Teléfono"
            fullWidth
            autoComplete="shipping address-line2"
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, phone: e.target.value });
            }}
            value={adressData.phone}
            disabled={true}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            required
            id="city"
            name="city"
            label="Ciudad"
            fullWidth
            autoComplete="shipping address-level2"
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, city: e.target.value });
            }}
            value={adressData.city}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            required
            id="state"
            name="state"
            label="Comunidad"
            fullWidth
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, state: e.target.value });
            }}
            value={adressData.state}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            required
            id="zip"
            name="zip"
            label="Código postal"
            fullWidth
            autoComplete="shipping postal-code"
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, zip: e.target.value });
            }}
            value={adressData.zip}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            required
            id="country"
            name="country"
            label="País"
            fullWidth
            autoComplete="shipping country"
            variant="standard"
            onChange={(e) => {
              setAdressData({ ...adressData, country: e.target.value });
            }}
            value={adressData.country}
            disabled={true}
          />
        </Grid>
      </Grid>
    </React.Fragment>
  );
}
