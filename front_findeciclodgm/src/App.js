import './App.css';
import MainPage from './pages/mainPage/MainPage';
import AboutUs from './pages/aboutUs/AboutUs';
import ContactPage from './pages/contact/ContactPage';
import Login from './pages/login/Login';
import Signup from './pages/singup/Signup';
import FilmsPage from './pages/filmsPage/FilmsPage';
import FilmsPageFilter from './pages/filmsPageFilter/FilmsPageFilter';
import DetailPage from './pages/detailPage/DetailPage';
import Checkout from './components/checkout/Checkout';
import { useState } from 'react'
import AcountPage from './pages/accountPage/AccountPage';
import OrderCompleted from './components/checkout/OrderCompleted';

import {
  BrowserRouter as Router,
  Route,
  Routes,
} from 'react-router-dom';

function App() {

  const [cart, setCart] = useState([]);
  const [totalItemsCart, setTotalItemsCart] = useState(0);
  const [totalCart, setTotalCart] = useState(0);
  const [orderID, setOrderID] = useState(0);

  const onAdd = (film) => {
    const exist = cart.find((x) => x.id === film.id);
    if(exist) {
      if(exist.qty < film.unidades) {
        setCart(
          cart.map((x) => 
            x.id === film.id ? { ...exist,  qty: exist.qty + 1} : x
          )
        );
        setTotalItemsCart(totalItemsCart+1);
        setTotalCart(Math.round(((totalCart + film.precio) + Number.EPSILON) * 100) / 100);
      }
    } else {
      setCart([...cart, { ...film, qty: 1}]);
      setTotalItemsCart(totalItemsCart+1);
      setTotalCart(Math.round(((totalCart + film.precio) + Number.EPSILON) * 100) / 100);
    }
    
  }

  const onRemove = (film) => {
    const exist = cart.find((x) => x.id === film.id);
    if(exist.qty === 1) {
      setCart(cart.filter((x) => x.id !== film.id));
    } else {
      setCart(
        cart.map((x) => 
          x.id === film.id ? { ...exist, qty: exist.qty - 1} : x
        )
      ); 
    }
    setTotalItemsCart(totalItemsCart-1);
    setTotalCart(Math.round(((totalCart - film.precio) + Number.EPSILON) * 100) / 100);
  }

  return (
    <div>
    <Router>
      <Routes>
        <Route path="/" element={<MainPage totalItemsCart={totalItemsCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>}/>
        <Route path="/index" element={<MainPage totalItemsCart={totalItemsCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/signup" element={<Signup/>}/>
        <Route path="/quienes-somos" element={<AboutUs totalItemsCart={totalItemsCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>}/>
        <Route path="/contacto" element={<ContactPage totalItemsCart={totalItemsCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>}/>
        <Route path="/peliculas" element={<FilmsPage totalItemsCart={totalItemsCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>}/>
        <Route path="/peliculas/filtrar" element={<FilmsPageFilter totalItemsCart={totalItemsCart} totalCart={totalCart} cart={cart} onRemove={onRemove}/>}/>
        <Route path="/detalle/:id" element={<DetailPage onAdd={onAdd} totalItemsCart={totalItemsCart} totalCart={totalCart} cart={cart} onRemove={onRemove}/>}/>
        <Route path="/checkout" element={<Checkout cart={cart} setCart={setCart} totalCart={totalCart} setTotalItemsCart={setTotalItemsCart} setTotalCart={setTotalCart} setOrderID={setOrderID}/>}/>
        <Route path="/checkout/completed" element={<OrderCompleted orderID={orderID}/>}/>
        <Route path="/cuenta" element={<AcountPage totalItemsCart={totalItemsCart} cart={cart} totalCart={totalCart} onRemove={onRemove}/>}/>
      </Routes>
    </Router>  
    </div>
  );
}

export default App;