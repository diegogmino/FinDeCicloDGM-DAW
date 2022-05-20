import Header from "../../parts/header/Header";
import Footer from "../../parts/footer/Footer";
import ShoppingCart from "../../components/shoppingCart/ShoppingCart";
import FilterButtons from "../../components/filterButtons/FilterButtons";
import FilmsGallery from "../../components/filmsGallery/FilmsGallery";
import React, { useState, useEffect } from "react";
import FilmsApi from "../../api/filmsApi";
import { useParams, useLocation } from "react-router-dom";

export default function FilmsPage() {

  const search = useLocation().search;

  const title = new URLSearchParams(search).get("titulo");
  const maxPrice = new URLSearchParams(search).get("precioMax");
  const minPrice = new URLSearchParams(search).get("precioMin");
  const featured = new URLSearchParams(search).get("destacada");
  const format = new URLSearchParams(search).get("formato");
  const genre = new URLSearchParams(search).get("genero");

  const [openCart, setOpenCart] = useState(false);
  const [films, setFilms] = useState([]);
  const [page, setPage] = useState(0);
  const [numberPages, setNumberPages] = useState(0);
  const [filmsNumber, setFilmsNumber] = useState(0);

  const size = 10;

  useEffect(() => {

    let filter = {
        titulo: title == null ? '' : title,
        precioMax: maxPrice == null ? '' : maxPrice,
        precioMin: minPrice == null ? '' : minPrice,
        destacada: featured == null ? '' : featured,
        formato: format == null ? '' : format,
        genero: genre == null ? '' : genre,
    }

    FilmsApi.getFiltered(filter, page, size).then((res) => {
      setFilms(res.content);
      setNumberPages(res.totalPages);
      setFilmsNumber(res.totalElements);
    });
  }, [page, genre]);

  return (
    <div>
      <Header openCart={openCart} setOpenCart={setOpenCart} />
      <FilterButtons />
      <ShoppingCart openCart={openCart} setOpenCart={setOpenCart} />
      <FilmsGallery
        films={films}
        setPage={setPage}
        numberPages={numberPages}
        filmsNumber={filmsNumber}
      />
      <Footer />
    </div>
  );
}
