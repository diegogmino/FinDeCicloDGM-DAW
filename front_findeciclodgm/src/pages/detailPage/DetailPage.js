import ShoppingCart from "../../components/shoppingCart/ShoppingCart";
import Header from "../../parts/header/Header";
import { useParams } from 'react-router-dom'
import { useState } from 'react'
import Footer from "../../parts/footer/Footer";
import Product from "../../components/product/Product";

export default function DetailPage(props) {

    const { onAdd, totalItemsCart, cart } = props;
    const { id } = useParams()
    const [openCart, setOpenCart] = useState(false)

    return (
        <div>
            <Header openCart={openCart} setOpenCart={setOpenCart} totalItemsCart={totalItemsCart}/>
            <ShoppingCart openCart={openCart} setOpenCart={setOpenCart} cart={cart}/>
            <Product id={id} onAdd={onAdd}/>
            <Footer/>
        </div>
    );

}
