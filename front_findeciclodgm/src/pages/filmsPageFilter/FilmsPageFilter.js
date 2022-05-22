import Header from "../../parts/header/Header";
import Footer from "../../parts/footer/Footer";
import ShoppingCart from "../../components/shoppingCart/ShoppingCart";
import FilterButtons from "../../components/filterButtons/FilterButtons";
import FilmsGallery from "../../components/filmsGallery/FilmsGallery";
import React, { useState, useEffect } from "react";
import FilmsApi from "../../api/filmsApi";
import { useLocation } from "react-router-dom";
import Filter from './../../components/filter/Filter';

export default function FilmsPage(props) {

  const search = useLocation().search;

  const title = new URLSearchParams(search).get("titulo");
  const featured = new URLSearchParams(search).get("destacada");
  const format = new URLSearchParams(search).get("formato");
  const genre = new URLSearchParams(search).get("genero");

  const [openCart, setOpenCart] = useState(false);
  const [films, setFilms] = useState([]);
  const [page, setPage] = useState(0);
  const [numberPages, setNumberPages] = useState(0);
  const [filmsNumber, setFilmsNumber] = useState(0);

  const {totalItemsCart} = props;

  const size = 10;

  useEffect(() => {

    let filter = {
        titulo: title == null ? '' : title,
        destacada: featured == null ? '' : featured,
        formato: format == null ? '' : format,
        genero: genre == null ? '' : genre,
    }

    console.log(filter);

    FilmsApi.getFiltered(filter, page, size).then((res) => {
      setFilms(res.content);
      setNumberPages(res.totalPages);
      setFilmsNumber(res.totalElements);
    });
  }, [page, title, featured, format, genre]);

  return (
    <div>
      <Header openCart={openCart} setOpenCart={setOpenCart} totalItemsCart={totalItemsCart}/>
      <FilterButtons />
      <Filter/>
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
