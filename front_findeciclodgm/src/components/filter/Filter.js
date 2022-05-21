import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Filter() {
  const [title, setTitle] = useState("");
  const [format, setFormat] = useState("");
  const [genre, setGenre] = useState("");
  const [featured, setFeatured] = useState("");

  const navigate = useNavigate();

  function handleFilter() {
    let url = "/peliculas";

    if (title != "" || format != "" || genre != "" || featured != "") {
      let formatURL = format;
      let genreURL = genre;
      let featuredURL = featured;

      if (format == "Formato") {
        formatURL = "";
      }

      if (genre == "Género") {
        genreURL = "";
      }

      if (featured == "Destacada") {
        featuredURL = "";
      } else {
        if (featured != "") {
          if (featured == "Sí") {
            featuredURL = true;
          } else {
            featuredURL = false;
          }
        } else {
          featuredURL = featured;
        }
      }

      url =
        "/peliculas/filtrar?titulo=" +
        title +
        "&genero=" +
        genreURL +
        "&destacada=" +
        featuredURL +
        "&formato=" +
        formatURL;
    }

    navigate(url);
    setTitle('');
    setFormat('');
    setGenre('');
    setFeatured('');
  }

  return (
    <div className="flex justify-center mt-4">
      <div className="grid md:grid-cols-5 md:gap-5 sm:grid-cols-1 md:gap-1">
        <input
          type="text"
          value={title}
          placeholder="Título"
          className="input input-bordered w-full max-w-xs mt-5 focus:ring-2 focus:ring-offset-2 focus:ring-principal"
          onChange={(event) => setTitle(event.target.value)}
        />

        <select
          className="select select-bordered w-full max-w-xs mt-5 focus:ring-2 focus:ring-offset-2 focus:ring-principal"
          value={genre}
          onChange={(event) => setGenre(event.target.value)}
        >
          <option>Género</option>
          <option>Acción</option>
          <option>Animación</option>
          <option>Anime</option>
          <option>Aventuras</option>
          <option>Bélico</option>
          <option>Ciencia ficción</option>
          <option>Comedia</option>
          <option>Drama</option>
          <option>Fantástico</option>
          <option>Histórico</option>
          <option>Policíaca</option>
          <option>Suspense</option>
          <option>Terror</option>
          <option>Thriller</option>
          <option>Western</option>
        </select>

        <select
          className="select select-bordered w-full max-w-xs mt-5 focus:ring-2 focus:ring-offset-2 focus:ring-principal"
          value={format}
          onChange={(event) => setFormat(event.target.value)}
        >
          <option defaultValue>Formato</option>
          <option>DVD</option>
          <option>Bluray</option>
          <option>UHD4K</option>
        </select>

        <select
          className="select select-bordered w-full max-w-xs mt-5 focus:ring-2 focus:ring-offset-2 focus:ring-principal"
          value={featured}
          onChange={(event) => setFeatured(event.target.value)}
        >
          <option defaultValue>Destacada</option>
          <option>Sí</option>
          <option>No</option>
        </select>

        <button
          onClick={() => handleFilter()}
          className="w-full inline-flex justify-center align-center py-4 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-principal hover:bg-gris-oscuro focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-principal mt-4"
        >
          Filtrar
        </button>
      </div>
    </div>
  );
}
