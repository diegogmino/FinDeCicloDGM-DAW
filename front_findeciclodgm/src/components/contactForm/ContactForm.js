import * as React from "react";
import Grid from "@mui/material/Grid";
import UsersApi from "../../api/usersApi";
import CircularProgress from "@mui/material/CircularProgress";
import AlertComponent from "./../alert/AlertComponent";
import { useNavigate } from "react-router-dom";

export default function ContactForm() {
  const [name, setName] = React.useState("");
  const [lastName, setLastName] = React.useState("");
  const [mail, setMail] = React.useState("");
  const [country, setCountry] = React.useState("España");
  const [text, setText] = React.useState("");
  const [showProgress, setShowProgress] = React.useState(false);
  const [open, setOpen] = React.useState(false);
  const [type, setType] = React.useState("");
  const [message, setMessage] = React.useState("");

  const navigate = useNavigate();

  function handleSubmit() {
    setShowProgress(true);
    let messageData = {
      nombre: name,
      apellidos: lastName,
      mail: mail,
      pais: country,
      mensaje: text,
    };

    UsersApi.postMessage(messageData)
      .then((res) => {
        setType("success");
        setMessage(
          "¡Mensaje enviado! En un momento te redirigimos a la página principal."
        );
        setOpen(true);
        setTimeout(() => {
          navigate("/index");
        }, 4000);
      })
      .catch(function (error) {
        setType("error");
        setMessage("¡Ooops, ha ocurrido un error!");
        setOpen(true);
        setShowProgress(false);
      });
  }

  return (
    <div>
      <div>
        <div className="text-center mb-6 mt-6">
          <p className="text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
            Queremos saber tu opinión
          </p>
        </div>
      </div>

      <Grid container component="main" sx={{ mb: 3 }} justifyContent="center">
        <Grid item xs={12} sm={10} md={7}>
          <form>
            <div className="shadow overflow-hidden sm:rounded">
              <div className="px-4 py-5 bg-white sm:p-6">
                <div className="grid grid-cols-6 gap-6">
                  <div className="col-span-6 sm:col-span-3">
                    <label
                      htmlFor="first-name"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Nombre
                    </label>
                    <input
                      type="text"
                      name="nombre"
                      id="nombre"
                      autoComplete="given-name"
                      className="mt-1 focus:ring-principal focus:border-principal block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                      onChange={(event) => setName(event.target.value)}
                    />
                  </div>

                  <div className="col-span-6 sm:col-span-3">
                    <label
                      htmlFor="last-name"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Apellido/s
                    </label>
                    <input
                      type="text"
                      name="apellido"
                      id="apellido"
                      autoComplete="family-name"
                      className="mt-1 focus:ring-principal focus:border-principal block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                      onChange={(event) => setLastName(event.target.value)}
                    />
                  </div>

                  <div className="col-span-6 sm:col-span-4">
                    <label
                      htmlFor="email-address"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Correo electrónico
                    </label>
                    <input
                      type="text"
                      name="email"
                      id="email"
                      autoComplete="email"
                      className="mt-1 focus:ring-principal focus:border-principal block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                      onChange={(event) => setMail(event.target.value)}
                    />
                  </div>

                  <div className="col-span-6 sm:col-span-3">
                    <label
                      htmlFor="country"
                      className="block text-sm font-medium text-gray-700"
                    >
                      País
                    </label>
                    <select
                      id="pais"
                      name="pais"
                      autoComplete="country-name"
                      className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-principal focus:border-principal sm:text-sm"
                      onChange={(event) => setCountry(event.target.value)}
                    >
                      <option>España</option>
                      <option>Francia</option>
                      <option>Portugal</option>
                    </select>
                  </div>

                  <div className="col-span-6">
                    <label
                      htmlFor="about"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Mensaje
                    </label>
                    <div className="mt-1">
                      <textarea
                        id="mensaje"
                        name="mensaje"
                        rows={3}
                        className="shadow-sm focus:ring-principal focus:border-principal mt-1 block w-full sm:text-sm border border-gray-300 rounded-md"
                        placeholder="Escríbenos algo bonito :)"
                        defaultValue={""}
                        onChange={(event) => setText(event.target.value)}
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div className="flex flex-row justify-end px-4 py-3 bg-gray-50 sm:px-6">
                {showProgress === true && (
                  <CircularProgress color="inherit" className="mr-6" />
                )}
                <div>
                  <button
                    type="button"
                    onClick={() => handleSubmit()}
                    className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-principal hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
                  >
                    Enviar
                  </button>
                </div>
              </div>
            </div>
          </form>
        </Grid>
      </Grid>
      <AlertComponent
        type={type}
        open={open}
        setOpen={setOpen}
        message={message}
      />
    </div>
  );
}
