import Header from '../../parts/header/Header';
import Footer from '../../parts/footer/Footer';
import Promo from '../../components/promo/Promo';
import ShoppingCart from '../../components/shoppingCart/ShoppingCart';
import FilterButtons from '../../components/filterButtons/FilterButtons';

import { useState } from 'react'

export default function MainPage(props) {

  const {totalItemsCart, cart, totalCart, onRemove, user} = props;

  const [openCart, setOpenCart] = useState(false)

  return (
    <div>
        <Header openCart={openCart} setOpenCart={setOpenCart} totalItemsCart={totalItemsCart} user={user}/>
        <Promo/>
        <ShoppingCart openCart={openCart} setOpenCart={setOpenCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>
        <FilterButtons/>     
        <Footer/>
    </div>
  );
}