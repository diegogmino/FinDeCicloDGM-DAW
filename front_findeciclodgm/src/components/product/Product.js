import React, { useState, useEffect } from "react";
import FilmsApi from "../../api/filmsApi";
import AlertComponent from "./../alert/AlertComponent";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";

export default function Product(props) {
  const { id, onAdd } = props;
  const [film, setFilm] = useState([]);
  const [firstDirector, setFirsDirector] = useState([]);
  const [secondDirector, setSecondDirector] = useState([]);
  const [user, setUser] = useState("");

  const [open, setOpen] = React.useState(false);
  const [type, setType] = React.useState("");
  const [message, setMessage] = React.useState("");

  useEffect(() => {
    FilmsApi.get(id).then((res) => {
      setFilm(res);
      setFirsDirector(res.directores[0]);
      setSecondDirector(res.directores[1]);
    });
    let user = JSON.parse(localStorage.getItem("user"));
    if (user == null) {
      setUser("");
    } else {
      setUser(user);
    }
  }, []);

  function checkFilm(filmId) {
    return filmId === parseInt(id);
  }

  function addWishlist() {
    let wishlist = JSON.parse(localStorage.getItem("wishlist"));
    var wishlistArray = [];
    var keys = Object.keys(wishlist);

    keys.forEach(function(key) {
      wishlistArray.push(wishlist[key]);
    });

    if (wishlistArray.find(checkFilm) === undefined) {
      wishlistArray.push(film.id);
      setType("success");
      setMessage("Película añadida a tu lista de deseos");
      setOpen(true);
    } else {
      setType("error");
      setMessage("¡Ya has añadido esta película a tu lista de deseos!");
      setOpen(true);
    }

    localStorage.setItem("wishlist", JSON.stringify(wishlistArray));

    /*

    wishlistArray.push(film.id);
    localStorage.setItem('wishlist', JSON.stringify(wishlistArray));
    wishlist = JSON.parse(localStorage.getItem("wishlist"));
    console.log(wishlist);
    */
  }

  return (
    <div className="bg-white">
      <div className="pt-6">
        {/* Image */}
        <div className="mt-6 max-w-2xl mx-auto sm:px-6 lg:max-w-7xl lg:px-8 lg:grid lg:grid-cols-3 lg:gap-x-8">
          <div className="rounded-lg overflow-hidden lg:block aspect-w-11 aspect-h-16">
            <img
              src={film.portada}
              alt={film.titulo}
              className="w-full h-full object-center object-cover"
            />
          </div>
        </div>

        {/* Product info */}
        <div className="max-w-2xl mx-auto pt-10 pb-16 px-4 sm:px-6 lg:max-w-7xl lg:pt-16 lg:pb-24 lg:px-8 lg:grid lg:grid-cols-3 lg:grid-rows-[auto,auto,1fr] lg:gap-x-8">
          <div className="lg:col-span-2 lg:border-r lg:border-gray-200 lg:pr-8">
            <h1 className="text-2xl font-extrabold tracking-tight text-gray-900 sm:text-3xl">
              {film.titulo}
            </h1>
          </div>

          {/* Options */}
          <div className="mt-4 lg:mt-0 lg:row-span-3">
            <h2 className="sr-only">Información de la película</h2>
            <p className="text-3xl text-gray-900">{film.precio} €</p>

            {/* Available */}
            <div>
              {film.unidades > 5 && (
                <div className="flex items-center mt-6">
                  <div className="alert alert-success shadow-lg w-[9rem] ">
                    <div>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="stroke-current flex-shrink-0 h-6 w-6"
                        fill="none"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
                        />
                      </svg>
                      <span>Disponible</span>
                    </div>
                  </div>
                  <div>
                    {user !== "" && (
                      <button
                        onClick={() => addWishlist()}
                        className=" ml-4 bg-principal border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
                      >
                        <FavoriteOutlinedIcon />
                      </button>
                    )}
                  </div>
                </div>
              )}
              {film.unidades <= 5 && film.unidades != 0 && (
                <div className="flex items-center mt-6">
                  <div className="alert alert-warning shadow-lg w-[12rem]">
                    <div>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        class="stroke-current flex-shrink-0 h-6 w-6"
                        fill="none"
                        viewBox="0 0 24 24"
                      >
                        <path
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"
                        />
                      </svg>
                      <span>Últimas unidades</span>
                    </div>
                  </div>
                  <div>
                    {user !== "" && (
                      <button
                        onClick={() => addWishlist()}
                        className=" ml-4 bg-principal border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
                      >
                        <FavoriteOutlinedIcon />
                      </button>
                    )}
                  </div>
                </div>
              )}
              {film.unidades == 0 && (
                <div className="flex items-center mt-6">
                  <div className="alert alert-error shadow-lg w-[10.5rem]">
                    <div>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="stroke-current flex-shrink-0 h-6 w-6"
                        fill="none"
                        viewBox="0 0 24 24"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"
                        />
                      </svg>
                      <span>No disponible</span>
                    </div>
                  </div>
                  <div>
                    {user !== "" && (
                      <button
                        onClick={() => addWishlist()}
                        className=" ml-4 bg-principal border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
                      >
                        <FavoriteOutlinedIcon />
                      </button>
                    )}
                  </div>
                </div>
              )}
            </div>
            {film.unidades > 0 && (
              <button
                onClick={() => onAdd(film)}
                className="mt-8 w-full bg-principal border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal"
              >
                Añadir al carrito
              </button>
            )}
          </div>

          <div className="py-10 lg:pt-6 lg:pb-16 lg:col-start-1 lg:col-span-2 lg:border-r lg:border-gray-200 lg:pr-8">
            {/* Description and details */}
            <div>
              <h3 className="sr-only">Sinopsis</h3>

              <div className="space-y-6">
                <p className="text-base text-gray-900">{film.sinopsis}</p>
              </div>
            </div>

            <div className="mt-10">
              <h3 className="text-sm font-medium text-gray-900">Detalles</h3>

              <div className="mt-4">
                <ul role="list" className="pl-4 list-disc text-sm space-y-2">
                  <li key="director" className="text-gray-400">
                    <span className="text-gray-600">
                      <span className="font-bold">Director/es:</span>{" "}
                      {firstDirector?.nombre} {firstDirector?.apellido}
                      {secondDirector != undefined && (
                        <span>
                          , {secondDirector.nombre} {secondDirector.apellido}
                        </span>
                      )}
                    </span>
                  </li>
                  <li key={film.genero} className="text-gray-400">
                    <span className="text-gray-600">
                      <span className="font-bold">Género:</span> {film.genero}
                    </span>
                  </li>
                  <li key={film.formato} className="text-gray-400">
                    <span className="text-gray-600">
                      <span className="font-bold">Formato:</span> {film.formato}
                    </span>
                  </li>
                </ul>
              </div>
            </div>
            <AlertComponent
              type={type}
              open={open}
              setOpen={setOpen}
              message={message}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
