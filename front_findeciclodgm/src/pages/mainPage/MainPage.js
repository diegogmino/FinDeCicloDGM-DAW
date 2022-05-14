import Header from '../../parts/header/Header';
import Footer from '../../parts/footer/Footer';
import Promo from '../../components/promo/Promo';
import ShoppingCart from '../../components/shoppingCart/ShoppingCart';
import FilterButtons from '../../components/filterButtons/FilterButtons';

import { useState } from 'react'

export default function MainPage() {

  const [openCart, setOpenCart] = useState(false)

  return (
    <div>
        <Header openCart={openCart} setOpenCart={setOpenCart}/>
        <Promo/>
        <ShoppingCart openCart={openCart} setOpenCart={setOpenCart}/>
        <FilterButtons/>     
        <Footer/>
    </div>
  );
}