import { useState } from "react";
import AboutUsInfo from "../../components/aboutUsInfo/AboutUsInfo";
import Header from "../../parts/header/Header";
import Footer from "../../parts/footer/Footer";

import ShoppingCart from "../../components/shoppingCart/ShoppingCart";
import AboutUsImage from "../../components/aboutUsImage/AboutUsImage";

export default function AboutUs(props) {
  const [openCart, setOpenCart] = useState(false);
  const { totalItemsCart, cart, totalCart, onRemove } = props;

  return (
    <div>
      <Header
        openCart={openCart}
        setOpenCart={setOpenCart}
        totalItemsCart={totalItemsCart}
      />
      <AboutUsImage />
      <ShoppingCart
        openCart={openCart}
        setOpenCart={setOpenCart}
        cart={cart}
        totalCart={totalCart}
        onRemove={onRemove}
      />
      <AboutUsInfo />
      <Footer />
    </div>
  );
}
