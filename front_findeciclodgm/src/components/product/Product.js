import React, { useState, useEffect } from "react";
import FilmsApi from "../../api/filmsApi";

export default function Product(props) {
  const { id } = props;
  const [film, setFilm] = useState([]);
  const [firstDirector, setFirsDirector] = useState([]);
  const [secondDirector, setSecondDirector] = useState([]);

  useEffect(() => {
    FilmsApi.get(id).then((res) => {
      setFilm(res);
      setFirsDirector(res.directores[0]);
      setSecondDirector(res.directores[1]);
    });
  }, []);

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
            <h2 className="sr-only">Product information</h2>
            <p className="text-3xl text-gray-900">{film.precio} €</p>

            <form className="mt-10">
              {/* Available */}
              <div>
                {film.unidades > 0 && (
                  <div className="alert alert-success shadow-lg w-[9rem]">
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
                          d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
                        />
                      </svg>
                      <span>Disponible</span>
                    </div>
                  </div>
                )}
                {film.unidades == 0 && (
                  <div className="alert alert-error shadow-lg w-[10.5rem]">
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
                          d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"
                        />
                      </svg>
                      <span>No disponible</span>
                    </div>
                  </div>
                )}
              </div>
              {film.unidades > 0 && (
                  <button
                  className="mt-10 w-full bg-principal border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Añadir al carrito
                </button>
                )}
            </form>
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
          </div>
        </div>
      </div>
    </div>
  );
}
