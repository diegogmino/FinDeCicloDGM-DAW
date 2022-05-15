import Header from '../../parts/header/Header';
import Footer from '../../parts/footer/Footer';
import ShoppingCart from '../../components/shoppingCart/ShoppingCart';
import FilterButtons from '../../components/filterButtons/FilterButtons';
import FilmsGallery from '../../components/filmsGallery/FilmsGallery';

import { useState } from 'react'

export default function FilmsPage() {

  const [openCart, setOpenCart] = useState(false)

  return (
    <div>
        <Header openCart={openCart} setOpenCart={setOpenCart}/>
        <FilterButtons/>
        <ShoppingCart openCart={openCart} setOpenCart={setOpenCart}/>
        <FilmsGallery/>
        <Footer/>
    </div>
  );
}