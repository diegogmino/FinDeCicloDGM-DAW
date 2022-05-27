import React, { useState, useEffect } from "react";
import Header from "../../parts/header/Header";
import Footer from "../../parts/footer/Footer";
import AccountImage from "./../../components/accountImage/AccountImage";
import FilmsApi from "../../api/filmsApi";
import UsersApi from "../../api/usersApi";
import ShoppingCart from "./../../components/shoppingCart/ShoppingCart";
import { useNavigate } from "react-router-dom";
import WishlistGallery from "../../components/wishlistGallery/WishlistGallery";
import OrderList from "../../components/orderList/OrderList";

export default function AccountPage(props) {
  const { totalItemsCart, cart, totalCart, onRemove } = props;

  const [openCart, setOpenCart] = useState(false);
  const [films, setFilms] = useState([]);
  const [isWishlistEmpty, setIsWishlistEmpty] = useState(true);
  const [orders, setOrders] = useState([]);
  const [isOrdersEmpty, setIsOrdersEmpty] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    let user = JSON.parse(localStorage.getItem("user"));

    if (user == null || user == "") {
      navigate("/login");
    } else {
      let wishlist = JSON.parse(localStorage.getItem("wishlist"));
      let wishlistArray = [];
      let keys = Object.keys(wishlist);

      keys.forEach(function (key) {
        wishlistArray.push(wishlist[key]);
      });

      if (wishlistArray.length !== 0) {
        FilmsApi.getWishlist(wishlistArray).then((res) => {
          setFilms(res);
          setIsWishlistEmpty(false);
        });
      } else {
        setIsWishlistEmpty(true);
      }

      UsersApi.getOrders(user.id).then((res) => {
        console.log(res.length);
        if (res.length === 0) {
          setIsOrdersEmpty(true);
        } else {
          setOrders(res);
          setIsOrdersEmpty(false);
        }
      });
    }
  }, []);

  return (
    <div>
      <Header
        openCart={openCart}
        setOpenCart={setOpenCart}
        totalItemsCart={totalItemsCart}
      />
      <ShoppingCart
        openCart={openCart}
        setOpenCart={setOpenCart}
        cart={cart}
        totalCart={totalCart}
        onRemove={onRemove}
      />
      <AccountImage />
      <div>
        <div className="text-center mt-7">
          <p className="text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
            Lista de deseos
          </p>
        </div>
      </div>
      {isWishlistEmpty === true && (
        <div className="text-center mt-7 mb-4">
          <p className="text-3xl leading-8 tracking-tight text-gray-900 sm:text-4xl">
            Tu lista de deseos está vacía. ¡Añade películas y aparecerán aquí!
          </p>
        </div>
      )}
      {isWishlistEmpty === false && <WishlistGallery films={films} />}

      {isOrdersEmpty === true && (
        <div className="mb-10">
          <div className="flex flex-col">
            <div className="text-center mt-7">
              <p className="text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
                Tus pedidos
              </p>
            </div>
          </div>
          <div className="text-center mt-7">
            <p className="text-3xl leading-8 tracking-tight text-gray-900 sm:text-4xl">
              Por ahora no has hecho ningún pedido, ¡pero eso puede cambiar! :)
            </p>
          </div>
        </div>
      )}
      {isOrdersEmpty === false && (
        <div>
          <div className="hidden md:flex flex-col">
            <div className="text-center mt-7 mb-9">
              <p className="text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
                Tus pedidos
              </p>
            </div>
          </div>
          <OrderList orders={orders} />
        </div>
      )}
      <Footer />
    </div>
  );
}
