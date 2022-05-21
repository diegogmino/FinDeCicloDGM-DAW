import Header from '../../parts/header/Header';
import Footer from '../../parts/footer/Footer';
import ShoppingCart from '../../components/shoppingCart/ShoppingCart';
import FilterButtons from '../../components/filterButtons/FilterButtons';
import FilmsGallery from '../../components/filmsGallery/FilmsGallery';
import React, { useState, useEffect } from 'react'
import FilmsApi from '../../api/filmsApi';
import Filter from './../../components/filter/Filter';

export default function FilmsPage() {

  const [openCart, setOpenCart] = useState(false)
  const [films, setFilms] = useState([])
  const [page, setPage] = useState(0)
  const [numberPages, setNumberPages] = useState(0)
  const [filmsNumber, setFilmsNumber] = useState(0);

  const size = 10;

  useEffect(() => {
    
    let filter = {};

    FilmsApi.getFiltered(filter, page, size).then((res) => {

      setFilms(res.content)
      setNumberPages(res.totalPages)
      setFilmsNumber(res.totalElements)

    });
  }, [page]);
  
  return (
    <div>
        <Header openCart={openCart} setOpenCart={setOpenCart}/>
        <FilterButtons/>
        <Filter/>
        <ShoppingCart openCart={openCart} setOpenCart={setOpenCart}/>
        <FilmsGallery films={films} setPage={setPage} numberPages={numberPages} filmsNumber={filmsNumber}/>
        <Footer/>
    </div>
  );
}