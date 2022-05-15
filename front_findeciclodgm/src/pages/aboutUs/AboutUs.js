import { useState } from 'react'
import AboutUsInfo from '../../components/aboutUsInfo/AboutUsInfo';
import Header from '../../parts/header/Header';
import Footer from '../../parts/footer/Footer';

import ShoppingCart from '../../components/shoppingCart/ShoppingCart';
import AboutUsImage from '../../components/aboutUsImage/AboutUsImage';

export default function AboutUs() {

    const [openCart, setOpenCart] = useState(false)

    return (
        <div>
            <Header openCart={openCart} setOpenCart={setOpenCart}/>
            <AboutUsImage/>
            <ShoppingCart openCart={openCart} setOpenCart={setOpenCart}/> 
            <AboutUsInfo/>
            <Footer/>
        </div>
    );
}