import { useState } from 'react'
import Header from '../../parts/header/Header';
import Footer from '../../parts/footer/Footer';
import ShoppingCart from '../../components/shoppingCart/ShoppingCart';
import ContactForm from '../../components/contactForm/ContactForm';
import ContactImage from '../../components/contactImage/ContactImage';

export default function ContactPage(props) {

    const [openCart, setOpenCart] = useState(false);
    const {totalItemsCart} = props;

    return (
        <div>
            <Header openCart={openCart} setOpenCart={setOpenCart} totalItemsCart={totalItemsCart}/>
            <ShoppingCart openCart={openCart} setOpenCart={setOpenCart}/> 
            <ContactImage/>
            <ContactForm/>
            <Footer/>
        </div>
    );
}