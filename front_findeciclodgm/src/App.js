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

import {
  BrowserRouter as Router,
  Route,
  Routes,
} from 'react-router-dom';

function App() {

  const [cart, setCart] = useState([]);
  const [totalItemsCart, setTotalItemsCart] = useState(0);

  const onAdd = (film) => {
    const exist = cart.find((x) => x.id === film.id);
    if(exist) {
      setCart(
        cart.map((x) => 
          x.id === film.id ? { ...exist, qty: exist.qty + 1} : x
        )
      );
      
    } else {
      setCart([...cart, { ...film, qty: 1}]);
    }
    setTotalItemsCart(totalItemsCart+1);
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
  }

  return (
    <div>
    <Router>
      <Routes>
        <Route path="/" element={<MainPage totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/index" element={<MainPage totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/signup" element={<Signup/>}/>
        <Route path="/quienes-somos" element={<AboutUs totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/contacto" element={<ContactPage totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/peliculas" element={<FilmsPage totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/peliculas/filtrar" element={<FilmsPageFilter totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/detalle/:id" element={<DetailPage onAdd={onAdd} totalItemsCart={totalItemsCart} cart={cart}/>}/>
        <Route path="/checkout" element={<Checkout/>}/>
      </Routes>
    </Router>  
    </div>
  );
}

export default App;